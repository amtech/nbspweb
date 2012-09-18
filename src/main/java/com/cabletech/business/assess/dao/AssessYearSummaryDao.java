package com.cabletech.business.assess.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.business.assess.model.AssessAppealForm;
import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.common.base.BaseDao;

/**
 * 年考核汇总Dao
 * 
 * @author 杨隽 2012-08-09 创建
 */
@SuppressWarnings("all")
@Repository
public class AssessYearSummaryDao extends BaseDao {
	/**
	 * 获取年考核汇总的标题行信息
	 * 
	 * @param yearMonth
	 *            String
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getTableItemList(String yearMonth) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT DISTINCT MAI.ID AS ITEM_ID,MAI.ITEM_NAME, ");
		sql.append(" TO_CHAR(ROW_NUMBER() OVER(PARTITION BY MAT.ID,MER.ID ORDER BY MAI.ID)) AS RN ");
		sql.append(" FROM MM_APPRAISE_ITEM MAI ");
		sql.append(" JOIN MM_APPRAISE_TABLE MAT ON MAI.TABLE_ID=MAT.ID ");
		sql.append(" JOIN MM_EXAMINATIONRESULTS MER ON MER.TABLE_ID=MAT.ID ");
		sql.append(" WHERE MAI.ITEM_ID IS NULL ");
		sql.append(" AND MAT.TABLE_TYPE='02' ");
		sql.append(" AND MER.APPRAISE_MONTH=TO_DATE('");
		sql.append(yearMonth);
		sql.append("','yyyy-mm-dd')");
		sql.append(" ORDER BY MAI.ID ");
		return super.getSQLALL(sql.toString());
	}

	/**
	 * 获取年考核汇总的数据信息
	 * 
	 * @param yearMonth
	 *            String
	 * @param rowNum
	 *            String[]
	 * @param rowName
	 *            String[]
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getYearSummaryDataList(String yearMonth,
			String[] rowNum, String[] rowName) {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT TO_CHAR(ROWNUM) AS NO_,M.* ");
		sql.append(" FROM ( ");
		sql.append(" SELECT MP.ID,MP.NAME, ");
		for (int i = 0; i < rowNum.length; i++) {
			sql.append(" TO_CHAR(MAX(DECODE(RN,");
			sql.append(rowNum[i]);
			sql.append(",MP.SCORE,0)),'FM990.09') AS ITEM_");
			sql.append(rowName[i]);
			sql.append(", ");
		}
		sql.append(" TO_CHAR(SUM(MP.SCORE)+( ");
		sql.append(" SELECT NVL(SUM(MAF.SCORE),0) FROM MM_APPEALFORM MAF ");
		sql.append(" JOIN MM_EXAMINATIONRESULTS MER ON MAF.EXAM_RESULT_ID=MER.ID ");
		sql.append(" JOIN MM_APPRAISE_TABLE MAT ON MER.TABLE_ID=MAT.ID ");
		sql.append(" WHERE MAF.CHECK_RESULT='pass' ");
		sql.append(" AND MAF.AUDITING_STATE='");
		sql.append(AssessAppealForm.END_STATE);
		sql.append("' ");
		sql.append(" AND MAF.CONTRACTOR_ID=MP.ID ");
		sql.append(" AND MAT.TABLE_TYPE='02' ");
		sql.append(" AND MER.APPRAISE_MONTH=TO_DATE('");
		sql.append(yearMonth);
		sql.append("','yyyy-mm-dd') ");
		sql.append(" ),'FM990.09') AS SUM_,TO_CHAR( ");
		sql.append(" ( ");
		sql.append(" SELECT COUNT(*) FROM MM_APPEALFORM MAF ");
		sql.append(" JOIN MM_EXAMINATIONRESULTS MER ON MAF.EXAM_RESULT_ID=MER.ID ");
		sql.append(" JOIN MM_APPRAISE_TABLE MAT ON MER.TABLE_ID=MAT.ID ");
		sql.append(" WHERE MAF.CHECK_RESULT='pass' ");
		sql.append(" AND MAF.AUDITING_STATE='");
		sql.append(AssessAppealForm.END_STATE);
		sql.append("' ");
		sql.append(" AND MAF.CONTRACTOR_ID=MP.ID ");
		sql.append(" AND MAT.TABLE_TYPE='02' ");
		sql.append(" AND MER.APPRAISE_MONTH=TO_DATE('");
		sql.append(yearMonth);
		sql.append("','yyyy-mm-dd') ");
		sql.append(" ) ");
		sql.append(" ) AS APPERAL_NUM ");
		sql.append(" FROM ( ");
		sql.append(" SELECT MO.*, ");
		sql.append(" ROW_NUMBER() OVER(PARTITION BY ID ORDER BY LV_ID) AS RN FROM ( ");
		sql.append(" SELECT VO.ID,VO.NAME,MN.LV_ID,");
		sql.append(" SUM(MN.SCORE) AS SCORE ");
		sql.append(" FROM ( ");
		sql.append(" SELECT MM.*,( ");
		sql.append(" SELECT MAI_.ID FROM MM_APPRAISE_ITEM MAI_ ");
		sql.append(" WHERE MAI_.ITEM_ID IS NULL ");
		sql.append(" START WITH MAI_.ID=MM.ITEM_ ");
		sql.append(" CONNECT BY PRIOR MAI_.ITEM_ID=MAI_.ID ");
		sql.append(" ) AS LV_ID ");
		sql.append(" FROM ( ");
		sql.append(" SELECT MER.CONTRACTOR_ID,MAD.*,MAI.ID AS ITEM_ ");
		sql.append(" FROM MM_EXAMINATIONRESULTS MER ");
		sql.append(" JOIN MM_APPRAISE_TABLE MAT ON MER.TABLE_ID=MAT.ID ");
		sql.append(" JOIN MM_ASSESSMENTDETAILS MAD ON MER.ID=MAD.RESULT_ID ");
		sql.append(" JOIN MM_APPRAISE_CONTENT MAC ON MAD.CONTENT_ID=MAC.ID ");
		sql.append(" JOIN MM_APPRAISE_ITEM MAI ON MAC.ITEM_ID=MAI.ID ");
		sql.append(" WHERE MAT.TABLE_TYPE='02' ");
		sql.append(" AND MER.STATE='");
		sql.append(AssessExaminationResult.END_STATE);
		sql.append("'");
		sql.append(" AND MER.APPRAISE_MONTH=TO_DATE('");
		sql.append(yearMonth);
		sql.append("','yyyy-mm-dd') ");
		sql.append(" ) MM ");
		sql.append(" ) MN ");
		sql.append(" JOIN VIEW_ORG VO ON VO.ID=MN.CONTRACTOR_ID ");
		sql.append(" GROUP BY VO.ID,VO.NAME,MN.LV_ID ");
		sql.append(" ) MO ");
		sql.append(" ) MP ");
		sql.append(" GROUP BY MP.ID,MP.NAME ");
		sql.append(" ORDER BY SUM(MP.SCORE) DESC,MP.ID ");
		sql.append(" ) M ");
		return super.getSQLALL(sql.toString());
	}
}
