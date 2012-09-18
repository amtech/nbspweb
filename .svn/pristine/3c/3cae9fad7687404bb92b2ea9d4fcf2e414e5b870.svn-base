package com.cabletech.business.assess.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.base.BaseUtil;
import com.cabletech.business.assess.dao.AssessTemplateContentDao;
import com.cabletech.business.assess.model.AssessTemplateContent;
import com.cabletech.business.assess.service.AssessTemplateContentService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 考核模版内容服务
 * 
 * @author zhaobi 2012-7-31 创建
 */
@Service
public class AssessTemplateContentServiceImpl extends
		BaseServiceImpl<AssessTemplateContent, String> implements
		AssessTemplateContentService {
	@Resource(name = "assessTemplateContentDao")
	private AssessTemplateContentDao dao;

	@Override
	protected BaseDao<AssessTemplateContent, String> getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cabletech.business.assess.service.AssessTemplateContentService#
	 * getTemplateContent
	 * (com.cabletech.business.assess.model.AssessTemplateContent)
	 */
	@Override
	public Map<String, Object> getTemplateContent(AssessTemplateContent content) {
		// TODO Auto-generated method stub
		return dao.getTemplateContent(content);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cabletech.business.assess.service.AssessTemplateContentService#
	 * getTableItemList(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getTableItemList(Map<String, Object> map) {
		int itemMaxcount = getMaxTableItem(map);
		List<Map<String, Object>> itemlist = getTableItemContent(map);
		return processList(itemMaxcount, itemlist);
	}

	@Override
	public List<Map<String, Object>> processList(int itemMaxcount,
			List<Map<String, Object>> itemlist) {
		List<Map<String, Object>> newlist = new ArrayList<Map<String, Object>>();
		if (itemlist != null & itemlist.size() > 0) {
			for (Map<String, Object> item : itemlist) {
				String[] itemSplit = item.get("ITEMPATH").toString().split(">");
				int itemlen = itemSplit.length;
				List<String> itemnamelist = new ArrayList<String>();
				for (int i = 1; i < itemlen; i++) {
					item.put("ITEM_NAME" + i, itemSplit[i]);
					itemnamelist.add(itemSplit[i]);
				}
				for (int j = itemlen; j <= itemMaxcount; j++) {
					String lastItem = "ITEM_NAME"
							+ Integer.toString(itemlen - 1);
					item.put("ITEM_NAME" + (j), item.get(lastItem));
					//修改：wangjie  此处有时候会报空指针
					//itemnamelist.add(item.get(lastItem).toString()); 
					itemnamelist.add((String)item.get(lastItem)); 
				}
				item.put("ITEMNAMELIST", itemnamelist);
				newlist.add(item);
			}
		}
		return newlist;
	}

	/**
	 * 获取最大考核项目
	 * 
	 * @param map
	 *            Map<String,Object>
	 * @return
	 */
	@Transactional(readOnly = true)
	public int getMaxTableItem(Map<String, Object> map) {
		return dao.getMaxLvItem(map);
	}

	/**
	 * 获取模块内容
	 * 
	 * @param map
	 *            Map<String,Object>
	 * @return
	 */
	private List<Map<String, Object>> getTableItemContent(
			Map<String, Object> map) {
		return dao.getTableItemContent(map);
	}
}
