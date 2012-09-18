package com.cabletech.business.ah.rating.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.ah.rating.dao.ContractorSelfRatingDao;
import com.cabletech.business.ah.rating.model.ContractorSelfRating;
import com.cabletech.business.ah.rating.model.ExamVerify;
import com.cabletech.business.ah.rating.model.ExamVerifyRecord;
import com.cabletech.business.ah.rating.service.ContractorSelfRatingService;
import com.cabletech.business.ah.rating.service.ExamVerifyRecordService;
import com.cabletech.business.ah.rating.service.ExamVerifyService;
import com.cabletech.business.ah.rating.service.ItemResultService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 代维单位人员自评
 * @author wangt
 *
 */
@Service
@Transactional
public class ContractorSelfRatingServiceImpl extends 
BaseServiceImpl<ContractorSelfRating, String> implements ContractorSelfRatingService  {
	// 代维单位人员自评Dao
	@Resource(name = "contractorSelfRatingDao")
	private ContractorSelfRatingDao dao;
	@Resource(name = "examVerifyRecordServiceImpl")
	private ExamVerifyRecordService examverifyrecordservice;
	@Resource(name = "examVerifyServiceImpl")
	private ExamVerifyService examverifyservice;
	@Override
	protected BaseDao<ContractorSelfRating, String> getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Page queryPage(Page page,String orgId) {
		// TODO Auto-generated method stub
		return dao.queryPage(page, orgId);
	}
	@Override
	public List<Map<String,Object>> getItems(String personId, String id) {
		// TODO Auto-generated method stub
		return dao.getItems(personId, id);
	}
	@Override
	public ContractorSelfRating getOne(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}
	/**
	 * 保存月考核信息
	 * @param entity 
	 * @return
	 */
	public void save(ContractorSelfRating entity){
		dao.save(entity);
	}
	@Override
	public Map<String,Object> getPersonImform(String personId, String id) {
		return dao.getPersonImfor(personId,id);
	}

	/**
	 * 流程处理
	 * @param request  请求
	 * @param ids 当前流程
	 * @param maxflownums 最大流程处理
	 * @param entity 实现
	 */
	@Transactional
	public void processData(HttpServletRequest request, String[] ids, String[] maxflownums, ContractorSelfRating entity) {
		if (ids.length > 0) {
			int flowstate = 0;
			for (int i = 0; i < ids.length; i++) {
				ExamVerify examverifybean = new ExamVerify();
				examverifybean.setResult(request.getParameter("result"));
				examverifybean.setRemark(request.getParameter("remark"));
				examverifyservice.save(examverifybean); // 保存提交记录详细信息
				ExamVerifyRecord examverifyrecordbean = new ExamVerifyRecord();
				examverifyrecordbean.setExamverifyid(examverifybean.getId());
				examverifyrecordbean.setMonthid(ids[i]);
				examverifyrecordservice.save(examverifyrecordbean); // 保存提交记录
				entity = getOne(ids[i]);
				if (StringUtils.isNotBlank(entity.getFlowstate())) {
					flowstate = Integer.valueOf(entity.getFlowstate());
				}
				// 如果审批通过,默认是通过，流程节点+1，不通过-1
				if ("1".equals(request.getParameter("result"))) {
					entity.setFlowstate(Integer.toString(flowstate + 1));
				} else {
					flowstate=flowstate - 1;
					entity.setFlowstate(Integer.toString(flowstate));
				}
				if (null!=maxflownums&&StringUtils.isNotBlank(maxflownums[i])) {
					if (Integer.toString(flowstate).equals(maxflownums[i])) {
                       //移动审核提交后
						entity.setFlowstate("-1");
					}
				}
				if (flowstate == -1) {// 代维确认 后 变成-2
					entity.setFlowstate("-2");
				}
				save(entity); // 改变流程
			}
		}
	}
}
