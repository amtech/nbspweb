package com.cabletech.common.util;

/**
 * 定义ServiceException 类
 * @author wangt
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	/**
	 * 构造方法1
	 */
	public ServiceException() {
		super();
	}

	/**
	 * 构造方法2
	 * @param message 
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * 构造方法3
	 * @param cause 
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造方法4
	 * @param message 
	 * @param cause 
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
