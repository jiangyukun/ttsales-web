/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename BusinessException.java
 * @package com.ratan.exception
 * @author dandyzheng
 * @date 2012-6-7
 */
package cn.ttsales.work.core.exception;

/**
 * @author dandyzheng
 * 
 */
public class BusinessException extends BaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param errorCode
	 */
	public BusinessException(String errorCode) {
		super(null, null, errorCode, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param errorCode
	 * @param parameters
	 */
	public BusinessException(String errorCode, Object[] parameters) {
		super(null, null, errorCode, parameters, null);
	}

	/**
	 * Constructor
	 * 
	 * @param errorCode
	 * @param e
	 */
	public BusinessException(String errorCode, Throwable e) {
		super(null, null, errorCode, null, e);
	}

	/**
	 * Constructor
	 * 
	 * @param errorCode
	 * @param parameters
	 * @param e
	 */
	public BusinessException(String errorCode, Object[] parameters, Throwable e) {
		super(null, null, errorCode, parameters, e);
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorCode
	 */
	public BusinessException(String errorMsg, String errorCode) {
		super(errorMsg, null, errorCode, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorCode
	 * @param parameters
	 */
	public BusinessException(String errorMsg, String errorCode,
			Object[] parameters) {
		super(errorMsg, null, errorCode, parameters, null);
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorCode
	 * @param parameters
	 * @param e
	 */
	public BusinessException(String errorMsg, String errorCode,
			Object[] parameters, Throwable e) {
		super(errorMsg, null, errorCode, parameters, e);
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorDetail
	 * @param errorCode
	 */
	public BusinessException(String errorMsg, String errorDetail,
			String errorCode) {
		super(errorMsg, errorDetail, errorCode, null, null);
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorDetail
	 * @param errorCode
	 * @param e
	 */
	public BusinessException(String errorMsg, String errorDetail,
			String errorCode, Throwable e) {
		super(errorMsg, errorDetail, errorCode, null, e);
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorDetail
	 * @param errorCode
	 * @param parameters
	 */
	public BusinessException(String errorMsg, String errorDetail,
			String errorCode, Object[] parameters) {
		super(errorMsg, errorDetail, errorCode, parameters, null);
	}

	/**
	 * Constructor
	 * 
	 * @param errorMsg
	 * @param errorDetail
	 * @param errorCode
	 * @param parameters
	 * @param e
	 */
	public BusinessException(String errorMsg, String errorDetail,
			String errorCode, Object[] parameters, Throwable e) {
		super(errorMsg, errorDetail, errorCode, parameters, e);
	}
}
