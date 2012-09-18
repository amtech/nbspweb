package com.cabletech.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 框架标签支持
 * 
 * @author 杨隽 2010-12-16 创建
 * 
 */
public abstract class AbstractTagSupport extends BodyTagSupport {

	/**
	 * 根据读取到的数据和标签设置的样式显示数据信息
	 */
	public abstract StringBuffer writeHtmlString();

	/**
	 * 标签执行方法
	 * 
	 * @return int
	 */
	@SuppressWarnings("static-access")
	public int doEndTag() throws JspException {
		StringBuffer buf = writeHtmlString();
		try {
			super.pageContext.getOut().print(buf.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		return super.EVAL_PAGE;
	}
}
