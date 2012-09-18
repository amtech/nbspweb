package com.cabletech.business.ah.rating.service;

import java.util.Map;

import com.cabletech.business.ah.rating.model.RatingForm;
import com.cabletech.business.ah.rating.model.RatingFormItemTemp;
import com.cabletech.common.util.Page;

/**
 * 考核表业务服务接口
 * 
 * @author 杨隽 2012-06-26 创建
 * 
 */
public interface RatingFormService {
	/**
	 * 保存考核表和考核表子项信息
	 * 
	 * @param parameterMap
	 *            Map<String,Object> 传入的参数
	 * @param oneCellTemp
	 *            PatrolItemTemp 巡检项导入数据信息
	 * @throws Exception
	 */
	public void save(Map<String, Object> parameterMap,
			RatingFormItemTemp oneCellTemp) throws Exception;

	/**
	 * 获取考核表列表信息
	 * 
	 * @param ratingForm
	 *            RatingForm
	 * @param page
	 *            Page
	 * @return Page
	 */
	public Page queryPage(RatingForm ratingForm, Page page);

	/**
	 * 删除考核表信息
	 * 
	 * @param id
	 *            String[]
	 */
	public void deleteRatingForm(String[] id);

	/**
	 * 查看考核表信息
	 * 
	 * @param id
	 *            String 考核表编号
	 * @return RatingForm 考核表信息
	 */
	public RatingForm view(String id);
}
