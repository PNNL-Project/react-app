package edu.neu.cs6510.pnnl.hunting.config;

import edu.neu.cs6510.pnnl.hunting.h2mapper.VavH2Mapper;
import edu.neu.cs6510.pnnl.hunting.job.HuntingJob;
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


    private static final String CRON_EXPRESSION = "0/5 * * ? * * *";
    @Autowired
    VavService vavService;

    @Autowired
    VavH2Mapper h2Mapper;

    @Bean
    JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(HuntingJob.class);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("service",vavService);
        jobDataMap.put("mapper",h2Mapper);
        jobDataMap.put("name","test");
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
