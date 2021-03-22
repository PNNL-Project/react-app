package edu.neu.cs6510.pnnl.hunting.controller;


import edu.neu.cs6510.pnnl.hunting.model.Vav;
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
import java.util.Date;
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

    @GetMapping("/today")
    public R todayHunting(){

        return R.ok("Test");
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
