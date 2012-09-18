package com.cabletech.business.ah.rating.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.rating.model.ContractorSelfRating;
import com.cabletech.business.ah.rating.model.ExamVerify;
import com.cabletech.business.ah.rating.model.ExamVerifyRecord;
import com.cabletech.business.ah.rating.model.ItemResult;
import com.cabletech.business.ah.rating.service.ContractorSelfRatingService;
import com.cabletech.business.ah.rating.service.ExamVerifyRecordService;
import com.cabletech.business.ah.rating.service.ExamVerifyService;
import com.cabletech.business.ah.rating.service.ItemResultService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;
import com.cabletech.common.util.StringUtil;

/**
 * 代维单位人员自评
 * 
 * @author wangt
 * 
 */
@Namespace("/ah")
@Results({
		@Result(name = "list", location = "/ah/rating/contractorselfrating_list.jsp"),
		@Result(name = "input", location = "/ah/rating/contractorselfrating_input.jsp") })
@Action("/ContractorSelfRatingAction")
public class ContractorSelfRatingAction extends
		BaseAction<ContractorSelfRating, String> {
	private static final long serialVersionUID = 1L;

	private ContractorSelfRating entity = new ContractorSelfRating();

	@Resource(name = "contractorSelfRatingServiceImpl")
	private ContractorSelfRatingService service;
	@Resource(name = "itemResultServiceImpl")
	private ItemResultService itemresultservice;

	/**
	 * 列表页面
	 * 
	 * @return
	 */
	public String list() {
		return LIST;
	}

	@Override
	public String input() {
		String personId = super.getParameter("personId");
		String flag = super.getParameter("flag");
		String maxflownum = super.getParameter("maxflownum");
		Map<String, Object> map = new HashMap<String, Object>();
		String id = super.getParameter("id");
		map = service.getPersonImform(personId, id);
		map.put("ID", id);
		map.put("flag", flag);
		map.put("TABLEID", super.getParameter("tableid"));
		map.put("maxflownum", maxflownum);
		super.getRequest().setAttribute("map", map);
		super.getRequest().setAttribute("personId", personId);
		return INPUT;
	}

	/**
	 * 获取指定用户考核表信息
	 * 
	 * @return
	 */
	public void getItems() {
		List<Map<String, Object>> list = service.getItems(super.getRequest()
				.getParameter("personId"), super.getParameter("id"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("root", list);
		super.convertObjToJson(map);
	}

	/**
	 * 查询列表的数据
	 */
	@SuppressWarnings("rawtypes")
	public void listData() {
		Page page = super.initPage();
		UserInfo user = super.getUser();
		page = service.queryPage(page, user.getOrgId());
		convertObjToJson(page);
	}

	@Override
	public ContractorSelfRating getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void prepareSaveModel() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 获取entity
	 * 
	 * @return
	 */
	public ContractorSelfRating getEntity() {
		return entity;
	}

	public void setEntity(ContractorSelfRating entity) {
		this.entity = entity;
	}

	/**
	 * 保存
	 */
	public String save() {
		String flag = (super.getParameter("flag"));
		Date yearMonth = entity.getYearmonth();
		String isexam = entity.getIsexam();
		if (StringUtils.isBlank(entity.getId())) {
			entity.setId(null);
		} else {
			entity = service.getOne(entity.getId());
			entity.setYearmonth(yearMonth);
			entity.setIsexam(isexam);
		}
		entity.setExamdate(new Date());
		entity.setExaminer(super.getUser().getPersonId());
		service.save(entity);
		setItemResult(entity.getId());
		entity.setSelfassenum(itemresultservice.getSelfScoreSum(entity.getId()));
		if (!"null".equals(itemresultservice.getExamScoreSum(entity.getId()))) {
			entity.setExamassenum(itemresultservice.getExamScoreSum(entity
					.getId()));
		}
		service.save(entity);
		String url = "";
		if (StringUtils.isNotBlank(flag)) {
			url = "/ah/MobileExamFormAction!TolistWaitCheckData.action?flag="
					+ flag;
		} else {
			url = "/ah/ContractorSelfRatingAction!list.action";
		}
		super.addMessage("保存月考核结果成功！", url, SysConstant.SUCCESS);
		return SysConstant.SUCCESS;
	}

	/**
	 * 获取考核项目结
	 * 
	 * @param itemid
	 *            itemid
	 */
	private void setItemResult(String itemid) {
		String[] completevalue = (super.getParameter("completevalue")
				.toString() + ",' '").split(",");
		String[] selfscore = (super.getParameter("selfscore").toString() + ",' '")
				.split(",");
		String[] selfremark = (super.getParameter("selfremark").toString() + ",' '")
				.split(",");
		String[] itemresultid = (super.getParameter("itemresultid").toString() + ",' '")
				.split(",");
		String[] score = (super.getParameter("score").toString() + ",' '")
				.split(",");
		String[] examremark = (super.getParameter("examremark").toString() + ",' '")
				.split(",");
		String[] ratingitemid = (super.getParameter("ratingitemid").toString() + ",' '")
				.split(",");
		for (int i = 0; i < itemresultid.length - 1; i++) {
			ItemResult bean = new ItemResult();
			if (StringUtils.isBlank(itemresultid[i])) {
				bean.setId(null);
			} else {
				bean.setId(itemresultid[i]);
			}
			bean.setExamid(itemid);
			bean.setItemid(ratingitemid[i]);
			bean.setExamremark(examremark[i]);
			bean.setScore(score[i]);
			bean.setSelfremark(selfremark[i]);
			bean.setSelfscore(selfscore[i]);
			bean.setTargetcompletevalue(completevalue[i]);
			itemresultservice.save(bean);
		}
	}

	/**
	 * 提交自评
	 * 
	 * 
	 * @update 修改了这个方法 用户移动考核，确认 审核... 周刚.
	 * @return
	 */
	public String submitData() {
		String flag = super.getParameter("flag");
		// 要提交审核流程
		String[] ids = (super.getParameter("ids") + ",").split(",");
		// 当前流程最大节点集合
		String[] maxflownums = null;
		if (StringUtils.isNotBlank(super.getParameter("maxflownum"))) {
			maxflownums = (super.getParameter("maxflownum") + ",").split(",");
		}
		service.processData(super.getRequest(), ids, maxflownums, entity);
		String url = "";
		String msg = "";
		if (StringUtils.isNotBlank(flag)) {
			if (flag.equals("1")) {
				msg = "提交考核结果成功!";
			} else if (flag.equals("2")) {
				msg = "提交审核结果成功!";
			} else if (flag.equals("3")) {
				msg = "提交确认结果成功!";
			}
			url = "/ah/MobileExamFormAction!TolistWaitCheckData.action?flag="
					+ flag;
		} else {
			msg = "提交月考核结果成功！";
			url = "/ah/ContractorSelfRatingAction!list.action";
		}
		super.addMessage(msg, url, SUCCESS);
		return SUCCESS;
	}

}
