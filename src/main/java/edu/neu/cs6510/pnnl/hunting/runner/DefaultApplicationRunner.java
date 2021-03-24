package edu.neu.cs6510.pnnl.hunting.runner;

import edu.neu.cs6510.pnnl.hunting.mapper.VavMapper;
import edu.neu.cs6510.pnnl.hunting.model.Vav;
import edu.neu.cs6510.pnnl.hunting.service.TableUtilService;
import edu.neu.cs6510.pnnl.hunting.service.VavService;
import edu.neu.cs6510.pnnl.hunting.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.core.Ordered;

import javax.annotation.Resource;
import java.util.*;

@Component
public class DefaultApplicationRunner implements ApplicationRunner, Ordered {

    @Autowired
    VavService vavService;

    @Autowired
    TableUtilService tableUtilService;

    HashMap<String,List<Vav>> vavToday = new HashMap<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Date now = new Date(1543618200000L);
        if(DateUtil.isWorkHour(now)){
            Date startTime = DateUtil.getWorkHourStartTime(now);
            Date endTime = DateUtil.getWorkHourEndTime(now);
            List<String> allVavTable = tableUtilService.getAllVavTable();
            for(String vavTable:allVavTable){
                vavToday.put(vavTable,vavService.getVavInRange(startTime, endTime, vavTable));
            }
        }else {

        }
        System.out.println("Done!");
    }

    private boolean isWorkHour(Date date) {
        Calendar c = Calendar.getInstance();
        int week = c.get(Calendar.DAY_OF_WEEK);

        return false;
    }

    @Override
    public int getOrder() {
        return 1000;
    }
}
