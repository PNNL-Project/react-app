package edu.neu.cs6510.pnnl.hunting.controller;


import edu.neu.cs6510.pnnl.hunting.h2mapper.VavH2Mapper;
import edu.neu.cs6510.pnnl.hunting.job.HuntingJob;
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

import java.util.LinkedList;
import java.util.List;

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
    VavH2Mapper h2Mapper;

    @GetMapping("/today")
    public R todayHunting(){
        List<Vav> allVav = h2Mapper.getAllVav();
        List<VavAlert> allRes = new LinkedList<>();
        for(Vav vav:allVav){
            allRes.add(new VavAlert(vav));
        }
        return R.ok(allRes.toString());
    }

    @GetMapping("/yesterday")
    public R yesterdayHunting(){
        Vav vav = vavService.selectByPrimaryKey(1);
        System.out.println(vav);
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
}
