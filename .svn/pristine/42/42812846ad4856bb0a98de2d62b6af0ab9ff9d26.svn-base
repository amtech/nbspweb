package com.cabletech.business.assess.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.Service.BaseInfoProvider;
import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.assess.dao.AssessScoreQueryDao;
import com.cabletech.business.assess.service.AssessScoreQueryService;
import com.cabletech.business.excel.TableCellData;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;

/**
 * 月度考核成绩生成业务接口实现
 * 
 * @author 杨隽 2012-08-08 创建
 */
@SuppressWarnings("all")
@Service
@Transactional(readOnly = true)
public class AssessScoreQueryServiceImpl extends BaseServiceImpl implements
		AssessScoreQueryService {
	public static final String BUSINESS_TYPE_QUERY = "0";
	public static final String SUM_QUERY = "1";
	private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat(
			"#0.00");
	@Resource(name = "baseInfoProvider")
	private BaseInfoProvider baseInfoProvider;
	@Resource(name = "assessScoreQueryDao")
	private AssessScoreQueryDao assessScoreQueryDao;

	@Override
	public Map<String, Object> queryScoreList(String yearMonth,
			String queryType, UserInfo user) {
		String regionId = user.getRegionId();
		List<Map<String, Object>> regionList = assessScoreQueryDao
				.getRegionList(regionId);
		List<List<TableCellData>> dataList = new ArrayList<List<TableCellData>>();
		if (BUSINESS_TYPE_QUERY.equals(queryType)) {
			writeBusinessTypeSubjectTr(dataList);
		}
		if (SUM_QUERY.equals(queryType)) {
			writeSumSubjectTr(dataList);
		}
		List<Map<String, Object>> businessTypeDataList = new ArrayList<Map<String, Object>>();
		for (int i = 0; regionList != null && i < regionList.size(); i++) {
			List<Map<String, Object>> list = getBusinessTypeScoreList(
					yearMonth, (String) regionList.get(i).get("REGIONID"));
			if (CollectionUtils.isEmpty(list)) {
				continue;
			}
			businessTypeDataList.addAll(list);
		}
		if (CollectionUtils.isEmpty(businessTypeDataList)) {
			return new HashMap<String, Object>();
		}
		if (BUSINESS_TYPE_QUERY.equals(queryType)) {
			processBusinessTypeScoreList(businessTypeDataList, dataList);
		}
		if (SUM_QUERY.equals(queryType)) {
			processSumScoreList(businessTypeDataList, dataList);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", dataList);
		return map;
	}

	/**
	 * 获取专业月度考核成绩数据列表
	 * 
	 * @param yearMonth
	 *            String
	 * @param regionId
	 *            String
	 * @return List<Map<String, Object>>
	 */
	private List<Map<String, Object>> getBusinessTypeScoreList(
			String yearMonth, String regionId) {
		List<Map<String, Object>> orgList = baseInfoProvider.getOrgService()
				.getOrgList(regionId, new String[] { "2" });
		if (CollectionUtils.isEmpty(orgList)) {
			return null;
		}
		List<Map<String, Object>> orgBusinessTypeList = assessScoreQueryDao
				.getOrgBusinessTypeList(regionId);
		if (CollectionUtils.isEmpty(orgBusinessTypeList)) {
			return null;
		}
		List<Map<String, Object>> businessTypeDataList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> cityAssessScoreList = assessScoreQueryDao
				.getOrgCityAssessScoreList(regionId, yearMonth);
		List<Map<String, Object>> cityAppealScoreList = assessScoreQueryDao
				.getOrgCityAppealScoreList(regionId, yearMonth);
		List<Map<String, Object>> countyAssessScoreList = assessScoreQueryDao
				.getOrgCountyAssessScoreList(regionId, yearMonth);
		List<Map<String, Object>> countyAppealScoreList = assessScoreQueryDao
				.getOrgCountyAppealScoreList(regionId, yearMonth);
		for (int i = 0; i < orgList.size(); i++) {
			Map<String, Object> orgDataMap = new HashMap<String, Object>();
			Map<String, Object> orgMap = orgList.get(i);
			for (int j = 0; j < orgBusinessTypeList.size(); j++) {
				Map<String, Object> businessTypeMap = orgBusinessTypeList
						.get(j);
				if (!orgMap.get("ID").equals(businessTypeMap.get("ORGID"))) {
					continue;
				}
				double cityScore = calculateScore(cityAssessScoreList,
						businessTypeMap);
				if (cityScore != -1) {
					cityScore += calculateScore(cityAppealScoreList,
							businessTypeMap);
				}
				double countyScore = calculateScore(countyAssessScoreList,
						businessTypeMap);
				if (countyScore != -1) {
					countyScore += calculateScore(countyAppealScoreList,
							businessTypeMap);
				}
				double sumScore = 0;
				if (cityScore != -1) {
					businessTypeMap.put("SCORE_01",
							NUMBER_FORMAT.format(cityScore));
				}
				if (countyScore != -1) {
					businessTypeMap.put("SCORE_02",
							NUMBER_FORMAT.format(countyScore));
				}
				if (cityScore != -1 && countyScore != -1) {
					sumScore = 0.9 * cityScore + 0.1 * countyScore
							/ countyAssessScoreList.size();
				} else if (cityScore != -1) {
					sumScore = cityScore;
				} else if (countyScore != -1) {
					sumScore = countyScore / countyAssessScoreList.size();
				}
				businessTypeMap
						.put("SUM_SCORE", NUMBER_FORMAT.format(sumScore));
				double weight = Double.parseDouble((String) businessTypeMap
						.get("WEIGHING")) / 100d;
				businessTypeMap.put("SUM_WEIGHT_SCORE",
						NUMBER_FORMAT.format(sumScore * weight));
				businessTypeDataList.add(businessTypeMap);
			}
		}
		return businessTypeDataList;
	}

	/**
	 * 计算考核成绩
	 * 
	 * @param scoreList
	 *            List<Map<String, Object>>
	 * @param businessTypeMap
	 *            Map<String, Object>
	 * @return double
	 */
	private double calculateScore(List<Map<String, Object>> scoreList,
			Map<String, Object> businessTypeMap) {
		if (CollectionUtils.isEmpty(scoreList)) {
			return -1;
		}
		String orgId = (String) businessTypeMap.get("ORGID");
		String businessType = (String) businessTypeMap.get("B_ID");
		double score = 0d;
		for (int i = 0; i < scoreList.size(); i++) {
			Map<String, Object> map = scoreList.get(i);
			if (orgId.equals(map.get("CONTRACTOR_ID"))
					&& businessType.equals(map.get("BUSINESS_TYPE"))) {
				String scoreValue = (String) map.get("SCORE");
				score += Double.parseDouble(scoreValue);
			}
		}
		return score;
	}

	/**
	 * 后期数据处理
	 * 
	 * @param businessTypeDataList
	 *            List<Map<String, Object>>
	 * @param dataList
	 *            List<List<TableCellData>>
	 */
	private void processBusinessTypeScoreList(
			List<Map<String, Object>> businessTypeDataList,
			List<List<TableCellData>> dataList) {
		String orgName = "";
		String regionName = "";
		for (int i = 0; i < businessTypeDataList.size(); i++) {
			List<TableCellData> rowList = new ArrayList<TableCellData>();
			Map<String, Object> map = businessTypeDataList.get(i);
			int colIndex = 0;
			regionName = writeRegionName(businessTypeDataList, regionName, i,
					colIndex, rowList);
			colIndex++;
			orgName = writeOrgName(businessTypeDataList, orgName, i, colIndex,
					rowList);
			colIndex++;
			writeBusinessType(i, rowList, map, colIndex);
			colIndex++;
			TableCellData fourthCellData = new TableCellData();
			fourthCellData.setRowIndex(i + 1);
			fourthCellData.setColIndex(colIndex);
			fourthCellData.setCellValue((String) map.get("SCORE_01"));
			rowList.add(fourthCellData);
			colIndex++;
			TableCellData fifthCellData = new TableCellData();
			fifthCellData.setRowIndex(i + 1);
			fifthCellData.setColIndex(colIndex);
			fifthCellData.setCellValue((String) map.get("SCORE_02"));
			rowList.add(fifthCellData);
			colIndex++;
			TableCellData sixthCellData = new TableCellData();
			sixthCellData.setRowIndex(i + 1);
			sixthCellData.setColIndex(colIndex);
			sixthCellData.setCellValue((String) map.get("SUM_SCORE"));
			rowList.add(sixthCellData);
			dataList.add(rowList);
		}
	}

	/**
	 * 后期数据处理
	 * 
	 * @param businessTypeDataList
	 *            List<Map<String, Object>>
	 * @param dataList
	 *            List<List<TableCellData>>
	 */
	private void processSumScoreList(
			List<Map<String, Object>> businessTypeDataList,
			List<List<TableCellData>> dataList) {
		String orgName = "";
		String regionName = "";
		for (int i = 0; i < businessTypeDataList.size(); i++) {
			List<TableCellData> rowList = new ArrayList<TableCellData>();
			Map<String, Object> map = businessTypeDataList.get(i);
			int colIndex = 0;
			regionName = writeRegionName(businessTypeDataList, regionName, i,
					colIndex, rowList);
			colIndex++;
			boolean flag = !orgName.equals((String) map.get("name"));
			orgName = writeOrgName(businessTypeDataList, orgName, i, colIndex,
					rowList);
			colIndex++;
			writeBusinessType(i, rowList, map, colIndex);
			colIndex++;
			TableCellData fourthCellData = new TableCellData();
			fourthCellData.setRowIndex(i + 1);
			fourthCellData.setColIndex(colIndex);
			fourthCellData.setCellValue((String) map.get("SUM_SCORE"));
			rowList.add(fourthCellData);
			colIndex++;
			TableCellData fifthCellData = new TableCellData();
			fifthCellData.setRowIndex(i + 1);
			fifthCellData.setColIndex(colIndex);
			fifthCellData.setCellValue((String) map.get("WEIGHING") + "%");
			rowList.add(fifthCellData);
			colIndex++;
			if (flag) {
				int rowSpan = 0;
				double sumWeightScore = 0d;
				double sumWeigth = 0d;
				for (int j = 0; j < businessTypeDataList.size(); j++) {
					Map<String, Object> tempMap = businessTypeDataList.get(j);
					if (orgName.equals(tempMap.get("NAME"))) {
						rowSpan++;
						sumWeightScore += Double.parseDouble((String) tempMap
								.get("SUM_WEIGHT_SCORE"));
						sumWeigth += Double.parseDouble((String) tempMap
								.get("WEIGHING"));
					}
				}
				TableCellData sixthCellData = new TableCellData();
				sixthCellData.setRowIndex(i + 1);
				sixthCellData.setColIndex(colIndex);
				sixthCellData.setRowSpan(rowSpan);
				sixthCellData.setCellValue(NUMBER_FORMAT.format(sumWeightScore
						* 100 / sumWeigth));
				rowList.add(sixthCellData);
			}
			dataList.add(rowList);
		}
	}

	/**
	 * 写入区域名称
	 * 
	 * @param businessTypeDataList
	 *            List<Map<String, Object>>
	 * @param regionName
	 *            String
	 * @param rowIndex
	 *            int
	 * @param rowList
	 *            List<TableCellData>
	 * @param colIndex
	 *            int
	 * @return String
	 */
	private String writeRegionName(
			List<Map<String, Object>> businessTypeDataList, String regionName,
			int rowIndex, int colIndex, List<TableCellData> rowList) {
		Map<String, Object> map = businessTypeDataList.get(rowIndex);
		if (!regionName.equals((String) map.get("regionname"))) {
			regionName = (String) map.get("regionname");
			TableCellData firstCellData = new TableCellData();
			firstCellData.setRowIndex(rowIndex + 1);
			firstCellData.setColIndex(colIndex);
			firstCellData.setCellValue(regionName);
			int rowSpan = 0;
			for (int j = 0; j < businessTypeDataList.size(); j++) {
				if (regionName.equals(businessTypeDataList.get(j).get(
						"regionname"))) {
					rowSpan++;
				}
			}
			firstCellData.setRowSpan(rowSpan);
			rowList.add(firstCellData);
		}
		return regionName;
	}

	/**
	 * 写入组织名称
	 * 
	 * @param businessTypeDataList
	 *            List<Map<String, Object>>
	 * @param orgName
	 *            String
	 * @param rowIndex
	 *            int
	 * @param rowList
	 *            List<TableCellData>
	 * @param colIndex
	 *            int
	 * @return String
	 */
	private String writeOrgName(List<Map<String, Object>> businessTypeDataList,
			String orgName, int rowIndex, int colIndex,
			List<TableCellData> rowList) {
		Map<String, Object> map = businessTypeDataList.get(rowIndex);
		if (!orgName.equals((String) map.get("name"))) {
			orgName = (String) map.get("name");
			TableCellData secondCellData = new TableCellData();
			secondCellData.setRowIndex(rowIndex + 1);
			secondCellData.setColIndex(colIndex);
			secondCellData.setCellValue(orgName);
			int rowSpan = 0;
			for (int j = 0; j < businessTypeDataList.size(); j++) {
				if (orgName.equals(businessTypeDataList.get(j).get("name"))) {
					rowSpan++;
				}
			}
			secondCellData.setRowSpan(rowSpan);
			rowList.add(secondCellData);
		}
		return orgName;
	}

	/**
	 * 写入专业类型
	 * 
	 * @param rowIndex
	 *            int
	 * @param rowList
	 *            List<TableCellData>
	 * @param map
	 *            Map<String, Object>
	 * @param colIndex
	 *            int
	 */
	private void writeBusinessType(int rowIndex, List<TableCellData> rowList,
			Map<String, Object> map, int colIndex) {
		TableCellData thirdCellData = new TableCellData();
		thirdCellData.setRowIndex(rowIndex + 1);
		thirdCellData.setColIndex(colIndex);
		thirdCellData.setCellValue((String) map.get("business_type"));
		rowList.add(thirdCellData);
	}

	/**
	 * 写入专业月度考核标题行数据
	 * 
	 * @param dataList
	 *            List<List<TableCellData>>
	 */
	private void writeBusinessTypeSubjectTr(List<List<TableCellData>> dataList) {
		List<TableCellData> subjectList = new ArrayList<TableCellData>();
		int colIndex = 0;
		TableCellData firstCellData = new TableCellData();
		firstCellData.setRowIndex(0);
		firstCellData.setColIndex(colIndex);
		firstCellData.setCellValue("地市");
		subjectList.add(firstCellData);
		colIndex++;
		TableCellData secondCellData = new TableCellData();
		secondCellData.setRowIndex(0);
		secondCellData.setColIndex(colIndex);
		secondCellData.setCellValue("代维公司");
		subjectList.add(secondCellData);
		colIndex++;
		TableCellData thirdCellData = new TableCellData();
		thirdCellData.setRowIndex(0);
		thirdCellData.setColIndex(colIndex);
		thirdCellData.setCellValue("代维专业");
		subjectList.add(thirdCellData);
		colIndex++;
		TableCellData fourthCellData = new TableCellData();
		fourthCellData.setRowIndex(0);
		fourthCellData.setColIndex(colIndex);
		fourthCellData.setCellValue("地市月考核成绩");
		subjectList.add(fourthCellData);
		colIndex++;
		TableCellData fifthCellData = new TableCellData();
		fifthCellData.setRowIndex(0);
		fifthCellData.setColIndex(colIndex);
		fifthCellData.setCellValue("县考核成绩汇总");
		subjectList.add(fifthCellData);
		colIndex++;
		TableCellData sixthCellData = new TableCellData();
		sixthCellData.setRowIndex(0);
		sixthCellData.setColIndex(colIndex);
		sixthCellData.setCellValue("总计");
		subjectList.add(sixthCellData);
		dataList.add(subjectList);
	}

	/**
	 * 写入月度综合考核标题行数据
	 * 
	 * @param dataList
	 *            List<List<TableCellData>>
	 */
	private void writeSumSubjectTr(List<List<TableCellData>> dataList) {
		List<TableCellData> subjectList = new ArrayList<TableCellData>();
		int colIndex = 0;
		TableCellData firstCellData = new TableCellData();
		firstCellData.setRowIndex(0);
		firstCellData.setColIndex(colIndex);
		firstCellData.setCellValue("地市");
		subjectList.add(firstCellData);
		colIndex++;
		TableCellData secondCellData = new TableCellData();
		secondCellData.setRowIndex(0);
		secondCellData.setColIndex(colIndex);
		secondCellData.setCellValue("代维公司");
		subjectList.add(secondCellData);
		colIndex++;
		TableCellData thirdCellData = new TableCellData();
		thirdCellData.setRowIndex(0);
		thirdCellData.setColIndex(colIndex);
		thirdCellData.setCellValue("代维专业");
		subjectList.add(thirdCellData);
		colIndex++;
		TableCellData fourthCellData = new TableCellData();
		fourthCellData.setRowIndex(0);
		fourthCellData.setColIndex(colIndex);
		fourthCellData.setCellValue("专业月度考核成绩");
		subjectList.add(fourthCellData);
		colIndex++;
		TableCellData fifthCellData = new TableCellData();
		fifthCellData.setRowIndex(0);
		fifthCellData.setColIndex(colIndex);
		fifthCellData.setCellValue("权重");
		subjectList.add(fifthCellData);
		colIndex++;
		TableCellData sixthCellData = new TableCellData();
		sixthCellData.setRowIndex(0);
		sixthCellData.setColIndex(colIndex);
		sixthCellData.setCellValue("总计");
		subjectList.add(sixthCellData);
		dataList.add(subjectList);
	}

	@Override
	protected BaseDao getBaseDao() {
		return null;
	}
}
