/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename BeforeExample.java
 * @package com.ratan.framework.aop
 * @author dandyzheng
 * @date 2012-6-7
 */
package cn.ttsales.work.core.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;



/**
 * @author dandyzheng
 * 
 */
@Component
@Aspect
public class Beforehandler{
	Logger log = Logger.getLogger(Beforehandler.class);

	@Before("execution (* cn.ttsales.work.service.*.*.*.*(..))")
	public void before(JoinPoint joinpoint) {
		log.info("开始执行:"+joinpoint.getStaticPart());
	}

}
