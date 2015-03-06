/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename AfterThrowExceptionHander.java
 * @package com.ratan.framework.aop
 * @author dandyzheng
 * @date 2012-6-7
 */
package cn.ttsales.work.core.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import cn.ttsales.work.core.exception.BusinessException;





/**
 * @author dandyzheng
 * 
 */
@Component
@Aspect
public class AfterThrowinghandler implements ThrowsAdvice {
	private Logger log = Logger.getLogger(AfterThrowinghandler.class);
	//
	@SuppressWarnings("unused")
	@AfterThrowing(pointcut = "execution(* cn.ttsales.work.*.*.*.*.*.*(..))", throwing = "ex")
	public void afterThrowing(Exception ex) {
		ex.printStackTrace();
		log.error(ex.getMessage());
		String errorCode = "RS-A1-000101";
		String msg = "系统出现未知异常，请联系管理员";
		if (ex instanceof BusinessException) {
			ex = (BusinessException) ex;
			errorCode = ((BusinessException) ex).getErrorCode();
			msg = ((BusinessException) ex).getMessage();
		}
		
		//BaseManageBean messageBean= new BaseManageBean();
		//messageBean.addErrorMessage(msg, msg);
	}

}
