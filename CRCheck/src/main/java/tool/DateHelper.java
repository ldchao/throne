package tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zs on 2016/7/14.
 */
public class DateHelper{
    //日期计算相差多少天
    public static int daysBetween(String sdate,String bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(sdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }
    //测试
    public static void main(String[] args){
        int day=0;
        int day2=0;
        int day3=0;
        int day4=0;
        try {
            day=DateHelper.daysBetween("2015-02-23","2015-02-23");
            day2=DateHelper.daysBetween("2015-02-23","2015-02-24");
            day3=DateHelper.daysBetween("2016-02-23","2016-03-23");
            day4=DateHelper.daysBetween("2015-02-23","2016-02-24");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(day);
        System.out.println(day2);
        System.out.println(day3);
        System.out.println(day4);
    }

}
