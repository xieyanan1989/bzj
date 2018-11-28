package com.mzk.bzj.util.exception;

public class MZKException  extends Exception
{
	  private static final long serialVersionUID = 1L;
	  private String errorCode;
	  private String errorMsg;

	  public MZKException()
	  {
	  }

	  public MZKException(Throwable throwable)
	  {
	    super(throwable);
	  }

	  public MZKException(String msg) {
	    super(msg);
	    this.errorMsg = msg;
	  }

	  public MZKException(String msg, Throwable t) {
	    super(msg, t);
	    this.errorMsg = msg;
	  }

	  public MZKException(String errorCode, String errorMsg) {
	    super(errorMsg);
	    this.errorCode = errorCode;
	    this.errorMsg = errorMsg;
	  }

	  public MZKException(String errorCode, String errorMsg, Throwable t) {
	    super(errorMsg, t);
	    this.errorCode = errorCode;
	    this.errorMsg = errorMsg;
	  }

	  public String getErrorCode() {
	    return this.errorCode;
	  }

	  public void setErrorCode(String errorCode) {
	    this.errorCode = errorCode;
	  }

	  public String getErrorMsg() {
	    return this.errorMsg;
	  }

	  public void setErrorMsg(String errorMsg) {
	    this.errorMsg = errorMsg;
	  }

	  public Throwable fillInStackTrace()
	  {
	    return null;
	  }
	}