package com.cabletech.business.ah.rating.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cabletech.baseinfo.base.BaseUtil;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.rating.dao.PersonFlowDao;
import com.cabletech.business.ah.rating.model.PersonFlow;
import com.cabletech.business.ah.rating.service.PersonFlowService;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 人员流程定义
 * 
 * @author wj
 * @param <T>
 * @param <PK>
 */
@SuppressWarnings("unchecked")
@Service
@Transactional
public class PersonFlowServiceImpl<T, PK extends Serializable> extends
		BaseServiceImpl<T, PK> implements PersonFlowService {

	@Resource(name = "personFlowDao")
	private PersonFlowDao<PersonFlow, String> personFlowDao;

	@Override
	public void save(Map<String, Object> parameters) throws RuntimeException {
		String[] ratingPersons = (String[]) parameters.get("ratingPersons");
		String[] processer = (String[]) parameters.get("processer");
		for (String person : ratingPersons) {
			personFlowDao.deletePersonFlows(person);
			for (int i = 0; i < processer.length; i++) {
				int index = i + 1;
				String oneProcesser = processer[i];
				PersonFlow entity = new PersonFlow();
				entity.setPersonId(person);
				entity.setProcesser(oneProcesser);
				entity.setFlowNum(index);
				personFlowDao.save(entity);
			}
		}
	}

	/**
	 * 
	 * 获取考核人员列表
	 * 
	 * @param parameters
	 *            参数封装
	 * @return List
	 * 
	 */
	public String searchRatingPersons(Map<String, Object> parameters) {
		List<Map<String, Object>> ls = personFlowDao
				.searchRatingPersons(parameters);
		StringBuffer buf = new StringBuffer("");
		if (CollectionUtils.isNotEmpty(ls)) {
			Map<String, Object> temp;
			for (int i = 0; i < ls.size(); i++) {
				temp = ls.get(i);
				buf.append(temp.get("USERNAME"));
				buf.append(",");
				buf.append(temp.get("SID"));
				buf.append(";");
				if (i < ls.size() - 1) {
				}
			}
		}
		return buf.toString();
	}

	/**
	 * 
	 * 删除人员流程定义列表
	 * 
	 * @param personId
	 *            人员sid
	 * 
	 */
	public void deletePersonFlows(String personId) {
		this.personFlowDao.deletePersonFlows(personId);
	}

	/**
	 * 根据人员ID获取办理人员
	 * 
	 * @param parameters
	 *            参数封装
	 * @return
	 */
	public List<Map<String, Object>> searchProcesserByPid(
			Map<String, Object> parameters) {
		return personFlowDao.searchProcesserByPid(parameters);
	}

	/**
	 * 查询人员流程定义列表
	 * 
	 * @param parameters
	 *            参数封装
	 * @param page
	 *            分页的信息
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public Page searchPersonFlows(Map<String, Object> parameters, Page page) {
		Page ret = this.personFlowDao.searchPersonFlows(parameters, page);
		ret.setResult(convertProcesser(page.getResult()));
		return ret;
	}

	/**
	 * 转换出来人
	 * 
	 * @param ls
	 *            ls
	 * @return
	 */
	private List<Map<String, Object>> convertProcesser(
			List<Map<String, Object>> ls) {
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> temp : ls) {
			Map<String, Object> ele = new HashMap<String, Object>();
			ele.put("PERSON_ID", (String) temp.get("PERSON_ID"));
			ele.put("PERSON_NAME", (String) temp.get("USERNAME"));
			ele.put("POST_OFFICE", (String) temp.get("POST_OFFICE"));
			String[] processers = BaseUtil.compart(
					(String) temp.get("PROCESSER"), ",");
			for (String processer : processers) {
				try {
					ele.put(BaseUtil.compart(processer, ":")[0],
							BaseUtil.compart(processer, ":")[1]);
				} catch (Exception e) {
					continue;
				}
			}
			ret.add(ele);
		}
		return ret;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected PersonFlowDao getBaseDao() {
		return personFlowDao;
	}

}