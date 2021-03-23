package edu.neu.cs6510.pnnl.hunting.job;

import edu.neu.cs6510.pnnl.hunting.controller.HuntingController;
import edu.neu.cs6510.pnnl.hunting.h2mapper.VavH2Mapper;
import edu.neu.cs6510.pnnl.hunting.model.Vav;
import edu.neu.cs6510.pnnl.hunting.service.VavService;
import edu.neu.cs6510.pnnl.hunting.utils.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class HuntingJob extends QuartzJobBean {

    private String name;
    private VavService service;
    private VavH2Mapper mapper;

    public void setName(String name) {
        this.name = name;
    }

    public void setService(VavService service) {
        this.service = service;
    }

    public void setMapper(VavH2Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // TODO Check the anomaly data here, add them into H2 Database
        List<Vav> vavList = service.getVavInRange(DateUtil.convertStringToDate("2018-11-30 17:09:00"), DateUtil.convertStringToDate("2018-11-30 17:11:00"), "vav100");
        for(Vav vav:vavList){
            mapper.insertSelective(vav);
        }
        System.out.println("Finish Check "+new Date());
    }




}
