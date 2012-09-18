package com.cabletech.business.assess.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.cabletech.business.assess.model.AssessAppealForm;
import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.common.base.BaseDao;

/**
 * 月度考核成绩生成Dao
 * 
 * @author 杨隽 2012-08-08 创建
 * 
 */
@Repository
public class AssessScoreQueryDao extends
		BaseDao<AssessExaminationResult, String> {
	/**
	 * 获取区域列表信息
	 * 
	 * @param regionId
	 *            String
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getRegionList(String regionId) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT * FROM VIEW_REGION VR ");
		sql.append(" WHERE REGIONID LIKE '%00' ");
		sql.append(" AND REGIONID NOT LIKE '%0000' ");
		sql.append(" START WITH VR.REGIONID='");
		sql.append(regionId);
		sql.append("' ");
		sql.append(" CONNECT BY PRIOR VR.REGIONID=VR.PARENTID ");
		return super.getSQLALL(sql.toString());
	}

	/**
	 * 获取组织专业类型列表
	 * 
	 * @param regionId
	 *            String
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getOrgBusinessTypeList(String regionId) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT VO.ID AS ORGID,VO.NAME,VO.REGIONID,VO.REGIONNAME, ");
		sql.append(" R.RESOURCEID,DIC.LABLE AS BUSINESS_TYPE, ");
		sql.append(" DIC.CODEVALUE AS B_ID,TO_CHAR(MSW.WEIGHING,'FM990.00') AS WEIGHING, ");
		sql.append(" '' AS SCORE_01,'' AS SCORE_02,'' AS SUM_SCORE,'' AS SUM_WEIGHT_SCORE ");
		sql.append(" FROM VIEW_ORG VO ");
		sql.append(" JOIN RESPONSIBLE R ON VO.ID=R.CONTRACTORID ");
		sql.append(" JOIN MM_SPECIALTYWEIGHING MSW ");
		sql.append(" ON MSW.SPECIALTY_TYPE=R.RESOURCEID");
		sql.append(" JOIN BASE_SYSDICTIONARY DIC ");
		sql.append(" ON DIC.CODEVALUE=R.RESOURCEID ");
		sql.append(" AND DIC.COLUMNTYPE='BUSINESSTYPE'");
		sql.append(" WHERE VO.ORGTYPE='2' ");
		sql.append(" AND VO.REGIONID='");
		sql.append(regionId);
		sql.append("' ");
		return super.getSQLALL(sql.toString());
	}

	/**
	 * 获取地市的专业考核成绩
	 * 
	 * @param regionId
	 *            String
	 * @param yearMonth
	 *            String
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getOrgCityAssessScoreList(String regionId,
			String yearMonth) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT TO_CHAR(MER.SCORE,'FM990.00') AS SCORE, ");
		sql.append(" MER.CONTRACTOR_ID,MAT.BUSINESS_TYPE ");
		sql.append(" FROM MM_EXAMINATIONRESULTS MER ");
		sql.append(" JOIN MM_APPRAISE_TABLE MAT ON MAT.ID=MER.TABLE_ID ");
		sql.append(" WHERE MER.APPRAISE_MONTH=TO_DATE('");
		sql.append(yearMonth);
		sql.append("','yyyy-mm-dd') ");
		sql.append(" AND MER.REGION_ID='");
		sql.append(regionId);
		sql.append("' ");
		sql.append(" AND MER.STATE='");
		sql.append(AssessExaminationResult.END_STATE);
		sql.append("'");
		return super.getSQLALL(sql.toString());
	}

	/**
	 * 获取地市的申诉成绩
	 * 
	 * @param regionId
	 *            String
	 * @param yearMonth
	 *            String
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getOrgCityAppealScoreList(String regionId,
			String yearMonth) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT TO_CHAR(MAF.SCORE,'FM990.00') AS SCORE, ");
		sql.append(" MAF.CONTRACTOR_ID,MAT.BUSINESS_TYPE ");
		sql.append(" FROM MM_APPEALFORM MAF");
		sql.append(" JOIN MM_EXAMINATIONRESULTS MER ON MAF.EXAM_RESULT_ID=MER.ID ");
		sql.append(" JOIN MM_APPRAISE_TABLE MAT ON MAT.ID=MER.TABLE_ID ");
		sql.append(" WHERE MER.APPRAISE_MONTH=TO_DATE('");
		sql.append(yearMonth);
		sql.append("','yyyy-mm-dd') ");
		sql.append(" AND MER.REGION_ID='");
		sql.append(regionId);
		sql.append("' ");
		sql.append(" AND MAF.CHECK_RESULT='pass' ");
		sql.append(" AND MAF.AUDITING_STATE='");
		sql.append(AssessAppealForm.END_STATE);
		sql.append("' ");
		return super.getSQLALL(sql.toString());
	}

	/**
	 * 获取县区的专业考核成绩
	 * 
	 * @param regionId
	 *            String
	 * @param yearMonth
	 *            String
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getOrgCountyAssessScoreList(
			String regionId, String yearMonth) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT TO_CHAR(MER.SCORE,'FM990.00') AS SCORE, ");
		sql.append(" MER.CONTRACTOR_ID,MAT.BUSINESS_TYPE ");
		sql.append(" FROM MM_EXAMINATIONRESULTS MER ");
		sql.append(" JOIN MM_APPRAISE_TABLE MAT ON MAT.ID=MER.TABLE_ID ");
		sql.append(" WHERE MER.APPRAISE_MONTH=TO_DATE('");
		sql.append(yearMonth);
		sql.append("','yyyy-mm-dd') ");
		sql.append(" AND EXISTS( ");
		sql.append(" SELECT RE.REGIONID FROM VIEW_REGION RE ");
		sql.append(" WHERE RE.REGIONID=MER.REGION_ID AND RE.REGIONID<>'");
		sql.append(regionId);
		sql.append("' ");
		sql.append(" START WITH RE.REGIONID='");
		sql.append(regionId);
		sql.append("' CONNECT BY PRIOR RE.REGIONID=RE.PARENTID ");
		sql.append(" ) ");
		sql.append(" AND MER.STATE='");
		sql.append(AssessExaminationResult.END_STATE);
		sql.append("'");
		return super.getSQLALL(sql.toString());
	}

	/**
	 * 获取县区的申诉成绩
	 * 
	 * @param regionId
	 *            String
	 * @param yearMonth
	 *            String
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getOrgCountyAppealScoreList(
			String regionId, String yearMonth) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT TO_CHAR(MAF.SCORE,'FM990.00') AS SCORE, ");
		sql.append(" MAF.CONTRACTOR_ID,MAT.BUSINESS_TYPE ");
		sql.append(" FROM MM_APPEALFORM MAF");
		sql.append(" JOIN MM_EXAMINATIONRESULTS MER ON MAF.EXAM_RESULT_ID=MER.ID ");
		sql.append(" JOIN MM_APPRAISE_TABLE MAT ON MAT.ID=MER.TABLE_ID ");
		sql.append(" WHERE MER.APPRAISE_MONTH=TO_DATE('");
		sql.append(yearMonth);
		sql.append("','yyyy-mm-dd') ");
		sql.append(" AND EXISTS( ");
		sql.append(" SELECT RE.REGIONID FROM VIEW_REGION RE ");
		sql.append(" WHERE RE.REGIONID=MER.REGION_ID AND RE.REGIONID<>'");
		sql.append(regionId);
		sql.append("' ");
		sql.append(" START WITH RE.REGIONID='");
		sql.append(regionId);
		sql.append("' CONNECT BY PRIOR RE.REGIONID=RE.PARENTID ");
		sql.append(" ) ");
		sql.append(" AND MAF.CHECK_RESULT='pass' ");
		sql.append(" AND MAF.AUDITING_STATE='");
		sql.append(AssessAppealForm.END_STATE);
		sql.append("' ");
		return super.getSQLALL(sql.toString());
	}
}