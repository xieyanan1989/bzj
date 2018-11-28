package com.mzk.bzj.util.log4j;

import java.util.HashMap;
import java.util.Map;

public class Log4jCache
{
  public static final String FLOWNO = "flowno";
  public static ThreadLocal<Map<String, String>> cacheMap = new ThreadLocal()
  {
    protected synchronized Map<String, String> initialValue() {
      return new HashMap();
    }
  };

  public static void setF(String flowno)
  {
    ((Map)cacheMap.get()).put("flowno", flowno);
  }
  
  public static String getF()
  {
    String flowno = (String)((Map)cacheMap.get()).get("flowno");

    if (null == flowno) {
      return "";
    }
    return (String)((Map)cacheMap.get()).get("flowno");
  }

  public static Long getTime()
  {
	  Long time = (Long) ((Map)cacheMap.get()).get("time");

    if (time <= 0) {
      return System.currentTimeMillis();
    }
    return (Long)((Map)cacheMap.get()).get("time");
  }
  
  public static void setTime(long time)
  {
    ((Map)cacheMap.get()).put("time", time);
  }
  public static void remove(){
	  ((Map)cacheMap.get()).remove("time");
	  ((Map)cacheMap.get()).remove("flowno");
  }
}
