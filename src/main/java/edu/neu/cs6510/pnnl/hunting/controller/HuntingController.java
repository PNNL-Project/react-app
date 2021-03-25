package edu.neu.cs6510.pnnl.hunting.controller;


import edu.neu.cs6510.pnnl.hunting.h2mapper.AlertMapper;
import edu.neu.cs6510.pnnl.hunting.h2mapper.VavAlertMapper;
import edu.neu.cs6510.pnnl.hunting.job.HuntingJob;
import edu.neu.cs6510.pnnl.hunting.model.Alert;
import edu.neu.cs6510.pnnl.hunting.model.Vav;
import edu.neu.cs6510.pnnl.hunting.model.VavAlert;
import edu.neu.cs6510.pnnl.hunting.service.CommonService;
import edu.neu.cs6510.pnnl.hunting.service.VavService;
import edu.neu.cs6510.pnnl.hunting.service.VavThresholdsService;
import edu.neu.cs6510.pnnl.hunting.utils.DateUtil;
import edu.neu.cs6510.pnnl.hunting.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

@RestController
@RequestMapping("hunting/hunting")
public class HuntingController {

    @Autowired
    VavService vavService;

    @Autowired
    VavThresholdsService vavThresholdsService;

    @Autowired
    CommonService commonService;

    @Autowired
    HuntingJob huntingJob;

    @Autowired
    AlertMapper alertMapper;

    @Autowired
    VavAlertMapper vavAlertMapper;

    @GetMapping("/today")
    public R todayHunting(){
        HashMap<String,List<VavAlert>> response = new HashMap<>();
        List<Alert> allAlert = alertMapper.getAllAlert();
        for(Alert alert:allAlert){
            Date alertTime = alert.getTime();
            List<VavAlert> vavAlerts = vavAlertMapper.getVavAlertInRange(
                    getQueryDateString(DateUtil.getOneHourBeforeTime(alertTime)),
                    getQueryDateString(alertTime)
            );
            response.put(DateUtil.convertDateToString(alert.getTime()),new ArrayList<>(vavAlerts));
        }
        return R.ok(response.toString());
    }

    @GetMapping("/yesterday")
    public R yesterdayHunting(){
        return R.ok("Test");
    }

    @GetMapping("/test")
    public R specificHunting(HttpServletRequest request){
        String vavName=request.getParameter("vavName");
        String startTime=request.getParameter("StartTime");
        String endTime=  request.getParameter("EndTime") == null
                ? DateUtil.getCurrentTimeString()
                : request.getParameter("EndTime");
        List<Vav> vavList = vavService.getVavInRange(DateUtil.convertStringToDate(startTime), DateUtil.convertStringToDate(endTime), vavName);
        return R.ok(vavList.toString());
    }

    private String getQueryDateString(Date date) {
        return "'" + DateUtil.convertDateToString(date) + "'";
    }

}
