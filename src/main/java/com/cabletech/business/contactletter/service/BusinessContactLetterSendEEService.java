package com.cabletech.business.contactletter.service;

import java.util.List;
import java.util.Map;

import com.cabletech.business.contactletter.model.BusinessContactLetterSendee;
import com.cabletech.business.workflow.common.model.CommonWorkflowResult;

/**
 * 
 * @author zg
 * 
 */
public interface BusinessContactLetterSendEEService {

	/**
	 * 根据公告编号查询与会人员
	 * 
	 * @param id
	 *            公告滴编号
	 * @return
	 */
	public List<BusinessContactLetterSendee> getBusinessContactletterSendeeById(
			String id);

	/**
	 * 获取参与人员列表
	 * 
	 * @param id
	 *            String
	 * @return
	 */
	public List<Map<String, Object>> getSendeeList(String id);

	/**
	 * 删除
	 * 
	 * @param id
	 *            String
	 */
	void delete(String id);

	/**
	 * 保存
	 * 
	 * @param sendee
	 *            BusinessContactLetterSendee
	 */
	void save(BusinessContactLetterSendee sendee);

	/**
	 * 获取实体
	 * 
	 * @param sendee
	 *            BusinessContactLetterSendee
	 * @return
	 */
	public BusinessContactLetterSendee getOneBusinessContactletterSendee(
			BusinessContactLetterSendee sendee);

	/**
	 * 删除所有的ContactLetterId 对应的发布对象关系数据
	 * 
	 * @param ContactLetterId
	 *            String
	 * @return
	 */
	public boolean deleteByContactLetterId(String ContactLetterId);

	/**
	 * 判断是否已读
	 * 
	 * @param id
	 *            String
	 * @param string
	 *            String
	 * @return
	 */
	public String checkReadById(String id, String string);

	/**
	 * 修改已读状态
	 * 
	 * @param entityEE
	 *            BusinessContactLetterSendee
	 * @return
	 */
	public void updateIsread(BusinessContactLetterSendee entityEE);

	/**查询是否有已存记录
	 * @param id
	 * @return
	 */
	public long getCountByLetterId(String id);

}
