package com.cabletech.business.assess.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.assess.dao.AssessTemplateDao;
import com.cabletech.business.assess.model.AssessTemplate;
import com.cabletech.business.assess.service.AssessTemplateService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 考核模版实现
 * 
 * @author zhaobi 2012-7-31 创建
 */
@Service
public class AssessTemplateServiceImpl extends
		BaseServiceImpl<AssessTemplate, String> implements
		AssessTemplateService {

	@Resource(name = "assessTemplateDao")
	private AssessTemplateDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cabletech.business.assess.service.AssessTemplateService#
	 * queryAssessTemplate(com.cabletech.business.assess.model.AssessTemplate,
	 * com.cabletech.common.util.Page)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page queryAssessTemplate(AssessTemplate template, Page page) {
		return dao.getAssessTemplate(template, page);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryAssessTemplate(AssessTemplate template){
		return dao.getAssessTemplate(template);
	}

	@Override
	protected BaseDao<AssessTemplate, String> getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.cabletech.business.assess.service.AssessTemplateService#getTemplate(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public AssessTemplate getTemplate(String id) {
		return dao.get(id);
	}

	@Override
	@Transactional
	public int del(String[] ids) {
		String itemIds = "";
		for (int i = 0; i < ids.length; i++) {
			if (i > 0) {
				itemIds = itemIds + ",";
			}
			itemIds = itemIds + "'" + ids[i] + "'";
		}
		return dao.del(itemIds);
		
	}
}
