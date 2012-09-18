package com.cabletech.business.contactletter.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cabletech.business.contactletter.dao.BusinessContactLetterDao;
import com.cabletech.business.contactletter.model.BusinessContactLetter;
import com.cabletech.business.workflow.common.service.WorkflowEntityManager;

/**
 * @author 周刚 2012 7 23
 */
@Service
public class ContactLetterWorkflowEntityService extends WorkflowEntityManager {
	// “待办列表跳转页面”常量
	public static final String WAIT_HANDLE_LIST_URL = "/contactletter/contactletter!waitHandledlist.action?parameter.isQuery=&businessType=";
	// “编号数据列名”常量
	public static final String ID_COLUMN_KEY = "id";
	// “标题数据列名”常量
	public static final String TITLE_COLUMN_KEY = "title";
	@Resource(name = "businessContactLetterDao")
	private BusinessContactLetterDao businessContactLetterDao;

	/**
	 * 获取工作流待办列表访问地址
	 * 
	 * @return String工作流待办列表访问地址
	 */
	@Override
	public String getAccessUrl() {
		return WAIT_HANDLE_LIST_URL;
	}

	/**
	 * 获取业务数据的主键列名
	 * 
	 * @return String 业务数据的主键列名
	 */
	@Override
	public String getBusinessIdColumn() {
		return ID_COLUMN_KEY;
	}

	/**
	 * 获取 业务数据的标题列名
	 * 
	 * @return String 业务数据的标题列名
	 */
	@Override
	public String getBusinessTitleColumn() {
		return TITLE_COLUMN_KEY;
	}

	@Override
	public List<Map<String, Object>> getBusinessDataList(String businessType) {
		BusinessContactLetter entity = new BusinessContactLetter();
		return businessContactLetterDao.queryList4WaitHand(entity, null);
	}

	@Override
	public String getUrlSuffix(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return "";
	}
}
