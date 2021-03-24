package edu.neu.cs6510.pnnl.hunting.job;

import edu.neu.cs6510.pnnl.hunting.h2mapper.UpdateInfoMapper;
import edu.neu.cs6510.pnnl.hunting.h2mapper.VavH2Mapper;
import edu.neu.cs6510.pnnl.hunting.model.UpdateInfo;
import edu.neu.cs6510.pnnl.hunting.model.Vav;
import edu.neu.cs6510.pnnl.hunting.service.TableUtilService;
import edu.neu.cs6510.pnnl.hunting.service.VavService;
import edu.neu.cs6510.pnnl.hunting.utils.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HuntingJob extends QuartzJobBean {


    private VavService vavService;
    private VavH2Mapper vavH2Mapper;
    private UpdateInfoMapper updateInfoMapper;
    private TableUtilService tableUtilService;
    private final Set<String> vavSet = new HashSet<>();
    private final Set<String> vavThresholdSet = new HashSet<>();
    // 2 years 4months 21days  12:38:11
    private long TIME_SHIFT = 72707891000L;
    private long ONE_HOUR = 3600000L;
    private int WARNING = 6;




    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // TODO Check the anomaly data here, add them into H2 Database
        getAllVav();
        System.out.println("Get All Vav");
        getAllVavThreshold();
        System.out.println("Get All Vav Threshold");
        checkTemperature();
        System.out.println("Finish Check "+new Date());
    }


    private void checkTemperature(){

        Date now = new Date(System.currentTimeMillis() - TIME_SHIFT);
        for(String vavName:vavSet){
            // TODO load the latest update time from H2
            Date startTime = null;
            Date endTime = null;
            UpdateInfo updateInfo = updateInfoMapper.selectByPrimaryKey(vavName);
            if(updateInfo == null){
                // First time to query this VAV
                startTime = DateUtil.getWorkHourStartTime(now);
                endTime = now;
                UpdateInfo currentUpdateInfo = new UpdateInfo();
                currentUpdateInfo.setVavName(vavName);
                currentUpdateInfo.setTime(now);
                updateInfoMapper.insert(currentUpdateInfo);
            }else{
                startTime = updateInfo.getTime();
                endTime = now;
                updateInfo.setTime(now);
                // TODO update H2
                updateInfoMapper.updateByPrimaryKeySelective(updateInfo);
            }
            List<Vav> vavInRange = vavService.getVavInRange(startTime, endTime, vavName);
            Deque<Vav> deque = new LinkedList<>();
            for(Vav vav: vavInRange){
                if(isAnomaly(vav)) {
                    deque.add(vav);
                    while (!deque.isEmpty() && largeThanOneHour(deque.getFirst(), deque.getLast())) {
                        deque.pollFirst();
                    }
                    if (deque.size() >= WARNING) {
                        vavH2Mapper.insertSelective(vav);
                    }
                }
            }
        }

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
        return first.getCommon().getTime().getTime() - last.getCommon().getTime().getTime() > ONE_HOUR;
    }

    private void getAllVav(){
        List<String> table = tableUtilService.getAllVavTable();
        vavSet.addAll(table);
    }

    private void getAllVavThreshold(){
        List<String> table = tableUtilService.getAllVavThresholdTable();
        vavThresholdSet.addAll(table);
    }


    public void setVavService(VavService vavService) {
        this.vavService = vavService;
    }

    public void setTableUtilService(TableUtilService tableUtilService) {
        this.tableUtilService = tableUtilService;
    }

    public void setVavH2Mapper(VavH2Mapper vavH2Mapper) {
        this.vavH2Mapper = vavH2Mapper;
    }

    public void setUpdateInfoMapper(UpdateInfoMapper updateInfoMapper) {
        this.updateInfoMapper = updateInfoMapper;
    }


}
