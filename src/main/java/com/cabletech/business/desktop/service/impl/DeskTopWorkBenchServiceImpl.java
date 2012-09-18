package com.cabletech.business.desktop.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cabletech.business.desktop.dao.DeskTopWorkBenchDao;
import com.cabletech.business.desktop.service.DeskTopWorkBenchService;
import com.cabletech.common.base.BaseServiceImpl;
/**
 * 
 * 个人工作台--用户快捷方式
 * @author wj
 * 
 */
@Service
@Transactional
public class DeskTopWorkBenchServiceImpl extends BaseServiceImpl implements DeskTopWorkBenchService{

	@Resource(name = "deskTopWorkBenchDao")
	private DeskTopWorkBenchDao deskTopWorkBenchDao;

	@Override
	protected DeskTopWorkBenchDao getBaseDao() {
		return deskTopWorkBenchDao;
	}

	@Override
	public void deleteUserShortcuts(String userId) {
		deskTopWorkBenchDao.deleteUserShortcuts(userId);
	}

	@Override
	public List<Map<String,Object>> queryUserShortcuts(String userId) {
		return deskTopWorkBenchDao.queryUserShortcuts(userId);
	}

	@Override
	public void saveUserShortcuts(String userId, String menuId) {
		deskTopWorkBenchDao.deleteUserShortcuts(userId);
		String[] ids = menuId.split(",");
		for(String id : ids){
			if(StringUtils.isNotBlank(id)){
				deskTopWorkBenchDao.saveUserShortcuts(userId, id);
			}
		}
	}
}