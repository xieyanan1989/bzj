package com.mzk.bzj.util.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil
{
  private static final Logger LOG = LoggerFactory.getLogger(LogUtil.class);

  public static void info(Object[] msg)
  {
    StringBuffer sb = new StringBuffer();
    for (Object temp : msg) {
      if (temp != null) {
        sb.append(temp.toString());
      }
    }
    if (LOG.isInfoEnabled()) {
      LOG.info(sb.toString());
    }
    sb = null;
  }

  public static void warn(Object[] msg)
  {
    StringBuffer sb = new StringBuffer();
    for (Object temp : msg) {
      if (temp != null) {
        sb.append(temp.toString());
      }
    }
    LOG.warn(sb.toString());
    sb = null;
  }

  public static void error(Object[] msg)
  {
    StringBuffer sb = new StringBuffer();
    for (Object temp : msg) {
      if (temp != null) {
        sb.append(temp.toString());
      }
    }
    LOG.error(sb.toString());
    sb = null;
  }

  public static void error(Throwable e, Object[] msg)
  {
    StringBuffer sb = new StringBuffer();
    for (Object temp : msg) {
      if (temp != null) {
        sb.append(temp.toString());
      }
    }
    LOG.error(sb.toString(), e);
    sb = null;
  }

  public static void debug(Object[] msg)
  {
    StringBuffer sb = new StringBuffer();
    for (Object temp : msg) {
      if (temp != null) {
        sb.append(temp.toString());
      }
    }
    LOG.debug(sb.toString());
    sb = null;
  }
}