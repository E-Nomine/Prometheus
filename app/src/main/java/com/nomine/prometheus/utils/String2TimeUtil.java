package com.nomine.prometheus.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 用于将String转化为时间
 * Created by E Nomine on 16/3/29.
 */
public class String2TimeUtil {

    public static String dateString2GoodExperienceFormat(String time) {
        if (isNullString(time)) {
            return "";
        } else {
            SimpleDateFormat simpleDateFormat;
            if(time.length()>10){
                simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
            }
            try {
                String timeString;
                Date parse = simpleDateFormat.parse(time);
                long distanceTime = new Date().getTime() - parse.getTime();
                if (distanceTime < 0L) {
                    //timeString = "0 mins ago";
                    timeString = "0 分钟前";
                } else {
                    long dis = distanceTime / 60000L;
                    new SimpleDateFormat("HH:mm", Locale.US);
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM-dd", Locale.US);
                    if (dis < 60L) {
                        timeString = String.valueOf(dis) + " 分钟前";
                    } else if (dis < 720L) {
                        timeString = String.valueOf(dis / 60L) + " 小时前";
                    } else {
                        timeString = simpleDateFormat2.format(parse);
                    }
                }
                return timeString;
            } catch (Exception ex) {
                ex.printStackTrace();
                return "";
            }
        }
    }

	public static boolean isNullString(String s) {
		return s == null || s.equals("") || s.equals("null");
	}


}
