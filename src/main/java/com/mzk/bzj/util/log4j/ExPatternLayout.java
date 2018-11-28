package com.mzk.bzj.util.log4j;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;
public class ExPatternLayout extends PatternLayout
{
  public ExPatternLayout(String pattern)
  {
    super(pattern);
  }

  public ExPatternLayout()
  {
  }

  protected PatternParser createPatternParser(String pattern)
  {
    return new ExPatternParser(pattern);
  }
}