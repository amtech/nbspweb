package com.cabletech.business.wplan.patrolitem.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.wplan.patrolitem.dao.PatrolItemDao;
import com.cabletech.business.wplan.patrolitem.model.PatrolItem;
import com.cabletech.business.wplan.patrolitem.model.PatrolItemTemp;
import com.cabletech.business.wplan.patrolitem.service.PatrolItemService;
import com.cabletech.business.wplan.patrolitem.service.PatrolSubItemService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 巡检项业务服务接口实现
 * 
 * @author wangjie
 * @author 杨隽 2011-10-25 添加导入巡检项目的“值域范围”和“默认值”字段
 * @author 杨隽 2011-10-25 添加启用巡检项目、修改逻辑删除为作废，废弃物理删除
 * @author 杨隽 2012-02-13 分离巡检项导入业务和巡检项基本操作业务
 * @author 杨隽 2012-02-14 重构query方法为queryForList方法
 * @author 杨隽 2012-02-14 添加save方法并去除queryForList、deleteLogic、startUsing方法
 * 
 */
@Service
public class PatrolItemServiceImpl extends BaseServiceImpl<PatrolItem, String>
		implements PatrolItemService {
	@Resource(name = "patrolItemDao")
	private PatrolItemDao patrolItemDao;
	@Resource(name = "patrolSubItemServiceImpl")
	private PatrolSubItemService patrolSubItemService;

	/**
	 * 保存巡检项和巡检子项信息
	 * 
	 * @param parameterMap
	 *            Map<String,String> 传入的参数
	 * @param oneCellTemp
	 *            PatrolItemTemp 巡检项导入数据信息
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void save(Map<String, String> parameterMap,
			PatrolItemTemp oneCellTemp) throws Exception {
		// TODO Auto-generated method stub
		PatrolItemTemp oneCell = new PatrolItemTemp();
		BeanUtils.copyProperties(oneCell, oneCellTemp);
		oneCell.setBusinessType(parameterMap.get("business_type"));
		oneCell.setRegionId(parameterMap.get("region_id"));
		String itemId = getExistItemId(oneCell);
		if (StringUtils.isBlank(itemId)) {
			itemId = saveOneItem(oneCell);
		}
		patrolSubItemService.save(oneCell, itemId);
	}

	/**
	 * 根据巡检项导入数据信息（巡检项名称、区域编号和专业类型）查询巡检项编号
	 * 
	 * @param oneCell
	 *            PatrolItemTemp 巡检项导入数据信息（巡检项名称、区域编号和专业类型）
	 * @return String 巡检项编号
	 */
	private String getExistItemId(PatrolItemTemp oneCell) {
		List<PatrolItem> itemList = patrolItemDao.getPatrolItemList(oneCell);
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
	private String saveOneItem(PatrolItemTemp oneCell) throws Exception {
		String itemId;
		PatrolItem item = new PatrolItem();
		BeanUtils.copyProperties(item, oneCell);
		patrolItemDao.save(item);
		itemId = item.getId();
		return itemId;
	}

	@Override
	protected BaseDao<PatrolItem, String> getBaseDao() {
		// TODO Auto-generated method stub
		return patrolItemDao;
	}
}