package com.mzk.bzj.util.log4j;


import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class ExDailyRollingFileAppender extends FileAppender
{
	  static final int TOP_OF_TROUBLE = -1;
	  static final int TOP_OF_MINUTE = 0;
	  static final int TOP_OF_HOUR = 1;
	  static final int HALF_DAY = 2;
	  static final int TOP_OF_DAY = 3;
	  static final int TOP_OF_WEEK = 4;
	  static final int TOP_OF_MONTH = 5;
	  protected long maxFileSize = 10485760L;

	  protected int maxBackupIndex = 1;

	  private String datePattern = "'.'yyyy-MM-dd";
	  private String scheduledFilename;
	  private long nextCheck = System.currentTimeMillis() - 1L;
	  private String oldFilename;
	  Date now = new Date();
	  SimpleDateFormat sdf;
	  RollingCalendar rc = new RollingCalendar();

	  int checkPeriod = -1;

	  static final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");

	  public ExDailyRollingFileAppender()
	  {
	  }

	  public ExDailyRollingFileAppender(Layout layout, String filename, String datePattern)
	    throws IOException
	  {
	    super(layout, filename, true);
	    this.datePattern = datePattern;
	    activateOptions();
	  }

	  public long getMaximumFileSize()
	  {
	    return this.maxFileSize;
	  }

	  public void setMaximumFileSize(long maxFileSize)
	  {
	    this.maxFileSize = maxFileSize;
	  }

	  public void setMaxFileSize(String value)
	  {
	    this.maxFileSize = OptionConverter.toFileSize(value, this.maxFileSize + 1L);
	  }

	  public int getMaxBackupIndex()
	  {
	    return this.maxBackupIndex;
	  }

	  public void setMaxBackupIndex(int maxBackups)
	  {
	    this.maxBackupIndex = maxBackups;
	  }

	  public void setDatePattern(String pattern)
	  {
	    this.datePattern = pattern;
	  }

	  public String getDatePattern()
	  {
	    return this.datePattern;
	  }

	  public void activateOptions() {
	    if ((this.datePattern != null) && (this.fileName != null)) {
	      this.oldFilename = this.fileName;
	      this.fileName = settleFileName(this.fileName);
	      super.activateOptions();
	      this.now.setTime(System.currentTimeMillis());
	      this.sdf = new SimpleDateFormat(this.datePattern);
	      int type = computeCheckPeriod();
	      printPeriodicity(type);
	      this.rc.setType(type);
	      File file = new File(this.fileName);
	      this.scheduledFilename = 
	        (this.fileName + this.sdf
	        .format(new Date(file
	        .lastModified())));
	    }
	    else {
	      LogLog.error("Either File or DatePattern options are not set for appender [" + this.name + "].");
	    }
	  }

	  private String settleFileName(String fileName)
	  {
	    if (fileName.indexOf("%d") > 0)
	    {
	      String dateFormat = fileName.substring(fileName.indexOf("{") + 1, fileName.indexOf("}"));
	      SimpleDateFormat format = new SimpleDateFormat(dateFormat);
	      fileName = fileName.substring(0, fileName.indexOf("%d")) + format.format(new Date()) + fileName.substring(fileName.indexOf("}") + 1);
	    }

	    return fileName;
	  }

	  void printPeriodicity(int type) {
	    switch (type) {
	    case 0:
	      LogLog.debug("Appender [" + this.name + "] to be rolled every minute.");
	      break;
	    case 1:
	      LogLog.debug("Appender [" + this.name + "] to be rolled on top of every hour.");

	      break;
	    case 2:
	      LogLog.debug("Appender [" + this.name + "] to be rolled at midday and midnight.");

	      break;
	    case 3:
	      LogLog.debug("Appender [" + this.name + "] to be rolled at midnight.");
	      break;
	    case 4:
	      LogLog.debug("Appender [" + this.name + "] to be rolled at start of week.");

	      break;
	    case 5:
	      LogLog.debug("Appender [" + this.name + "] to be rolled at start of every month.");

	      break;
	    default:
	      LogLog.warn("Unknown periodicity for appender [" + this.name + "].");
	    }
	  }

	  int computeCheckPeriod()
	  {
	    RollingCalendar rollingCalendar = new RollingCalendar(gmtTimeZone, Locale.ENGLISH);

	    Date epoch = new Date(0L);
	    if (this.datePattern != null) {
	      for (int i = 0; i <= 5; i++) {
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);

	        simpleDateFormat.setTimeZone(gmtTimeZone);

	        String r0 = simpleDateFormat.format(epoch);
	        rollingCalendar.setType(i);
	        Date next = new Date(rollingCalendar.getNextCheckMillis(epoch));
	        String r1 = simpleDateFormat.format(next);

	        if ((r0 != null) && (r1 != null) && (!r0.equals(r1))) {
	          return i;
	        }
	      }
	    }
	    return -1;
	  }

	  public void sizeRollOver()
	  {
	    this.fileName = settleFileName(this.oldFilename);

	    LogLog.debug("rolling over count=" + ((CountingQuietWriter)this.qw)
	      .getCount());
	    LogLog.debug("maxBackupIndex=" + this.maxBackupIndex);

	    String datedFilename = this.fileName + this.sdf.format(this.now);

	    if (this.maxBackupIndex > 0)
	    {
	      File file = new File(datedFilename + '.' + this.maxBackupIndex);
	      if (file.exists()) {
	        file.delete();
	      }

	      for (int i = this.maxBackupIndex - 1; i >= 1; i--) {
	        file = new File(datedFilename + "." + i);
	        if (file.exists()) {
	          File target = new File(datedFilename + '.' + (i + 1));
	          LogLog.debug("Renaming file " + file + " to " + target);
	          file.renameTo(target);
	        }

	      }

	      File target = new File(datedFilename + "." + 1);

	      closeFile();

	      file = new File(this.fileName);
	      LogLog.debug("Renaming file " + file + " to " + target);
	      file.renameTo(target);
	    } else if (this.maxBackupIndex < 0)
	    {
	      for (int i = 1; i < 2147483647; i++) {
	        File target = new File(datedFilename + "." + i);
	        if (!target.exists()) {
	          closeFile();
	          File file = new File(this.fileName);
	          file.renameTo(target);
	          LogLog.debug("Renaming file " + file + " to " + target);
	          break;
	        }
	      }

	    }

	    try
	    {
	      setFile(this.fileName, false, this.bufferedIO, this.bufferSize);
	    } catch (IOException e) {
	      LogLog.error("setFile(" + this.fileName + ", false) call failed.", e);
	    }
	    this.scheduledFilename = datedFilename;
	  }

	  public synchronized void setFile(String fileName, boolean append, boolean bufferedIO, int bufferSize) throws IOException
	  {
	    super.setFile(fileName, append, this.bufferedIO, this.bufferSize);
	    if (append) {
	      File f = new File(fileName);
	      ((CountingQuietWriter)this.qw).setCount(f.length());
	    }
	  }

	  protected void setQWForFiles(Writer writer) {
	    this.qw = new CountingQuietWriter(writer, this.errorHandler);
	  }

	  void timeRollOver()
	    throws IOException
	  {
	    this.fileName = settleFileName(this.oldFilename);

	    if (this.datePattern == null) {
	      this.errorHandler.error("Missing DatePattern option in rollOver().");
	      return;
	    }

	    String datedFilename = this.fileName + this.sdf.format(this.now);

	    if (this.scheduledFilename.equals(datedFilename)) {
	      return;
	    }

	    closeFile();

	    File target = new File(this.scheduledFilename);
	    if (target.exists()) {
	      target.delete();
	    }

	    File file = new File(this.fileName);
	    boolean result = file.renameTo(target);
	    if (result)
	      LogLog.debug(this.fileName + " -> " + this.scheduledFilename);
	    else {
	      LogLog.error("Failed to rename [" + this.fileName + "] to [" + this.scheduledFilename + "].");
	    }

	    try
	    {
	      super.setFile(this.fileName, false, this.bufferedIO, this.bufferSize);
	    } catch (IOException e) {
	      this.errorHandler.error("setFile(" + this.fileName + ", false) call failed.");
	    }
	    this.scheduledFilename = datedFilename;
	  }

	  protected void subAppend(LoggingEvent event)
	  {
	    long n = System.currentTimeMillis();

	    if (n >= this.nextCheck) {
	      this.now.setTime(n);
	      this.nextCheck = this.rc.getNextCheckMillis(this.now);
	      try {
	        timeRollOver();
	      } catch (IOException ioe) {
	        LogLog.error("rollOver() failed.", ioe);
	      }
	    } else if ((this.fileName != null) && 
	      (((CountingQuietWriter)this.qw)
	      .getCount() >= this.maxFileSize)) {
	      sizeRollOver();
	    }
	    super.subAppend(event);
	  }
	}
