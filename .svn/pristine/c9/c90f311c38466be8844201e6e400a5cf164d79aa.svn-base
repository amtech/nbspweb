package com.cabletech.business.webservice.interfaces;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 外部WEBSERVICE接口
 * 
 * @author 杨隽 2012-08-16 创建
 * 
 */
@WebService
public interface ExternalWebService {
	/**
	 * 
	 */
	public static final String SUCCESS_CODE = "0";
	/**
	 * 
	 */
	public static final Object EXCEPTION_CODE = "-1";
	/**
	 * 
	 */
	public static final String NO_USER_ERROR_CODE = "10";
	/**
	 * 
	 */
	public static final String PASSWORD_ERROR_CODE = "11";
	/**
	 * 
	 */
	public static final String NO_POWER_ERROR_CODE = "14";
	/**
	 * 
	 */
	public static final String NO_WAIT_HANDLED_LIST_CODE = "20";
	/**
	 * 
	 */
	public static final String NO_DATA_LIST_CODE = "50";
	/**
	 * 
	 */
	public static final Object NO_CMD_ERROR_CODE = "80";
	/**
	 * 
	 */
	public static final String PARAMETER_ERROR_CODE = "81";
	/**
	 * 
	 */
	public static final String PARSE_ERROR_CODE = "90";
	
	/**
	 * 调用接口服务
	 * 
	 * @param xmlText
	 *            String 请求XML内容
	 * @return String 服务响应内容
	 */
	public String invokeService(@WebParam(name = "xmlText") String xmlText);
}
