package com.mzk.bzj.util.aspect;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mzk.bzj.util.date.DateFormatUtil;
import com.mzk.bzj.util.log4j.Log4jCache;
import com.mzk.bzj.util.log4j.LogUtil;

@Aspect
@Component
public class LogAspect {
	
	 @Pointcut("execution(public * com.mzk.bzj.control..*.*(..))")
	    public void webLog(){}
	 	private int i = 10000000;
	    @Before("webLog()")
	    public void doBefore(JoinPoint joinPoint) throws Throwable {
	        // 接收到请求，记录请求内容
	        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	        HttpServletRequest request = attributes.getRequest();
	        Map<String, String[]>  reqMap = request.getParameterMap();
	        Map<String,String> map = new HashMap<String, String>();
	        for (Map.Entry<String, String[]> entry : reqMap.entrySet()) { 
//	        	System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
	        	map.put( entry.getKey(),entry.getValue()[0]);
	        }
	        
	        Log4jCache.setF(""+(i++));
	        Log4jCache.setTime(System.currentTimeMillis());
	        LogUtil.info(new Object[] { "------------------------交易开始------------------------" });
	        LogUtil.info(new Object[] { "开始时间:", DateFormatUtil.formatDate(new Date(), DateFormatUtil.yyyyMMddHHmmss19) });
	        LogUtil.info(new Object[] { "-->服务请求参数:", map.toString() });
	        LogUtil.info(new Object[] { "请求地址:", request.getRemoteAddr()});
	        LogUtil.info(new Object[] { "请求服务:", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() });
	    }

	    @AfterReturning(returning = "ret", pointcut = "webLog()")
	    public void doAfterReturning(Object ret) throws Throwable {
	        // 处理完请求，返回内容
	        LogUtil.info(new Object[] { "-->服务响应参数:",ret.toString() });
	        LogUtil.info(new Object[] {  "; 耗时:[", System.currentTimeMillis()-Log4jCache.getTime(), "]ms." });
	        LogUtil.info(new Object[] { "------------------------交易结束------------------------" });
	        Log4jCache.remove();
	    }
}
