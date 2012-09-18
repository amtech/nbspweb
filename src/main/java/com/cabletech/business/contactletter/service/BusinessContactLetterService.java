package com.cabletech.business.contactletter.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.contactletter.model.BusinessContactLetter;
import com.cabletech.business.contactletter.model.ContactLetterAudit;
import com.cabletech.business.workflow.common.model.CommonWorkflowResult;
import com.cabletech.common.util.Page;

/**
 * 业务处理接口
 * 
 * @author zg
 * 
 */
public interface BusinessContactLetterService {
	/**
	 * 保存通知
	 * 
	 * @param businessContactletter
	 *            businessContactletter
	 * @param user
	 *            user
	 * @param files
	 *            files
	 * @return
	 * @throws Exception
	 */
	BusinessContactLetter saveBusinessContactletter(
			BusinessContactLetter businessContactletter, UserInfo user,
			List<FileItem> files) throws Exception;

	/**
	 * 删除
	 * 
	 * @param id
	 *            id
	 * @return
	 */
	boolean deleteBusinessContactletter(String id);


	public Map<String, List<CommonWorkflowResult>> getProcessHistoryList(
			String id);
	/**
	 * loadBusinessContactletter
	 * 
	 * @param id
	 *            id
	 * @return
	 */
	BusinessContactLetter loadBusinessContactletter(String id);

	/**
	 * 查询
	 * 
	 * @param businessContactletter
	 *            BusinessContactLetter
	 * @param page
	 *            page
	 * @param user
	 *            UserInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Page queryPage4WaitHand(BusinessContactLetter businessContactletter,
			Page page, UserInfo user);

	/**
	 * 阅读联系函
	 * 
	 * @param id
	 *            String
	 * @param personId
	 *            String
	 * @return
	 */
	BusinessContactLetter readContactletter(String id, String personId);

	/**
	 * 查询统计
	 * 
	 * @param entity
	 *            BusinessContactLetter
	 * @param page
	 *            Page
	 * @param userInfo
	 *            UserInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Page queryPage4Query(BusinessContactLetter entity, Page page,
			UserInfo userInfo);

	/**
	 * 审核方法
	 * 
	 * @param entity
	 *            BusinessContactLetter
	 * @param contactLetterAudit
	 *            ContactLetterAudit
	 * @param userInfo
	 *            UserInfo
	 */
	void auditThis(BusinessContactLetter entity,
			ContactLetterAudit contactLetterAudit, UserInfo userInfo);

	/**
	 * 检查工作流中信息
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	Integer checkInViewJBPM(String id); 
}
