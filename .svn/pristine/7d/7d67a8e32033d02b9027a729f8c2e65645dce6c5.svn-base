package com.cabletech.business.contactletter.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.contactletter.dao.BusinessContactLetterSendeeDao;
import com.cabletech.business.contactletter.model.BusinessContactLetterSendee;
import com.cabletech.business.contactletter.service.BusinessContactLetterSendEEService;
import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.flowservice.util.WorkFlowServiceClient;
import com.cabletech.business.workflow.common.model.CommonWorkflowResult;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.base.SysConstant;

/**
 * 
 * @author zg
 * 
 */
@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BusinessContactLetterSendEEServiceImpl extends
		BaseServiceImpl<BusinessContactLetterSendee, String> implements
		BusinessContactLetterSendEEService {

	@Resource(name = "businessContactLetterSendeeDao")
	private BusinessContactLetterSendeeDao dao;

	@Override
	protected BaseDao getBaseDao() {
		return dao;
	}

	@Override
	public void delete(String contactletterid) {
		dao.deleteBycontactletter(contactletterid);
	}

	@Override
	public List<BusinessContactLetterSendee> getBusinessContactletterSendeeById(
			String id) {
		return null;
	}

	@Override
	public long getCountByLetterId(String id) {

		return dao.getCountByLetterId(id);
	}

	@Override
	public BusinessContactLetterSendee getOneBusinessContactletterSendee(
			BusinessContactLetterSendee sendee) {
		return null;
	}

	@Override
	public List<Map<String, Object>> getSendeeList(String noticeid) {
		return null;
	}

	@Override
	public boolean deleteByContactLetterId(String ContactLetterId) {
		return false;
	}

	@Transactional
	@Override
	public String checkReadById(String objectid, String letterid) {

		return dao.checkReadById(objectid, letterid);
	}

	@Override
	public void updateIsread(BusinessContactLetterSendee entityEE) {
		dao.updateIsread(entityEE);
	}


}
