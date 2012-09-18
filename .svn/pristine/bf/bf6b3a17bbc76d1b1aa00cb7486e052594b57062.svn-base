package com.cabletech.business.satisfy.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.satisfy.model.Satisfaction;
import com.cabletech.business.satisfy.service.SatisfactionListService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.util.Page;

/**
 * 
 * 客户满意度评价查询ACTION
 * 
 * @author 杨隽 2012-04-21 创建
 * 
 */
@Results({ @Result(name = "list", location = "/satisfy/satisfaction_list.jsp") })
@Action("/satisfactionAction")
public class SatisfactionAction extends BaseAction<Satisfaction, String> {
	private static final long serialVersionUID = 1L;
	// 客户满意度评价查询业务处理
	@Resource(name = "satisfactionListServiceImpl")
	private SatisfactionListService satisfactionListService;
	// 客户满意度评价查询条件
	private Satisfaction satisfaction = new Satisfaction();

	/**
	 * 转到客户满意度评价查询界面
	 * 
	 * @return String
	 */
	public String list() {
		return LIST;
	}

	/**
	 * 获取客户满意度评价查询列表数据
	 */
	@SuppressWarnings("rawtypes")
	public void listData() {
		UserInfo userInfo = super.getUser();
		Page page = satisfactionListService.getSatisfactionList(satisfaction,
				userInfo);
		convertObjToJson(page);
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}

	@Override
	protected void prepareViewModel() throws Exception {
	}

	@Override
	public Satisfaction getModel() {
		// TODO Auto-generated method stub
		return satisfaction;
	}

	public Satisfaction getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Satisfaction satisfaction) {
		this.satisfaction = satisfaction;
	}
}