package edu.neu.cs6510.pnnl.hunting.job;

import edu.neu.cs6510.pnnl.hunting.h2mapper.AlertMapper;
import edu.neu.cs6510.pnnl.hunting.h2mapper.TableUtilH2Mapper;
import edu.neu.cs6510.pnnl.hunting.h2mapper.UpdateInfoMapper;
import edu.neu.cs6510.pnnl.hunting.h2mapper.VavAlertMapper;
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
    private TableUtilH2Mapper tableUtilH2Mapper;
    private final Set<String> vavSet = new HashSet<>();
    private static final HashMap<String,Deque<Vav>> vavDequeMap = new HashMap<>();
    private static final HashMap<String,Boolean> ExistOnSameSideVavMap = new HashMap<>();







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
        Deque<Vav> deque = vavDequeMap.getOrDefault(vavName, new LinkedList<>());
        Boolean isExistOnSameSide = ExistOnSameSideVavMap.getOrDefault(vavName, false);
        for(Vav vav: vavInRange){
            int tag = valueRange(vav);
            if(tag != 0) {
                if(!deque.isEmpty() && largeThanOneHour(deque.getFirst(),vav)){
                    if(deque.size() >= WARNING){
                        sendAlert(vavName,deque.getLast().getCommon().getTime());
                    }
                    if(!deque.isEmpty() && largeThanOneHour(deque.getLast(), vav) && isExistOnSameSide){
                        // if the anomaly temperature keep on one side
                        // Poll the data large than one hour
                        System.out.println("First:"+deque.getLast().getCommon().getTime());
                        deque.pollLast();
                    }
                    while (!deque.isEmpty() && largeThanOneHour(deque.getFirst(), vav)) {
                        System.out.println("Third:"+deque.getFirst().getCommon().getTime());
                        vavAlertMapper.insert(vavName,new VavAlert(Objects.requireNonNull(deque.pollFirst())));
                    }
                }
                if(!deque.isEmpty()){
                    if(valueRange(deque.getLast()) == valueRange(vav)){
                        if(isExistOnSameSide){
                            System.out.println("Second:"+deque.getLast().getCommon().getTime());
                            deque.pollLast();
                        }
                        isExistOnSameSide = true;
                    }else {
                        isExistOnSameSide = false;
                    }
                }else {
                    isExistOnSameSide = false;
                }
                deque.addLast(vav);
            }
        }
        // Run out of all vav, still have some anomaly vav in the deque.
        if(DateUtil.getWorkHourEndTime(endTime).getTime() <= endTime.getTime() && deque.size() >= WARNING){
            Vav last = deque.getLast();
            sendAlert(last.getVavName(),last.getCommon().getTime());
            // Insert all remain anomaly vav
            while(!deque.isEmpty()){
                vavAlertMapper.insert(vavName,new VavAlert(Objects.requireNonNull(deque.pollFirst())));
            }
        }
    }

    private void sendAlert(String vavName, Date time) {
        Alert alert = new Alert();
        alert.setTime(time);
        alert.setVavName(vavName);
        alertMapper.insert(alert);
    }

    /**
     * Get the range of the vav temperature in two set points
     * @param vav
     * @return 1: over cooling set point
     *         0: between two set points
     *         -1: below heating set point
     */
    private int valueRange(Vav vav) {
        if(vav.getZoneTemperature() != null){
            double temp = vav.getZoneTemperature();
            double heatingTemperatureSetPoint = vav.getZoneHeatingTemperatureSetPoint();
            double coolingTemperatureSetPoint = vav.getZoneCoolingTemperatureSetPoint();
            if(temp > coolingTemperatureSetPoint){
                return 1;
            }else if(temp<heatingTemperatureSetPoint){
                return  -1;
            }

        }
        return 0;
    }

    private boolean largeThanOneHour(Vav first, Vav last) {
        return Math.abs(first.getCommon().getTime().getTime() - last.getCommon().getTime().getTime()) > ONE_HOUR;
    }

    private void getAllVav(){
        List<String> table = tableUtilService.getAllVavTable();
        vavSet.addAll(table);

        for(String vavName:table){
            if(tableUtilH2Mapper.existTable(vavName) == 0){
                tableUtilH2Mapper.createNewVavAlertTable(vavName);
            }
        }

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

    public void setTableUtilH2Mapper(TableUtilH2Mapper tableUtilH2Mapper) {
        this.tableUtilH2Mapper = tableUtilH2Mapper;
    }
}
