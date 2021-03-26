package edu.neu.cs6510.pnnl.hunting.job;

import edu.neu.cs6510.pnnl.hunting.h2mapper.AlertMapper;
import edu.neu.cs6510.pnnl.hunting.h2mapper.UpdateInfoMapper;
import edu.neu.cs6510.pnnl.hunting.h2mapper.VavAlertMapper;
import edu.neu.cs6510.pnnl.hunting.h2mapper.VavH2Mapper;
import edu.neu.cs6510.pnnl.hunting.model.Alert;
import edu.neu.cs6510.pnnl.hunting.model.UpdateInfo;
import edu.neu.cs6510.pnnl.hunting.model.Vav;
import edu.neu.cs6510.pnnl.hunting.model.VavAlert;
import edu.neu.cs6510.pnnl.hunting.service.TableUtilService;
import edu.neu.cs6510.pnnl.hunting.service.VavService;
import edu.neu.cs6510.pnnl.hunting.utils.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import static edu.neu.cs6510.pnnl.hunting.utils.ConfigConst.*;

import java.util.*;

@Component
public class HuntingJob extends QuartzJobBean {


    private VavService vavService;
    private VavAlertMapper vavAlertMapper;
    private AlertMapper alertMapper;
    private UpdateInfoMapper updateInfoMapper;
    private TableUtilService tableUtilService;
    private final Set<String> vavSet = new HashSet<>();
    private  static Deque<Vav> deque = new LinkedList<>();






    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        getAllVav();
        System.out.println("Get All Vav");
        checkTemperature();
        System.out.println("Finish Check "+new Date());
    }


    private void checkTemperature(){

        Date now = new Date(System.currentTimeMillis() - TIME_SHIFT);
        System.out.println(DateUtil.convertDateToString(now));
        for(String vavName:vavSet){
            Date startTime = null;
            Date endTime = null;
            UpdateInfo updateInfo = updateInfoMapper.selectByPrimaryKey(vavName);
            if(updateInfo == null){
                // First time to query this VAV
                startTime = DateUtil.getWorkHourStartTime(now);
                endTime = now;
                updateInfo = new UpdateInfo();
                updateInfo.setVavName(vavName);
                updateInfo.setTime(now);
                doHunting(vavName, startTime, endTime);
                updateInfoMapper.insert(updateInfo);
            }else{
                startTime = updateInfo.getTime();
                endTime = now;
                updateInfo.setTime(now);
                doHunting(vavName, startTime, endTime);
                updateInfoMapper.updateByPrimaryKeySelective(updateInfo);
            }
        }

    }

    private void doHunting(String vavName, Date startTime, Date endTime) {
        List<Vav> vavInRange = vavService.getVavInRange(startTime, endTime, vavName);
        for(Vav vav: vavInRange){
            if(isAnomaly(vav)) {
                if(!deque.isEmpty() && largeThanOneHour(deque.getFirst(),vav)){
                    if(deque.size() >= WARNING){
                        sendAlert(deque.getLast().getCommon().getTime());
                    }
                    while (!deque.isEmpty() && largeThanOneHour(deque.getFirst(), vav)) {
                        vavAlertMapper.insertSelective(new VavAlert(Objects.requireNonNull(deque.pollFirst())));
                    }
                }
                deque.add(vav);

            }
        }
        // Run out of all vav, still have some anomaly vav in the deque.
        if(DateUtil.getWorkHourEndTime(endTime).getTime() <= endTime.getTime() && deque.size() >= WARNING){
            // TODO Save alert with tha last vav time
            sendAlert(deque.getLast().getCommon().getTime());
            // Insert all remain anomaly vav
            while(!deque.isEmpty()){
                vavAlertMapper.insertSelective(new VavAlert(Objects.requireNonNull(deque.pollFirst())));
            }
        }
    }

    private void sendAlert(Date time) {
        Alert alert = new Alert();
        alert.setTime(time);
        alertMapper.insert(alert);
    }

    private boolean isAnomaly(Vav vav) {
        if(vav.getZoneTemperature() != null){
            double temp = vav.getZoneTemperature();
            double setPoint = vav.getZoneHeatingTemperatureSetPoint();
            return temp > setPoint + 2;
        }
        return false;
    }

    private boolean largeThanOneHour(Vav first, Vav last) {
        return Math.abs(first.getCommon().getTime().getTime() - last.getCommon().getTime().getTime()) > ONE_HOUR;
    }

    private void getAllVav(){
        List<String> table = tableUtilService.getAllVavTable();
        vavSet.addAll(table);
    }


    public void setVavService(VavService vavService) {
        this.vavService = vavService;
    }

    public void setTableUtilService(TableUtilService tableUtilService) {
        this.tableUtilService = tableUtilService;
    }

    public void setVavAlertMapper(VavAlertMapper vavAlertMapper) {
        this.vavAlertMapper = vavAlertMapper;
    }

    public void setUpdateInfoMapper(UpdateInfoMapper updateInfoMapper) {
        this.updateInfoMapper = updateInfoMapper;
    }

    public void setAlertMapper(AlertMapper alertMapper) {
        this.alertMapper = alertMapper;
    }
}
