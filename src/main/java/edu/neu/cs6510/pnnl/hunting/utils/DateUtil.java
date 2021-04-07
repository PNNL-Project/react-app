package edu.neu.cs6510.pnnl.hunting.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import static edu.neu.cs6510.pnnl.hunting.utils.ConfigConst.*;
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

    public static boolean isWorkHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week = c.get(Calendar.DAY_OF_WEEK);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        return week >= 2 && week<=6 && hour>=6 && hour<18;
    }

    public static Date getOneHourBeforeTime(Date date){
        return new Date(date.getTime() - ONE_HOUR);
    }
    public static Date getWorkHourStartTime(Date date){
        return getWorkTime(date,WORK_HOUR_START_TIME);
    }

    public static Date getWorkHourEndTime(Date date){
        return getWorkTime(date,WORK_HOUR_END_TIME);
    }

    public static Date getAnyWorkHourTime(Date date, int hour){
        if(hour>=6 && hour<=18){
            return getWorkTime(date, hour +":00:00");
        }else {
            // FIXME add an exception or logging
            return null;
        }
    }

    private static Date getWorkTime(Date date, String hourString) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd");
        String timeStr = simpleDateFormat.format(date) + " " + hourString;
        Date workHourStart = null;
        try {
            workHourStart = dateFormat.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return workHourStart;
    }

    public static List<Date> convertLocalDateToPSTDate(List<LocalDate> localDates){
        List<Date> res = new LinkedList<>();
        for(LocalDate localDate:localDates){
            res.add(Date.from(localDate.atStartOfDay(ZoneId.of("America/Los_Angeles")).toInstant()));
        }
        return res;
    }

    public static Date[] getCurrentDayStartAndEnd(Date date){
        return new Date[]{DateUtil.getWorkHourStartTime(date),DateUtil.getWorkHourEndTime(date)};
    }
}
