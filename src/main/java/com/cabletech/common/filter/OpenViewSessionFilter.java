package com.cabletech.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

/**
 * 查看过滤
 * @author wangt
 *
 */
public class OpenViewSessionFilter implements Filter {
	private static Logger log = Logger.getLogger(OpenViewSessionFilter.class);
	protected String encoding = null;
	protected FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		encoding = filterConfig.getInitParameter("encoding");
	}
	@Override
	public void destroy() {
		encoding = null;
		filterConfig = null;
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		try {
			// Set Character Encoding
			//request.setCharacterEncoding(encoding);
			chain.doFilter(request, response);
		} finally {
			//log.info("测试jdbc_oracle.properties路径配置不正确导致的HibernateSession无法初始化文件:   "+HibernateSession.test);
			// commit transaction and close hibernate session
			try {
			//	HibernateSession.currentSession().connection().commit();
				//log.info("开始事务");
			//	HibernateSession.commitTransaction();
			} catch (Exception e) {
				try {
			//		HibernateSession.currentSession().connection().rollback();
			//		HibernateSession.rollbackTransaction();
					//log.info("事务回滚");
				} catch (Exception e1) {
				}
				log.error(e);
			} finally {
				try {
					// close Hibernate Session
				//	HibernateSession.closeSession();
					//log.info("关闭事务");
				} catch (HibernateException e) {
					log.error(e);
				}
			}
		}
	}
}
