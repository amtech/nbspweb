package com.cabletech.common.util;

import java.io.BufferedReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.sql.SQLException;
/**
 * Clob 工具类
 * @author wangt
 *
 */
public class ClobUtils {
	/**
	 * 将clob转化成String
	 * @param in 
	 * @return
	 * @throws ServiceException
	 */
	public static String ClobToString(Object in) throws ServiceException {
		String reString = "";
		oracle.sql.CLOB clob = null;
		try {
			if ("oracle.sql.CLOB".equals(in.getClass().getName())) {
				clob = (oracle.sql.CLOB) in;
			} else if ("weblogic.jdbc.wrapper.Clob_oracle_sql_CLOB".equals(in
					.getClass().getName())) {
				Method method = in.getClass().getMethod("getVendorObj",
						new Class[] {});
				clob = (oracle.sql.CLOB) method.invoke(in);
			}
			Reader is = clob.getCharacterStream();// 得到流
			BufferedReader br = new BufferedReader(is);
			String s = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(s);
				s = br.readLine();
			}
			br.close();
			
			reString = sb.toString();
		} catch (SQLException e) {
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return reString;
	}
}