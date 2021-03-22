package edu.neu.cs6510.pnnl.hunting.runner;

import edu.neu.cs6510.pnnl.hunting.service.VavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.core.Ordered;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Component
public class DefaultApplicationRunner implements ApplicationRunner, Ordered {

    @Autowired
    VavService vavService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Date date = new Date();
        if(isWorkHour(date)){

        }else {

        }
    }

    private boolean isWorkHour(Date date) {
        Calendar c = Calendar.getInstance();
        int week = c.get(Calendar.DAY_OF_WEEK);

        return false;
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
