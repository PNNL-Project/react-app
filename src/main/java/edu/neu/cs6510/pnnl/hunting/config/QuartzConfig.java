package edu.neu.cs6510.pnnl.hunting.config;

import edu.neu.cs6510.pnnl.hunting.h2mapper.*;
import edu.neu.cs6510.pnnl.hunting.job.HuntingJob;
import edu.neu.cs6510.pnnl.hunting.service.TableUtilService;
import edu.neu.cs6510.pnnl.hunting.service.VavService;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Objects;

@Configuration
public class QuartzConfig {


//    private static final String CRON_EXPRESSION = "0/30 * * ? * * *";
//    private static final String CRON_EXPRESSION = " 0/45 * 6-20 ? * MON,TUE,WED,THU,FRI *";
    // Every 15 minutes
    private static final String CRON_EXPRESSION = " 0 */15 * ? * *";
//    private static final String CRON_EXPRESSION = "0 42 11 ? * * *";
    @Autowired
    VavService vavService;

    @Autowired
    VavAlertMapper vavAlertMapper;

    @Autowired
    TableUtilService tableUtilService;

    @Autowired
    TableUtilH2Mapper tableUtilH2Mapper;

    @Autowired
    UpdateInfoMapper updateInfoMapper;

    @Autowired
    AlertMapper alertMapper;

    @Bean
    JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(HuntingJob.class);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("vavService",vavService);
        jobDataMap.put("tableUtilService",tableUtilService);
        jobDataMap.put("updateInfoMapper",updateInfoMapper);
        jobDataMap.put("alertMapper",alertMapper);
        jobDataMap.put("vavAlertMapper",vavAlertMapper);
        jobDataMap.put("tableUtilH2Mapper",tableUtilH2Mapper);
        bean.setJobDataMap(jobDataMap);
        bean.setDurability(true);
        return bean;
    }

    @Bean
    CronTriggerFactoryBean cronTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(Objects.requireNonNull(jobDetail().getObject()));
        bean.setCronExpression(CRON_EXPRESSION);
        return bean;
    }

    @Bean
    SchedulerFactoryBean schedulerFactory() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        CronTrigger cronTrigger = cronTrigger().getObject();
        bean.setTriggers(cronTrigger);
        return bean;
    }

}
