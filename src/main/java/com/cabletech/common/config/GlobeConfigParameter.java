package com.cabletech.common.config;

/**
 * 全局配置参数类
 * 
 * @author 杨隽 2012-07-17 创建
 * 
 */
public class GlobeConfigParameter {
	/**
	 * eoms控制开关
	 */
	private boolean eomsSwitch;
	/**
	 * eoms服务访问地址
	 */
	private String eomsUrl;
	/**
	 * WebGis部署目录名
	 */
	private String webGisDeployname;

	public boolean isEomsSwitch() {
		return eomsSwitch;
	}

	public void setEomsSwitch(boolean eomsSwitch) {
		this.eomsSwitch = eomsSwitch;
	}

	public String getEomsUrl() {
		return eomsUrl;
	}

	public void setEomsUrl(String eomsUrl) {
		this.eomsUrl = eomsUrl;
	}

	public String getWebGisDeployname() {
		return webGisDeployname;
	}

	public void setWebGisDeployname(String webGisDeployname) {
		this.webGisDeployname = webGisDeployname;
	}
}
