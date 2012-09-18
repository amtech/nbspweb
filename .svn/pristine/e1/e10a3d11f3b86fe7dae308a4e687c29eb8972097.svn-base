package com.cabletech.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.session.SessionMappingStorage;
import org.jasig.cas.client.util.AbstractConfigurationFilter;

/**
 * 单点登录退出过滤
 * 
 * @author wangt
 * @author 杨隽 2012-05-17 去除无用的导入、局部变量和类成员
 * 
 */
public class SingleSignOutFilter extends AbstractConfigurationFilter {

    private static final SingleSignOutHandler handler = new SingleSignOutHandler();

    /* 
     * 单点登出初始化
     * (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(final FilterConfig filterConfig) throws ServletException {
        if (!isIgnoreInitConfiguration()) {
            handler.setArtifactParameterName(getPropertyFromInitParams(filterConfig, "artifactParameterName", "ticket"));
            handler.setLogoutParameterName(getPropertyFromInitParams(filterConfig, "logoutParameterName", "logoutRequest"));
        }
        handler.init();
    }

    /**
     * 设置参数名称
     * @param name 名称
     */
    public void setArtifactParameterName(final String name) {
        handler.setArtifactParameterName(name);
    }
    
    /**
     * 设置退出参数
     * @param name 名称
     */
    public void setLogoutParameterName(final String name) {
        handler.setLogoutParameterName(name);
    }

    /**
     * 设置session
     * @param storage 存储session
     */
    public void setSessionMappingStorage(final SessionMappingStorage storage) {
        handler.setSessionMappingStorage(storage);
    }
    
    /* 
     * 过滤器
     * (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (handler.isTokenRequest(request)) {
            handler.recordSession(request);
        } else if (handler.isLogoutRequest(request)) {
            handler.destroySession(request);
            // Do not continue up filter chain
            return;
        } else {
            log.trace("Ignoring URI " + request.getRequestURI());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
        // nothing to do
    }
    
    /**
     * @return
     */
    protected static SingleSignOutHandler getSingleSignOutHandler() {
        return handler;
    }
}
