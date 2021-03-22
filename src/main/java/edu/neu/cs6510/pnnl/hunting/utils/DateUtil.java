package edu.neu.cs6510.pnnl.hunting.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

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


}
