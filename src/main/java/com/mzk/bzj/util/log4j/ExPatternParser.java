package com.mzk.bzj.util.log4j;
import java.util.Map;
import org.apache.log4j.helpers.FormattingInfo;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;

public class ExPatternParser extends PatternParser
{
	  public ExPatternParser(String pattern)
	  {
	    super(pattern);
	  }

	  protected void finalizeConverter(char c)
	  {
	    if (c == 'T')
	      addConverter(new ExPatternConverter(this.formattingInfo));
	    else if (c == 'F')
	      addConverter(new ExFlowPatternConverter(this.formattingInfo));
	    else
	      super.finalizeConverter(c);
	  }

	  private static class ExFlowPatternConverter extends PatternConverter
	  {
	    public ExFlowPatternConverter(FormattingInfo fi)
	    {
	      super();
	    }

	    protected String convert(LoggingEvent event)
	    {
	      String flowno = (String)((Map)Log4jCache.cacheMap.get()).get("flowno");
	      if (flowno == null) {
	        flowno = "";
	      }
	      return flowno;
	    }
	  }

	  private static class ExPatternConverter extends PatternConverter
	  {
	    public ExPatternConverter(FormattingInfo fi)
	    {
	      super();
	    }

	    protected String convert(LoggingEvent event)
	    {
	      return String.valueOf(Thread.currentThread().getId());
	    }
	  }
	}