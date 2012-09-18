package com.cabletech.business.base.service;
 
import java.util.Map; 
 
import org.apache.log4j.Logger; 
/**
 * Service基类，结合Spring进行控制管理 <br />
 * 
 *  
 * 
 * 
 */
public class BaseServiceImpl implements BaseService {
	public Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Map<String, Object> queryPageMap(String mapId, Object parameter,
			int page, int pagesize) {
		 
		return null;
	}

	 
}
