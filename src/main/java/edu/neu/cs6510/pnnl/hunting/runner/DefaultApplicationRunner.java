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

    }

    @Override
    public int getOrder() {
        return 1000;
    }
}
