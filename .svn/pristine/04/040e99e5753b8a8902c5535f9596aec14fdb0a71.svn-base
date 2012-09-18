package com.cabletech.business.assess.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.business.assess.dao.AssessExaminationDao;
import com.cabletech.business.assess.dao.AssessExaminationDetailDao;
import com.cabletech.business.assess.model.AssessExaminationResult;
import com.cabletech.business.assess.model.AssessMentDetail;
import com.cabletech.business.assess.service.AssessExaminationService;
import com.cabletech.business.resource.dao.ResourceInfoDao;
import com.cabletech.business.resource.model.ResourceInfo;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.Page;

/**
 * 现场检查-检查明细
 * 
 * @author wj 2012-07-31 创建
 * 
 */
@Service
@Transactional
public class AssessExaminationServiceImpl extends BaseServiceImpl implements AssessExaminationService{
	@Resource(name = "assessExaminationDao")
	private AssessExaminationDao assessExaminationDao;
	@Resource(name = "assessExaminationDetailDao")
	private AssessExaminationDetailDao assessExaminationDetailDao;
	@Resource(name = "resourceInfoDao")
	private ResourceInfoDao resourceInfoDao;

	@Override
	protected AssessExaminationDao getBaseDao() {
		return assessExaminationDao;
	}

	@Override
	public List<Map<String, Object>> queryAppraiseTables(Map<String, String> parameter) {
		return assessExaminationDao.queryAppraiseTables(parameter);
	}
	
	@Override
	public void save(AssessExaminationResult result) {
		 Double[] scores = result.getItemScore();
		 String[] ratingDescs = result.getRatingDesc();
		 String[] contentIds = result.getContentId();
		 double allSsores = 0;
		 for(int i = 0;i<scores.length;i++){
			 if(null!=scores[i]){
				 double score = Math.abs(scores[i])*-1;
				 allSsores +=score; 
			 }
		 }
		 result.setScore(allSsores);
		 assessExaminationDao.save(result);
		 String resultId = result.getId();
		 for(int i = 0;i<scores.length;i++){
			 if(null!=scores[i]){
				 double score = Math.abs(scores[i])*-1;
				 String ratingDesc = ratingDescs[i];
				 String contentId = contentIds[i];
				 AssessMentDetail detail = new AssessMentDetail();
				 detail.setResultId(resultId);
				 detail.setScore(score);
				 detail.setRatingDesc(ratingDesc);
				 detail.setContentId(contentId);
				 assessExaminationDetailDao.save(detail);
			 }
		 }
	}
	
	public String viewResourceInfo (String resourceId){
		String resName = "";
		ResourceInfo res = resourceInfoDao.view(resourceId);
		if(null!=res){
			resName = res.getResourceName();
		}
		return resName;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public AssessExaminationResult view(String id) {
		return (AssessExaminationResult) assessExaminationDao.get(id);
	}
	
	@Override
	public Page queryResultList(Map<String, String> parameters, Page page) {
		return assessExaminationDao.queryResultPage(parameters, page);
	}
	
	@Override
	public List<Map<String, Object>> queryResultList(
			Map<String, String> parameter) {
		return assessExaminationDao.queryResultList(parameter);
	}

	@Override
	public Map<String,Object> queryExaminationSummaryList(Map<String, String> parameters) {
		return assessExaminationDao.queryExaminationSummaryList(parameters);
	}
}