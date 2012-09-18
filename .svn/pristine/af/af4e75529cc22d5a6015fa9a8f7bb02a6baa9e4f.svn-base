package com.cabletech.business.desktop.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.desktop.dao.ContractorResEquipDao;
import com.cabletech.business.desktop.dao.DesktopDao;
import com.cabletech.business.desktop.service.DesktopService;
import com.cabletech.business.notice.model.Notice;
import com.cabletech.business.notice.service.NoticeService;
import com.cabletech.business.workflow.fault.service.FaultStatisticService;
import com.cabletech.business.workflow.workorder.service.WorkOrderStatisticService;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.base.SysConstant;

/**
 * 首页桌面业务服务处理接口实现
 * 
 * @author 杨隽 2011-11-28
 * @author 杨隽 2011-11-30 添加信息类型常量与获取信息类型常量Map的方法
 * @author 杨隽 2011-12-13 添加代维单位人员图表显示方法
 * @author 杨隽 2012-04-27 添加资源专业类型KEY常量
 * 
 */
@Service
@SuppressWarnings("rawtypes")
public class DesktopServiceImpl extends BaseServiceImpl implements
		DesktopService {
	// 资源专业类型KEY
	public static final String BUSINESS_TYPE = "business_type";
	// 资源专业类型名称KEY
	public static final String BUSINESS_TYPE_NAME = "business_type_name";
	// 待办数量KEY
	public static final String WAIT_HANDLED_NUMBER = "wait_handled_number";
	// 待办访问地址KEY
	public static final String URL_KEY = "url";
	// 工作流定义KEY
	public static final String WORKFLOW_KEY = "workflow_key";
	// 工作流名称KEY
	public static final String WORKFLOW_NAME = "workflow_name";
	// 当前登录用户信息
	private UserInfo userInfo;
	// 桌面服务Dao
	@Resource(name = "desktopDao")
	private DesktopDao desktopDao;
	// 公告信息管理业务处理
	@Resource(name = "noticeServiceImpl")
	private NoticeService noticeBo;
	// 代维资源配备统计
	@Resource(name = "contractorResEquipDao")
	private ContractorResEquipDao contractorResEquipDao;
	@Resource(name = "workOrderStatisticServiceImpl")
	private WorkOrderStatisticService workOrderStatisticService;
	@Resource(name = "faultStatisticServiceImpl")
	private FaultStatisticService faultStatisticService;


	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 设置登录用户信息
	 * 
	 * @param user
	 *            用户
	 */
	@Override
	public void setUserInfo(UserInfo user) {
		// TODO Auto-generated method stub
		userInfo = user;
	}

	/**
	 * 列举所有发布给当前用户查看的最近信息列表
	 * 
	 * @param noticeType
	 *            公告类型
	 * @param count
	 *            最近公告数量
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> getLatestNoticeList(String noticeType,
			String count) {
		// TODO Auto-generated method stub
		String condition = getNoticeCondition(noticeType, count);
		List<Map<String, Object>> list = noticeBo
				.getLatestNoticeList(condition);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			if (MapUtils.isEmpty(list.get(i))) {
				String url = "";
				list.get(i).put(URL_KEY, url);
			} else {
				String url = NoticeService.ACCESS_URL;
				url += list.get(i).get("id");
				list.get(i).put(URL_KEY, url);
			}
			resultList.add(list.get(i));
		}
		return resultList;
	}

	/**
	 * 获取当月会议日期字串列表
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<String> getMeetDateList() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		StringBuffer conditionBuffer = new StringBuffer("");
		conditionBuffer.append(" and ( ");
		conditionBuffer.append(" type='");
		conditionBuffer.append(Notice.MEET_TYPE);
		conditionBuffer.append("' and  s.person_id='");
		conditionBuffer.append(userInfo.getPersonId());
		conditionBuffer.append("') ");
		conditionBuffer.append(" and meet_time>=to_date('");
		conditionBuffer.append(year);
		conditionBuffer.append("-");
		conditionBuffer.append(month);
		conditionBuffer.append("-");
		conditionBuffer.append(date);
		conditionBuffer.append("','yyyy-mm-dd') ");
		conditionBuffer.append(" and meet_time<add_months(to_date('");
		conditionBuffer.append(year);
		conditionBuffer.append("-");
		conditionBuffer.append(month);
		conditionBuffer.append("-01','yyyy-mm-dd'),1) ");
		List<Map<String, Object>> list = noticeBo
				.getLatestNoticeList(conditionBuffer.toString());
		List<String> dateList = new ArrayList<String>();
		for (int i = 0; list != null && i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			String monthStr = (String) map.get("month");
			if (dateList.contains(monthStr)) {
				continue;
			}
			dateList.add(monthStr);
		}
		return dateList;
	}

	/**
	 * 获取当天会议数量
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public int getTodayMeetNumber() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		StringBuffer conditionBuffer = new StringBuffer("");
		conditionBuffer.append(" and ( ");
		conditionBuffer.append(" type='");
		conditionBuffer.append(Notice.MEET_TYPE);
		conditionBuffer.append("' and  s.person_id='");
		conditionBuffer.append(userInfo.getPersonId());
		conditionBuffer.append("') ");
		conditionBuffer.append(" and meet_time>=to_date('");
		conditionBuffer.append(year);
		conditionBuffer.append("-");
		conditionBuffer.append(month);
		conditionBuffer.append("-");
		conditionBuffer.append(date);
		conditionBuffer.append("','yyyy-mm-dd') ");
		conditionBuffer.append(" and meet_time<to_date('");
		conditionBuffer.append(year);
		conditionBuffer.append("-");
		conditionBuffer.append(month);
		conditionBuffer.append("-");
		conditionBuffer.append(date);
		conditionBuffer.append("','yyyy-mm-dd')+1 ");
		conditionBuffer.append(" and exists(select regionid from view_region r where r.regionid=a.regionid start with r.regionid='"
					+ userInfo.getRegionId()
					+ "' connect by prior r.parentid=r.regionid)");
		List<Map<String, Object>> list = noticeBo
				.getLatestNoticeList(conditionBuffer.toString());
		if (CollectionUtils.isEmpty(list)) {
			return 0;
		}
		return list.size();
	}

	/**
	 * 获取信息类型
	 * 
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> getInformationTypeList() {
		// 获取信息类型
		List<Map<String, Object>> informationTypeList = desktopDao
				.getDictionaryList(SysConstant.SYSDICTIONARY_TYPE_INFORMATION);
		return informationTypeList;
	}

	/**
	 * 根据信息类型和当前用户组织信息查询条件
	 * 
	 * @param noticeType
	 *            String
	 * @param count
	 *            String
	 * @return String
	 */
	@Transactional
	private String getNoticeCondition(String noticeType, String count) {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer("");
		if (StringUtils.isNotBlank(noticeType)) {
			buf.append(" and type='");
			buf.append(noticeType);
			buf.append("' ");
		}
		// 取最近条数
		if (StringUtils.isNotBlank(count)) {
			buf.append(" and rowindex<='");
			buf.append(count);
			buf.append("' ");
		}
		buf.append(" and ( ");
		buf.append("   (type='");
		buf.append(Notice.NEWS_TYPE);
		buf.append("'  ) ");
		buf.append(" or (type='");
		buf.append(Notice.BULLETIN_TYPE);
		buf.append("' and s.person_id='");
		buf.append(userInfo.getPersonId());
		buf.append("'");
		buf.append(" ) ");
		buf.append(" ) ");
		buf.append(" and exists(select regionid from view_region r where r.regionid=a.regionid start with r.regionid='"
				+ userInfo.getRegionId()
				+ "' connect by prior r.parentid=r.regionid)");
		return buf.toString();
	}

	/**
	 * 代维资源配备 -- 统计列表
	 * 
	 * @param loginUser
	 *            登录用户
	 * @return list
	 */
	@Transactional
	public List<Map<String, Object>> getContractorResEquipList(
			UserInfo loginUser) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (loginUser.isProvinceMobile()) {// 省移动用户
			list = contractorResEquipDao
					.getContractorResEquipListByRegion(loginUser.getRegionId());
		} else if (loginUser.isCityMobile()) {// 市移动用户
			list = contractorResEquipDao.getContractorResEquipListByOrg(
					loginUser.getRegionId(), "");
		} else if (loginUser.isContractor()) {// 代维用户
			list = contractorResEquipDao.getContractorResEquipListByOrg(
					loginUser.getRegionId(), loginUser.getOrgId());
		} else {// 其它用户
			list = contractorResEquipDao.getContractorResEquipListByOrg(
					loginUser.getRegionId(), "");
		}

		return list;
	}

	/**
	 * 代维人员 -- 统计列表
	 * 
	 * @param loginUser
	 *            登录用户
	 * @return list
	 */
	private List<Map<String, Object>> getContractorPersonList(UserInfo loginUser) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (loginUser.isProvinceMobile()) {// 省移动用户
			list = contractorResEquipDao
					.getContractorPersonListByRegion(loginUser.getRegionId());
		} else if (loginUser.isCityMobile()) {// 市移动用户
			list = contractorResEquipDao.getContractorPersonListByOrg(loginUser
					.getRegionId());
		} else if (loginUser.isContractor()) {// 代维用户
			list = contractorResEquipDao.getContractorPersonListByPatrol(
					loginUser.getRegionId(), loginUser.getOrgId());
		} else {// 其它用户
			list = contractorResEquipDao.getContractorPersonListByOrg(loginUser
					.getRegionId());
		}

		return list;
	}

	/**
	 * 获取袋为单位人员显示图需要的数据
	 * 
	 * @param loginUser
	 *            UserInfo
	 * @return String
	 */
	@Transactional
	public String getContractorPersonChartData(UserInfo loginUser) {
		List<Map<String, Object>> list = getContractorPersonList(loginUser);
		String jsonStr = "";
		StringBuffer sb = new StringBuffer();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> temp = list.get(i);
				sb.append("[");
				sb.append("'" + temp.get("NAME") + "'");
				sb.append(",");
				sb.append(temp.get("PERSONNUM"));
				sb.append("]");
				sb.append(",");
			}
		}
		if (sb.length() > 0) {
			jsonStr = sb.substring(0, sb.length() - 1);
		} else {
			jsonStr = "[]";
		}

		return "[" + jsonStr + "]";
	}

	/**
	 * 超时故障派单
	 * 
	 * @param userInfo
	 *            UserInfo
	 */
	@Transactional
	public Map<String, Map<String, Object>> getOvertimeWorkOrderAndFaultNumberList(
			UserInfo userInfo) {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		List<Map<String, Object>> overtimeWorkOrderNumList = workOrderStatisticService
				.getOvertimeWorkOrderStatisticResultList(userInfo);
		List<Map<String, Object>> overtimeFaultNumList = faultStatisticService
				.getOvertimeWorkOrderStatisticResultList(userInfo);
		for (int i = 0; overtimeWorkOrderNumList != null
				&& i < overtimeWorkOrderNumList.size(); i++) {
			Map<String, Object> workOrderNumMap = overtimeWorkOrderNumList
					.get(i);
			String gid = (String) workOrderNumMap.get("gid");
			workOrderNumMap.put("overtime_f_num", "0");
			map.put(gid, workOrderNumMap);
		}
		for (int i = 0; overtimeFaultNumList != null
				&& i < overtimeFaultNumList.size(); i++) {
			Map<String, Object> faultNumMap = overtimeFaultNumList.get(i);
			String gid = (String) faultNumMap.get("gid");
			Map<String, Object> numMap = new HashMap<String, Object>();
			if (map.containsKey(gid)) {
				numMap = map.get(gid);
				numMap.put("overtime_f_num", faultNumMap.get("overtime_f_num"));
			} else {
				numMap = faultNumMap;
				numMap.put("overtime_w_num", "0");
			}
			map.put(gid, numMap);
		}
		return map;
	}


}