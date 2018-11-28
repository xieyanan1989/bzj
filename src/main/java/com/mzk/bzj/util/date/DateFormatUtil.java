package com.mzk.bzj.util.date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil
{
  public static ThreadLocal<DateFormat> yyyyMMdd = new ThreadLocal()
  {
    protected synchronized DateFormat initialValue() {
      return new SimpleDateFormat("yyyyMMdd");
    }
  };

  public static ThreadLocal<DateFormat> yyyyMM = new ThreadLocal()
  {
    protected synchronized DateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM");
    }
  };

  public static ThreadLocal<DateFormat> yyyyMMddLine = new ThreadLocal()
  {
    protected synchronized DateFormat initialValue() {
      return new SimpleDateFormat("yyyy/MM/dd");
    }
  };

  public static ThreadLocal<DateFormat> yyyyMMdd10 = new ThreadLocal()
  {
    protected synchronized DateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd");
    }
  };

  public static ThreadLocal<DateFormat> YYMMDD = new ThreadLocal()
  {
    protected synchronized DateFormat initialValue() {
      return new SimpleDateFormat("yyMMdd");
    }
  };

  public static ThreadLocal<DateFormat> HHmmss = new ThreadLocal()
  {
    protected synchronized DateFormat initialValue() {
      return new SimpleDateFormat("HHmmss");
    }
  };

  public static ThreadLocal<DateFormat> HHmmssSSS = new ThreadLocal()
  {
    protected synchronized DateFormat initialValue() {
      return new SimpleDateFormat("HHmmssSSS");
    }
  };

  public static ThreadLocal<DateFormat> yyyyMMddHHmmss14 = new ThreadLocal()
  {
    protected synchronized DateFormat initialValue() {
      return new SimpleDateFormat("yyyyMMddHHmmss");
    }
  };

  public static ThreadLocal<DateFormat> yyyyMMddHHmmss19 = new ThreadLocal()
  {
    protected synchronized DateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
  };

  public static ThreadLocal<DateFormat> TIME_ZONE = new ThreadLocal()
  {
    protected synchronized DateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'+08:00'");
    }
  };

  public static String formatDate(Date date, ThreadLocal<DateFormat> dateFormat)
  {
    return ((DateFormat)dateFormat.get()).format(date);
  }
}