package com.cabletech.common.tag;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.common.util.SpringContext;

/**
 * 从静态编码表中获取下拉数据
 * 
 * @author zhaobi
 * 
 */
public class QuickloadListsTag extends AbstractTagSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * 选择使用的组件类型下拉列表，复选框，单选钮
	 */
	private String type = "look";// select，radio，checkbox,look,subset
	/**
	 * 下拉类别内容名称，英文描述。
	 */
	private String columntype = "";
	/**
	 * 组件值
	 */
	private String keyValue = "";

	/**
	 * 组件ID
	 */
	private String id;
	/**
	 * cssclass 名称
	 */
	private String cssClass;
	/**
	 * 组件name
	 */
	private String name;
	/**
	 * 组件style
	 */
	private String style;
	/**
	 * 是否查询时使用
	 */
	private String isQuery = "false";
	/**
	 * onchange事件
	 */
	private String onChange = null;
	/**
	 * onclick事件
	 */
	private String onClick = null;

	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	private BaseInfoProvider baseInfoProvider;

	/**
	 * 添加html组件
	 */
	public StringBuffer writeHtmlString() {
		StringBuffer builder = new StringBuffer("");
		baseInfoProvider = (BaseInfoProvider) SpringContext
				.getApplicationContext().getBean("baseInfoProvider");
		// 获得字典数据
		List<Map<String, Object>> dictslist = baseInfoProvider
				.getDicList(columntype);

		// 组织成相应的组件
		StringBuilder htmlStr = assembly(dictslist);
		return builder.append(htmlStr);
	}

	/**
	 * @param data
	 *            List<Map<String,Object>>
	 * @return
	 */
	private StringBuilder assembly(List<Map<String, Object>> data) {
		StringBuilder assembly = new StringBuilder();
		if (id == null) {
			setId(name);
		}
		if ("checkbox".equals(type)) {
			int i = 1;

			for (Map<String, Object> dict : data) {
				assembly.append("<input type=\"checkbox\" name=\"" + name
						+ "\" value=\"" + dict.get("CODEVAULE") + "\" id=\""
						+ id + i + "\" class=\"" + cssClass + "\"");
				List<String> list = Arrays.asList(keyValue.split(","));
				if (list.contains(dict.get("CODEVAULE"))) {
					assembly.append(" checked ");
				}
				assembly.append(" />" + dict.get("LABLE"));
				i++;
			}
		} else if ("radio".equals(type)) {
			int i = 1;
			for (Map<String, Object> dict : data) {
				assembly.append("<input type=\"radio\" name=\"" + name + "\"");
				if (onClick != null) {
					assembly.append(" onclick=\"" + getOnClick() + "\" ");
				}
				if (onChange != null) {
					assembly.append(" onchange=\"" + getOnChange() + "\" ");
				}
				assembly.append(" value=\"" + dict.get("CODEVAULE")
						+ "\" id=\"" + id + i + "\" class=\"" + cssClass + "\"");
				if (keyValue.equals(dict.get("CODEVAULE"))) {
					assembly.append(" checked ");
				}
				assembly.append(" />" + dict.get("LABLE"));
				i++;
			}
		} else if ("select".equals(type)) {// 默认是list

			assembly.append("<select name=\"" + name + "\" id=\"" + id
					+ "\" style=\"" + style + "\" ");
			if (onClick != null) {
				assembly.append(" onclick=\"" + getOnClick() + "\" ");
			}
			if (onChange != null) {
				assembly.append(" onchange=\"" + getOnChange() + "\" ");
			}
			assembly.append(" class=\"" + cssClass + "\" />");
			if (isQuery.equals("query")) {
				assembly.append("<option value=\"\"> 不限 </option>");
			} else if (isQuery.equals("select")) {
				assembly.append("<option value=\"\"> 请选择 </option>");
			}
			for (Map<String, Object> dict : data) {
				assembly.append("<option value=\"" + dict.get("CODEVAULE")
						+ "\" ");
				if (keyValue.equals(dict.get("CODEVAULE"))) {
					assembly.append("selected");
				}
				assembly.append(">" + dict.get("LABLE") + "</option>");
			}
			assembly.append("</select>");
		} else {
			for (Map<String, Object> dict : data) {
				if (keyValue.equals(dict.get("CODEVAULE"))) {
					assembly.append(dict.get("LABLE") + " ");
				}

			}
		}
		setId(null);
		return assembly;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the columntype
	 */
	public String getColumntype() {
		return columntype;
	}

	/**
	 * @param columntype
	 *            the columntype to set
	 */
	public void setColumntype(String columntype) {
		this.columntype = columntype;
	}

	/**
	 * @return the keyValue
	 */
	public String getKeyValue() {
		return keyValue;
	}

	/**
	 * @param keyValue
	 *            the keyValue to set
	 */
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the cssClass
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * @param cssClass
	 *            the cssClass to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @param style
	 *            the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @return the onChange
	 */
	public String getOnChange() {
		return onChange;
	}

	/**
	 * @param onChange
	 *            the onChange to set
	 */
	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	/**
	 * @return the onClick
	 */
	public String getOnClick() {
		return onClick;
	}

	/**
	 * @param onClick
	 *            the onClick to set
	 */
	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
}
