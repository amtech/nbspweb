package com.cabletech.business.ah.rating.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.rating.dao.RatingFormDao;
import com.cabletech.business.ah.rating.model.RatingForm;
import com.cabletech.business.ah.rating.model.RatingFormItemTemp;
import com.cabletech.business.ah.rating.service.RatingFormItemService;
import com.cabletech.business.ah.rating.service.RatingFormService;
import com.cabletech.business.base.condition.ConditionGenerate;
import com.cabletech.business.base.condition.QueryParameter;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 考核表业务服务接口实现
 * 
 * @author 杨隽 2012-02-26 创建
 * 
 */
@Service
public class RatingFormServiceImpl extends BaseServiceImpl<RatingForm, String>
		implements RatingFormService {
	@Resource(name = "ratingFormDao")
	private RatingFormDao ratingFormDao;
	@Resource(name = "ratingFormItemServiceImpl")
	private RatingFormItemService ratingFormItemService;
	@Resource(name = "emptyConditionGenerate")
	private ConditionGenerate conditionGenerate;

	/**
	 * 保存巡检项和巡检子项信息
	 * 
	 * @param parameterMap
	 *            Map<String,Object> 传入的参数
	 * @param oneCellTemp
	 *            PatrolItemTemp 巡检项导入数据信息
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void save(Map<String, Object> parameterMap,
			RatingFormItemTemp oneCellTemp) throws Exception {
		UserInfo user = (UserInfo) parameterMap.get("user");
		RatingForm ratingForm = (RatingForm) parameterMap.get("ratingForm");
		ratingForm.setCreater(user.getPersonId());
		String itemId = getExistItemId(ratingForm);
		if (StringUtils.isBlank(itemId)) {
			itemId = saveOneItem(ratingForm);
		}
		ratingFormItemService.save(oneCellTemp, itemId);
	}

	@Override
	@Transactional
	public Page queryPage(RatingForm ratingForm, Page page) {
		conditionGenerate.setQuerySql(new QueryParameter());
		conditionGenerate.setPage(page);
		return ratingFormDao.queryPageForSql(conditionGenerate);
	}

	@Override
	@Transactional
	public void deleteRatingForm(String[] id) {
		if (ArrayUtils.isEmpty(id)) {
			return;
		}
		for (int i = 0; i < id.length; i++) {
			RatingForm ratingForm = ratingFormDao.get(id[i]);
			ratingFormDao.delete(ratingForm);
			ratingFormItemService.delete(ratingForm.getId());
		}
	}

	@Override
	@Transactional
	public RatingForm view(String id) {
		if (StringUtils.isBlank(id)) {
			return new RatingForm();
		}
		RatingForm ratingForm = ratingFormDao.get(id);
		return ratingForm;
	}

	/**
	 * 根据巡检项导入数据信息（巡检项名称、区域编号和专业类型）查询巡检项编号
	 * 
	 * @param oneCell
	 *            PatrolItemTemp 巡检项导入数据信息（巡检项名称、区域编号和专业类型）
	 * @return String 巡检项编号
	 */
	private String getExistItemId(RatingForm oneCell) {
		List<RatingForm> itemList = ratingFormDao.getRatingFormList(oneCell);
		String itemId = "";
		if (!CollectionUtils.isEmpty(itemList)) {
			itemId = itemList.get(0).getId();
		}
		return itemId;
	}

	/**
	 * 保存单个巡检项信息
	 * 
	 * @param oneCell
	 *            PatrolItemTemp 巡检项导入数据信息
	 * @return String 巡检项编号
	 * @throws Exception
	 */
	private String saveOneItem(RatingForm oneCell) throws Exception {
		RatingForm item = new RatingForm();
		BeanUtils.copyProperties(item, oneCell);
		item.setCreateTime(new Date());
		item.setUseState(RatingForm.ITEM_START_USING_STATE);
		ratingFormDao.save(item);
		String itemId = item.getId();
		return itemId;
	}

	@Override
	protected BaseDao<RatingForm, String> getBaseDao() {
		return ratingFormDao;
	}
}