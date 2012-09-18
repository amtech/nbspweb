package com.cabletech.business.workflow.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 空工作流业务实体服务
 * 
 * @author 杨隽 2011-11-08 创建
 * @author 杨隽 2011-11-29 添加getBusinessTitleColumn()方法和getAccessUrl()方法
 * @author 杨隽 2012-01-09 修改包名
 * 
 */
@Service
public class EmptyWorkflowEntityManager extends WorkflowEntityManager {
	@Override
	public String getAccessUrl() {
		return "";
	}

	@Override
	public String getBusinessIdColumn() {
		return "";
	}

	@Override
	public String getBusinessTitleColumn() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public List<Map<String, Object>> getBusinessDataList(String businessType) {
		// TODO Auto-generated method stub
		return new ArrayList<Map<String, Object>>();
	}

	@Override
	public String getUrlSuffix(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return "";
	}
}
