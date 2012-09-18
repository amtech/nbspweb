package com.cabletech.business.ah.rating.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.ah.rating.dao.PersonRatingFormDao;
import com.cabletech.business.ah.rating.model.PersonRatingForm;
import com.cabletech.business.ah.rating.service.PersonRatingFormService;
import com.cabletech.business.workflow.electricity.oilengine.dao.OilEngineManageDao;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 人员考核表定义
 * @author wangt
 *
 */
@Service
@Transactional
public class PersonRatingFormServiceImpl extends
BaseServiceImpl<PersonRatingForm, String> implements PersonRatingFormService {

	// 定义人员考核表Dao
	@Resource(name = "personRatingFormDao")
	private PersonRatingFormDao dao;
	@Override
	protected BaseDao<PersonRatingForm, String> getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Page queryPage(PersonRatingForm entity, Page page) {
		return dao.queryPage(entity,page);
	}
	@Override
	public List<Map<String, Object>> getPersons(String regionid, String jobtype) {
		// TODO Auto-generated method stub
		return dao.getPersons(regionid,jobtype);
	}
	/**
	 * 保存分配人员信息
	 * @param entity 
	 */
	@Transactional
	public void saveRelationShip(PersonRatingForm entity){
		dao.deleteOldRelationship(entity.getTableId());
		String ids = entity.getPersonId().trim();
		String [] id = ids.split(",");
		for(int i=0;i<id.length;i++){
			if(id[i].equals("")){
				continue;
			}
			PersonRatingForm bean = new PersonRatingForm() ;
			bean.setTableId(entity.getTableId());
			bean.setPersonId(id[i]);
			dao.save(bean);
		}
	}
	@Override
	public List<Map<String, Object>> getPersonsAssigned(String regionId,
			String jobtype, String tableid) {
		// TODO Auto-generated method stub
		return dao.getPersonsAssigned(regionId,jobtype,tableid);
	}

}
