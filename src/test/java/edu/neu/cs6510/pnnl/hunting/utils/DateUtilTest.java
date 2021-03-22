package edu.neu.cs6510.pnnl.hunting.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class DateUtilTest {

    @Test
    public void testCalender(){
        Calendar c = Calendar.getInstance();
        Date date = DateUtil.convertStringToDate("2018-11-30 17:09:00");
        c.setTime(date);
        int week = c.get(Calendar.DAY_OF_WEEK);

        System.out.println(week);
    }

    @Test
    void listAllBusinessDay() {

    }

    @Test
    void convertStringToDate() {
    }

    @Test
    void getCurrentTimeString() {
    }

    @Test
    void convertDateToString() {
    }

    @Test
    void listAllBusinessDayInRange() {
        LocalDate startDate = LocalDate.of(2012, 3, 7);
        LocalDate endDate = LocalDate.of(2012, 6, 7);
        List<LocalDate> dates = DateUtil.listAllBusinessDayInRange(startDate, endDate);
        for (LocalDate date:dates){
            System.out.println(date);
        }
    }

    @Test
    void listCurrentWeekBusinessDay() {
//        LocalDate today  = LocalDate.now();
        LocalDate today  = LocalDate.of(2021,3,27);

        List<LocalDate> dates = DateUtil.listCurrentWeekBusinessDay(today);
        for (LocalDate date:dates){
            System.out.println(date);
        }
    }
}