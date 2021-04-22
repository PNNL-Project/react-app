package edu.neu.cs6510.pnnl.hunting.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import static edu.neu.cs6510.pnnl.hunting.utils.ConfigConst.*;
import static java.time.DayOfWeek.*;

public class DateUtil {

    final static Set<DayOfWeek> BUSINESS_DAYS = Set.of(
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
    );

    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static {
//        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
    }

    /**
     * Add any holiday here
     */
    final static Set<LocalDate> HOLIDAYS = Set.of(

    );

    private DateUtil(){

    }

   public static Date convertStringToDate(String dateString){
       Date date = null;
       try {
           date = DATE_FORMAT.parse(dateString);

       }catch (ParseException e){
           e.printStackTrace();
       }

       return date;
   }

   public static String getCurrentTimeString(){
       Date date = new Date();
       return DATE_FORMAT.format(date);
   }


   public static String convertDateToString(Date date){
       return DATE_FORMAT.format(date);
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

    public static Date getTimeWindowStartTime(Date date){
        return new Date(date.getTime() - HUNTING_TIME_WINDOW);
    }
    public static Date getWorkHourStartTime(Date date){
        return getWorkTime(date,WORK_HOUR_START_TIME);
    }

    public static Date getWorkHourEndTime(Date date){
        return getWorkTime(date,WORK_HOUR_END_TIME);
    }


    private static Date getWorkTime(Date date, int setHour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,setHour);
        return cal.getTime();
    }

    public static List<Date> convertLocalDateToPSTDate(List<LocalDate> localDates){
        List<Date> res = new LinkedList<>();
        ZoneId z = ZoneId.of("America/Los_Angeles");
        for(LocalDate localDate:localDates){
            ZonedDateTime zdt = localDate.atStartOfDay(z);
            Instant instant = zdt.toInstant();
            res.add(Date.from(instant));
        }
        return res;
    }

    public static Date[] getCurrentDayStartAndEnd(Date date){
        return new Date[]{DateUtil.getWorkHourStartTime(date),DateUtil.getWorkHourEndTime(date)};
    }

    public static String convertDateToPSTString(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return DATE_FORMAT.format(calendar.getTime());
    }
}
