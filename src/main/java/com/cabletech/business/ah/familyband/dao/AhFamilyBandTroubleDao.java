package com.cabletech.business.ah.familyband.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.ah.familyband.model.AhFamilyBandTrouble;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.util.DateUtil;

/**
 * 
 * 家庭宽带巡检隐患登记表DAO
 * @author wj
 *
 */
@Repository
public class AhFamilyBandTroubleDao extends BaseDao<AhFamilyBandTrouble, String>{
	/**
	 * 
	 * 根据巡检记录查找 隐患
	 * @param parameters 参数封装
	 * @return List 
	 * 
	 */
	public List<Map<String,Object>> searchTroublesByRecod(Map<String,Object> parameters){
		String recodeId = (String) parameters.get("recodeId");
		String status = (String) parameters.get("status");
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from ah_familyband_trouble t where t.recodeid = '"+recodeId+"' ");
		if(StringUtils.isNotBlank(status)){
			sb.append(" and t.status = '"+status+"' ");
		}
		return this.jdbcTemplate.queryForList(sb.toString());
	}
	
	/**
	 * 
	 * 生成序号
	 * @param userInfo 用户
	 * @return String  
	 * 
	 */
	public String getOrderNumber(UserInfo userInfo){
		StringBuffer sb = new StringBuffer();
		StringBuffer pybf = new StringBuffer();
        char[] arr = userInfo.getRegionName().toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
                if (arr[i] > 128) {
                        try {
                                String[] _t = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                                if (_t != null) {
                                        pybf.append(_t[0].charAt(0));
                                }
                        } catch (BadHanyuPinyinOutputFormatCombination e) {
                                logger.error(e);
                        }
                } else {
                        pybf.append(arr[i]);
                }
        }
        String region =  (pybf.toString().trim()).toUpperCase();
        String day = DateUtil.DateToString(new Date(), "yyyyMMdd");
        String orderNumberPre = day+region;
		sb.append(" select count(*)+1 as ordernum from ah_familyband_trouble t where t.ordernumber like '"+orderNumberPre+"%' ");
		int index = this.jdbcTemplate.queryForInt(sb.toString());
		String order = String.valueOf(index);
		int pre = 4-order.length();
		for(int i = 0;i<pre;i++){
			order ="0"+order;
		}
		return orderNumberPre+order;
	}
}