package com.cabletech.business.workflow.workorder.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cabletech.business.flowservice.util.ProMockPo;
import com.cabletech.business.workflow.workorder.dao.WorkOrderBaseDao;
import com.cabletech.business.workflow.workorder.model.WorkOrder;
import com.cabletech.business.workflow.workorder.model.WorkOrderReply;
import com.cabletech.business.workflow.workorder.service.WorkOrderCalcuateService;
import com.cabletech.common.util.DateUtil;

/**
 * 通用工单历时计算业务接口实现
 * 
 * @author 杨隽 2012-01-10 创建
 * @author 杨隽 2012-01-11 实现工单历时的计算功能
 * @author 杨隽 2012-02-07 修改getBaseDao()方法实现为getWorkOrderBaseDao ()方法实现
 * @author 杨隽 2012-03-22 修改isOverTime()方法实现（以第一次回复时间和反馈时限进行比较）
 * 
 */
@Service
public class WorkOrderCalcuateServiceImpl extends
		WorkOrderBaseServiceImpl<String, String> implements
		WorkOrderCalcuateService {
	// 以秒为计量单位的计算方式
	public static final int SECOND_UNIT = 0;
	// 以分为计量单位的计算方式
	public static final int MINUTE_UNIT = 1;
	// 以小时为计量单位的计算方式
	public static final int HOUR_UNIT = 2;
	// 以天为计量单位的计算方式
	public static final int DAY_UNIT = 3;
	// 以天+小时为计量单位的计算方式
	public static final int DAY_HOUR_UNIT = 4;
	// 以天+小时+分为计量单位的计算方式
	public static final int DAY_HOUR_MINUTE_UNIT = 5;
	// 秒时长基数
	public static final long SECORD_TIME_LENGTH = 1000;
	// 分时长基数
	public static final long MINUTE_TIME_LENGTH = 1000 * 60;
	// 小时时长基数
	public static final long HOUR_TIME_LENGTH = 1000 * 60 * 60;
	// 天时长基数
	public static final long DAY_TIME_LENGTH = 1000 * 60 * 60 * 24;
	// 计算单位类型
	private int unitCalculateType;

	/**
	 * 设置通用工单历时的计算单位
	 * 
	 * @param unitType
	 *            int 通用工单历时的计算单位
	 */
	@Override
	public void setCalculateUnit(int unitType) {
		// TODO Auto-generated method stub
		unitCalculateType = unitType;
	}

	/**
	 * 根据工单的工作流业务编号进行工单总体历时的计算
	 * 
	 * @param transferId
	 *            String 工单的工作流业务编号
	 * @return String 工单总体历时的计算结果
	 */
	@Override
	public String calcuateTotalResult(String transferId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = super.workOrderWorkflowService
				.getWorkflowTasksTimeLength(transferId);
		Long totalTimeLength = (Long) map.get("total_time_length");
		return calculateTimeLength(totalTimeLength);
	}

	/**
	 * 根据工单的工作流业务编号进行工单每步任务处理历时的计算
	 * 
	 * @param transferId
	 *            String 工单的工作流业务编号
	 * @return List<Map<String, Object>> 工单每步任务处理历时的计算结果
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> calculateEveryStepResult(String transferId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = super.workOrderWorkflowService
				.getWorkflowTasksTimeLength(transferId);
		List<Map<String, Object>> list = (List<Map<String, Object>>) map
				.get("every_step_time_length");
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<Map<String, Object>>();
		}
		List<Map<String, Object>> resultList = calculateEveryStepTimeLength(list);
		return resultList;
	}

	/**
	 * 进行工单的是否超时判断
	 * 
	 * @param workOrder
	 *            工单实体
	 * @param workOrderReply
	 *            工单回单实体
	 * @return boolean 工单的是否超时
	 */
	@Override
	public boolean isOverTime(WorkOrder workOrder, WorkOrderReply workOrderReply) {
		// TODO Auto-generated method stub
		Date deadlineDate = workOrder.getOvertimeSet();
		String taskId = workOrderReply.getTransferId();
		List<ProMockPo> list = super.workOrderWorkflowService
				.getHandledProcessList(taskId);
		if (CollectionUtils.isEmpty(list)) {
			return false;
		}
		ProMockPo task = null;
		for (int i = 0; i < list.size(); i++) {
			task = list.get(i);
			if ("填写回单".equals(task.getTaskName())) {
				break;
			}
		}
		if (task != null) {
			Date replyDate = DateUtil.Str2UtilDate(task.getEndTime(),
					"yyyy-MM-dd HH:mm:ss");
			return replyDate.after(deadlineDate);
		}
		return true;
	}

	@Override
	protected WorkOrderBaseDao<String, String> getWorkOrderBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据每步的工作流历时长度（毫秒级）和计量单位进行历时的计算
	 * 
	 * @param list
	 *            List<Map<String, Object>> 每步的工作流历时长度结果列表
	 * @return List<Map<String, Object>> 历时的计算列表
	 */
	private List<Map<String, Object>> calculateEveryStepTimeLength(
			List<Map<String, Object>> list) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> resultMap : list) {
			if (MapUtils.isEmpty(resultMap)) {
				continue;
			}
			Iterator<Map.Entry<String, Object>> iter = resultMap.entrySet()
					.iterator();
			Map.Entry<String, Object> entry = iter.next();
			Long totalTimeLength = (Long) entry.getValue();
			String totalTimeLengthString = calculateTimeLength(totalTimeLength);
			resultMap.put(entry.getKey(), totalTimeLengthString);
			resultList.add(resultMap);
		}
		return resultList;
	}

	/**
	 * 根据历时的长度（毫秒级）和计量单位进行历时的计算
	 * 
	 * @param totalTimeLength
	 *            Long 历时的长度（毫秒级）
	 * @return String 历时的计算结果
	 */
	private String calculateTimeLength(Long totalTimeLength) {
		// TODO Auto-generated method stub
		long timeLength = 0l;
		long dayLength = 0l;
		long hourLength = 0l;
		long minuteLength = 0l;
		String timeLengthString = "";
		switch (unitCalculateType) {
		case SECOND_UNIT:
			timeLength = Math
					.round(1.0d * totalTimeLength / SECORD_TIME_LENGTH);
			timeLengthString = timeLength + "秒";
			break;
		case MINUTE_UNIT:
			timeLength = Math
					.round(1.0d * totalTimeLength / MINUTE_TIME_LENGTH);
			timeLengthString = timeLength + "分";
			break;
		case HOUR_UNIT:
			timeLength = Math.round(1.0d * totalTimeLength / HOUR_TIME_LENGTH);
			timeLengthString = timeLength + "小时";
			break;
		case DAY_UNIT:
			timeLength = Math.round(1.0d * totalTimeLength / DAY_TIME_LENGTH);
			timeLengthString = timeLength + "天";
			break;
		case DAY_HOUR_UNIT:
			dayLength = totalTimeLength / DAY_TIME_LENGTH;
			hourLength = Math.round(1.0d * (totalTimeLength % DAY_TIME_LENGTH)
					/ HOUR_TIME_LENGTH);
			timeLengthString = dayLength + "天";
			timeLengthString += hourLength + "小时";
			break;
		case DAY_HOUR_MINUTE_UNIT:
			dayLength = totalTimeLength / DAY_TIME_LENGTH;
			hourLength = (totalTimeLength % DAY_TIME_LENGTH) / HOUR_TIME_LENGTH;
			minuteLength = Math.round(1.0d
					* ((totalTimeLength % DAY_TIME_LENGTH) % HOUR_TIME_LENGTH)
					/ MINUTE_TIME_LENGTH);
			timeLengthString = dayLength + "天";
			timeLengthString += hourLength + "小时";
			timeLengthString += minuteLength + "分";
			break;
		default:
			break;
		}
		return timeLengthString;
	}
}
