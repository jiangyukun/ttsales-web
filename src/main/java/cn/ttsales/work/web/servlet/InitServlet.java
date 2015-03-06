/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename InitServlet.java
 * @package ratan.bds.web
 * @author dandyzheng
 * @date 2012-8-17
 */
package cn.ttsales.work.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.ttsales.work.core.SASConstants;

/**
 * @author dandyzheng
 * 
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(InitServlet.class);
	
	
	public void init(ServletConfig config) throws ServletException {
		super.init();
		log.info("版本号："+SASConstants.SAS_VERSION);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
}
