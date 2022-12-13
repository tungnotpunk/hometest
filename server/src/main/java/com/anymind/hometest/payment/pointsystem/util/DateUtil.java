package com.anymind.hometest.payment.pointsystem.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.ParseException;

public class DateUtil {

  public static Date datetimeToDate(String datetimeWithTimezone) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
    return sdf.parse(datetimeWithTimezone);
  }

  public static String dateToDatetime(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
    return sdf.format(date);
  }
}