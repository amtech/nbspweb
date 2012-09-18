package com.cabletech.business.ah.rating.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.ah.rating.model.RatingForm;
import com.cabletech.business.ah.rating.service.RatingFormItemService;
import com.cabletech.business.ah.rating.service.RatingFormService;
import com.cabletech.common.base.BaseAction;
import com.cabletech.common.base.SysConstant;
import com.cabletech.common.util.Page;

/**
 * 考核表ACTION
 * 
 * @author 杨隽 2012-06-26 创建
 * 
 */
@Namespace("/ah")
@Results({
		@Result(name = "view", location = "/ah/rating/rating_form_view.jsp"),
		@Result(name = "list", location = "/ah/rating/rating_form_list.jsp") })
@Action("/ratingFormAction")
public class RatingFormAction extends BaseAction<RatingForm, String> {
	private static final long serialVersionUID = 1L;
	// 巡检项查询条件参数
	private RatingForm ratingForm;
	// 考核表列表页面跳转路径
	private static final String RATING_FORM_LIST_URL = "/ah/ratingFormAction!list.action?";
	/**
	 * 考核表业务服务
	 */
	@Resource(name = "ratingFormServiceImpl")
	private RatingFormService ratingFormService;
	/**
	 * 考核表子项业务服务
	 */
	@Resource(name = "ratingFormItemServiceImpl")
	private RatingFormItemService ratingFormItemService;

	/**
	 * 转到考核表列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String list() throws Exception {
		return LIST;
	}

	/**
	 * 考核表列表数据
	 */
	public void listData() {
		Page page = super.initPage();
		page = ratingFormService.queryPage(ratingForm, page);
		super.setExcelParameter(page);
		convertObjToJson(page);
	}

	/**
	 * 考核表子项列表数据
	 */
	public void listItemData() {
		String tableId = super.getParameter("id");
		List<Map<String, Object>> list = ratingFormItemService
				.queryListByTableId(tableId);
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("root", list);
		super.convertObjToJson(map);
	}

	/**
	 * 删除考核表数据信息
	 * 
	 * @return String
	 */
	public String delete() {
		String[] id = super.getRequest().getParameterValues("id");
		ratingFormService.deleteRatingForm(id);
		String url = RATING_FORM_LIST_URL;
		super.addMessage("提示：考核表信息删除成功 ", url, SysConstant.SUCCESS);
		return SUCCESS;
	}

	@Override
	public RatingForm getModel() {
		return ratingForm;
	}

	public RatingForm getRatingForm() {
		return ratingForm;
	}

	public void setRatingForm(RatingForm ratingForm) {
		this.ratingForm = ratingForm;
	}

	@Override
	protected void prepareViewModel() throws Exception {
		String id = super.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			ratingForm = ratingFormService.view(id);
			this.getRequest().setAttribute("tableid", id);
		}
	}

	@Override
	protected void prepareSaveModel() throws Exception {
	}
}