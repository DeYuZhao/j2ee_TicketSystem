package edu.nju.ticketsystem.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {
    private static DateTools dateTools = new DateTools();

    public static DateTools getInstance(){
        return dateTools;
    }

    public int getDateMinus(String startDate, String endDate){
        int startMonth = Integer.parseInt(startDate.substring(5,6))*10
                +Integer.parseInt(startDate.substring(6,7));
        int startDay = Integer.parseInt(startDate.substring(8,9))*10
                +Integer.parseInt(startDate.substring(9,10));
        int endMonth = Integer.parseInt(endDate.substring(5,6))*10
                +Integer.parseInt(endDate.substring(6,7));
        int endDay = Integer.parseInt(endDate.substring(8,9))*10
                +Integer.parseInt(endDate.substring(9,10));
        int result = (endMonth-startMonth)*30 + (endDay-startDay);
        return result;
    }

    public boolean judgeOutDate(String startDate, String endDate){
        int startMonth = Integer.parseInt(startDate.substring(5,6))*10 + Integer.parseInt(startDate.substring(6,7));
        int endMonth = Integer.parseInt(endDate.substring(5,6))*10 + Integer.parseInt(endDate.substring(6,7));
        int startDay = Integer.parseInt(startDate.substring(8,9))*10 + Integer.parseInt(startDate.substring(9,10));
        int endDay = Integer.parseInt(endDate.substring(8,9))*10 + Integer.parseInt(endDate.substring(9,10));
        int startHour = Integer.parseInt(startDate.substring(11,12))*10 + Integer.parseInt(startDate.substring(12,13));
        int endHour = Integer.parseInt(endDate.substring(11,12))*10 + Integer.parseInt(endDate.substring(12,13));
        int startMinute = Integer.parseInt(startDate.substring(14,15))*10 + Integer.parseInt(startDate.substring(15,16));
        if (startMonth > endMonth){
            return false;
        }else if (startMonth == endMonth){
            if (startDay > endDay){
                return false;
            }else if (startDay == endDay){
                if (startHour > endHour-1){
                    return false;
                }
            }
        }
        return true;
    }

    public String getWantedDate(int nums){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, nums);
        date = calendar.getTime();
        return sdf.format(date);
    }

}
