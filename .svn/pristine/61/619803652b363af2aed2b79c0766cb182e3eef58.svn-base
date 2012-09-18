package com.cabletech.business.assess.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.business.entity.UserInfo;
import com.cabletech.business.assess.model.AssessTemplate;
import com.cabletech.business.assess.model.AssessTemplateContent;
import com.cabletech.business.assess.model.AssessTemplateItem;
import com.cabletech.business.assess.service.AssessTemplateImportService;
import com.cabletech.business.excel.AutoGenerateParameter;
import com.cabletech.business.excel.AutoGenerateUtils;
import com.cabletech.business.excel.TableCellData;
import com.cabletech.common.base.BaseDao;
import com.cabletech.common.base.BaseServiceImpl;
import com.cabletech.common.util.ReflectionUtils;
import com.cabletech.common.xmlparse.ParseXmlTools;

/**
 * 考核模板导入业务接口实现
 * 
 * @author 杨隽 2012-07-31 创建
 * 
 */
@SuppressWarnings("all")
@Service
@Transactional
public class AssessTemplateImportServiceImpl extends
		BaseServiceImpl<AssessTemplate, String> implements
		AssessTemplateImportService {
	/**
	 * XML中开始列<prop>中id的值
	 */
	private static final String START_COL_KEY = "startCol";
	/**
	 * XML中开始行<prop>中id的值
	 */
	private static final String START_ROW_KEY = "startRow";
	/**
	 * XML中开始工作表<prop>中id的值
	 */
	private static final String START_SHEET_KEY = "startSheet";
	/**
	 * XML中考核表配置<root>中id的值
	 */
	private static final String ASSESS_CONFIG_KEY = "assessConfig";
	/**
	 * XML配置文件的解析工具业务服务
	 */
	@Resource(name = "parseXmlTools")
	private ParseXmlTools parseXmlTools;
	/**
	 * 模版-总体信息Dao
	 */
	@Resource(name = "assessTemplateDao")
	private BaseDao assessTemplateDao;
	/**
	 * 模版-项目Dao
	 */
	@Resource(name = "assessTemplateItemDao")
	private BaseDao assessTemplateItemDao;
	/**
	 * 模版-考核内容Dao
	 */
	@Resource(name = "assessTemplateContentDao")
	private BaseDao assessTemplateContentDao;

	@Override
	public Map<String, Object> createItemPreviewData(
			AssessTemplate assessTemplate, String filePath) {
		Element root = parseXmlTools.getImportXmlElement(ASSESS_CONFIG_KEY);
		AutoGenerateParameter parameter = getAutoGenerateParameter(root,
				assessTemplate.getTableType());
		Map<String, Object> map = AutoGenerateUtils.autoGenerate(filePath,
				parameter);
		processMap(root, assessTemplate.getTableType(), parameter, map);
		return map;
	}

	@Override
	public void importItemData(AssessTemplate assessTemplate, UserInfo user) {
		assessTemplate.setCreateDate(new Date());
		assessTemplate.setCreater(user.getPersonId());
		assessTemplateDao.save(assessTemplate);
		String templateId = assessTemplate.getId();
		String[] itemName = assessTemplate.getItemName();
		String[] name = assessTemplate.getName();
		Double[] benchmarkValue = assessTemplate.getBenchmarkValue();
		Double[] challengeValue = assessTemplate.getChallengeValue();
		Double[] weight = assessTemplate.getWeight();
		String[] demandDesc = assessTemplate.getDemandDesc();
		String[] evaluationCriterion = assessTemplate.getEvaluationCriterion();
		Map<String, String> keyMap = new HashMap<String, String>();
		for (int i = 0; i < itemName.length; i++) {
			String[] itemNames = itemName[i].split("_");
			String key = "";
			String itemId = "";
			for (int j = 0; j < itemNames.length; j++) {
				key += itemNames[j];
				String parentKey = "";
				for (int k = 0; k < j; k++) {
					parentKey += itemNames[k];
				}
				String parentItemId = "";
				if (keyMap.containsKey(parentKey)) {
					parentItemId = keyMap.get(parentKey);
				}
				if (!keyMap.containsKey(key)) {
					AssessTemplateItem item = new AssessTemplateItem();
					item.setItemName(itemNames[j]);
					item.setParentItemId(parentItemId);
					item.setTableId(templateId);
					assessTemplateItemDao.save(item);
					itemId = item.getId();
					keyMap.put(key, itemId);
					logger.info(itemId + "_" + item.getItemName() + "_"
							+ parentItemId);
				}
			}
			AssessTemplateContent content = new AssessTemplateContent();
			if (name != null) {
				content.setName(name[i]);
			}
			content.setItemId(keyMap.get(itemName[i].replaceAll("_", "")));
			if (benchmarkValue != null) {
				if (benchmarkValue[i] == null) {
					benchmarkValue[i] = new Double(0);
				}
				content.setBenchmarkValue(benchmarkValue[i]);
			}
			if (challengeValue != null) {
				if (challengeValue[i] == null) {
					challengeValue[i] = new Double(0);
				}
				content.setChallengeValue(challengeValue[i]);
			}
			if (weight != null) {
				if (weight[i] == null) {
					weight[i] = new Double(0);
				}
				content.setWeight(weight[i]);
			}
			content.setDemandDesc(demandDesc[i]);
			content.setEvaluationCriterion(evaluationCriterion[i]);
			assessTemplateContentDao.save(content);
			if (StringUtils.isBlank(content.getName())) {
				logger.info(content.getItemId() + "_" + content.getDemandDesc());
			} else {
				logger.info(content.getItemId() + "_" + content.getName());
			}
		}
	}

	@Override
	protected BaseDao<AssessTemplate, String> getBaseDao() {
		return null;
	}

	/**
	 * 从xml中读取自动生成Table的参数信息
	 * 
	 * @param root
	 *            Element
	 * @param type
	 *            String
	 * @return AutoGenerateParameter
	 */
	private AutoGenerateParameter getAutoGenerateParameter(Element root,
			String type) {
		Element tableConfigElem = parseXmlTools.getChildElementById(root,
				ParseXmlTools.CONSTANT_ELEMENT_KEY, type);
		AutoGenerateParameter parameter = new AutoGenerateParameter();
		Element sheetConfigElem = parseXmlTools.getChildElementById(
				tableConfigElem, ParseXmlTools.PROP_ELEMENT_KEY,
				START_SHEET_KEY);
		parameter
				.setSheetIndex(Integer.parseInt(sheetConfigElem.getTextTrim()));
		Element rowConfigElem = parseXmlTools.getChildElementById(
				tableConfigElem, ParseXmlTools.PROP_ELEMENT_KEY, START_ROW_KEY);
		parameter.setStartRow(Integer.parseInt(rowConfigElem.getTextTrim()));
		Element colConfigElem = parseXmlTools.getChildElementById(
				tableConfigElem, ParseXmlTools.PROP_ELEMENT_KEY, START_COL_KEY);
		parameter.setStartCol(Integer.parseInt(colConfigElem.getTextTrim()));
		parameter.setDataStartRow(parameter.getStartRow() + 1);
		return parameter;
	}

	/**
	 * 数据后期处理
	 * 
	 * @param type
	 *            Element
	 * @param root
	 *            String
	 * @param parameter
	 *            AutoGenerateParameter
	 * @param map
	 *            Map<String,Object>
	 */
	private void processMap(Element root, String type,
			AutoGenerateParameter parameter, Map<String, Object> map) {
		boolean flag = true;
		Element tableConfigElem = parseXmlTools.getChildElementById(root,
				ParseXmlTools.CONSTANT_ELEMENT_KEY, type);
		List<List<TableCellData>> dataList = (List<List<TableCellData>>) map
				.get("dataList");
		if (CollectionUtils.isEmpty(dataList)) {
			return;
		}
		for (int i = 0; i < dataList.size(); i++) {
			List<TableCellData> rowList = dataList.get(i);
			if (CollectionUtils.isEmpty(rowList)) {
				continue;
			}
			String errorMessage = "";
			for (int j = 0; j < rowList.size(); j++) {
				TableCellData data = rowList.get(j);
				Element dataConfigElem = parseXmlTools.getChildElementById(
						tableConfigElem, ParseXmlTools.PROP_ELEMENT_KEY, data
								.getColumnInputName().replaceAll("\\s*", ""));
				if (dataConfigElem != null) {
					if (i >= parameter.getDataStartRow()
							- parameter.getStartRow()) {
						flag = checkData(data, dataConfigElem) && flag;
					}
					data.setColumnInputName(dataConfigElem.getTextTrim());
				} else {
					data.setColumnInputName("");
				}
				errorMessage += data.getErrorMessage();
				if (j < rowList.size() - 1
						&& StringUtils.isNotBlank(errorMessage)) {
					errorMessage += " ";
				}
			}
			if (i >= parameter.getDataStartRow() - parameter.getStartRow()) {
				TableCellData data = new TableCellData();
				data.setCellValue(errorMessage);
				data.setColIndex(rowList.size());
				data.setRowIndex(i);
				if(StringUtils.isNotBlank(errorMessage)){
					data.setCellColor("red");
				}
				rowList.add(data);
			}
		}
		if (!flag) {
			map.put("validData", "0");
		} else {
			map.put("validData", "1");
		}
	}

	/**
	 * 进行数据校验
	 * 
	 * @param data
	 *            TableCellData
	 * @param dataConfigElem
	 *            Element
	 * @return
	 */
	private boolean checkData(TableCellData data, Element dataConfigElem) {
		boolean flag = true;
		String rule = dataConfigElem
				.attributeValue(ParseXmlTools.RULE_ATTRIBUTE_KEY);
		if (StringUtils.isBlank(rule)) {
			return flag;
		}
		flag = (Boolean) ReflectionUtils.invokeMethod(this, rule,
				new Class[] { TableCellData.class },
				new TableCellData[] { data });
		return flag;
	}

	/**
	 * 判断值是否为空
	 * 
	 * @param data
	 *            TableCellData
	 * @return
	 */
	private boolean isNotNull(TableCellData data) {
		if (StringUtils.isNotBlank(data.getCellValue())) {
			return true;
		}
		data.setErrorMessage(data.getColumnInputName() + "不能为空！");
		return false;
	}

	/**
	 * 判断值是否为数字
	 * 
	 * @param data
	 *            TableCellData
	 * @return
	 */
	private boolean isNumber(TableCellData data) {
		String regex = "^(-)?\\d+(\\.\\d+)?$";
		if (StringUtils.isBlank(data.getCellValue())) {
			return true;
		}
		if (data.getCellValue().matches(regex)) {
			return true;
		}
		data.setErrorMessage(data.getColumnInputName() + "必须为数字！");
		return false;
	}
}
