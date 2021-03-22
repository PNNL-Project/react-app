package edu.neu.cs6510.pnnl.hunting.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.*;

public class DateUtil {

    final static Set<DayOfWeek> BUSINESS_DAYS = Set.of(
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
    );

    /**
     * Add any holiday here
     */
    final static Set<LocalDate> HOLIDAYS = Set.of(

    );

   private DateUtil(){}

   public static Date convertStringToDate(String dateString){
       Date date = null;
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       try {
           date = dateFormat.parse(dateString);

       }catch (ParseException e){
           e.printStackTrace();
       }

       return date;
   }

   public static String getCurrentTimeString(){
       Date date = new Date();
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return dateFormat.format(date);
   }


   public static String convertDateToString(Date date){
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return dateFormat.format(date);
   }

    public static List<LocalDate> listAllBusinessDayInRange(LocalDate startDate, LocalDate endDate){
        return startDate.datesUntil(endDate.plusDays(1))
                .filter(t -> BUSINESS_DAYS.contains(t.getDayOfWeek()))
                .filter(t -> !HOLIDAYS.contains(t))
                .collect(Collectors.toList());
    }

    public static List<LocalDate> listCurrentWeekBusinessDay(LocalDate today){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        // The default first day is Sunday
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        LocalDate startDate = today.plusDays(-dayOfWeek);
        return listAllBusinessDayInRange(startDate,today);
    }

}
