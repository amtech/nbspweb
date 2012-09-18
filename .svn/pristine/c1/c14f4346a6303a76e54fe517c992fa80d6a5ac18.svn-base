package com.cabletech.business.base.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.jms.JmsSmParameter;
import com.cabletech.baseinfo.jms.JmsMessageTemplateService;
import com.cabletech.baseinfo.jms.JmsSmMessageSender;
import com.cabletech.baseinfo.shortmessage.RmiService;
import com.cabletech.baseinfo.shortmessage.SmSendClientService;
import com.cabletech.business.base.dao.SmParameterDao;
import com.cabletech.business.base.model.SmParameter;

/**
 * 发送短信客户端服务
 * 
 * @author 杨隽 2012-03-26 创建
 * @author 杨隽 2012-03-28 添加根据“是否发送短信标志”进行发送短信的功能
 * 
 */
@Service
public class SmSendService {
	/**
	 * 日志输出
	 */
	private Logger logger = Logger.getLogger("SmSendService");
	/**
	 * 发送信息模板服务
	 */
	@Resource(name = "jmsMessageTemplateService")
	private JmsMessageTemplateService messageTemplateService;
	/**
	 * 发送信息业务
	 */
	@Resource(name = "smMessageSender")
	private JmsSmMessageSender sender;
	/**
	 * 发送短信记录Dao
	 */
	@Resource(name = "smParameterDao")
	private SmParameterDao smParameterDao;

	/**
	 * 执行发送短信信息
	 * 
	 * @param smParameter
	 *            SmParameter 短信发送参数
	 */
	public void sendMessage(SmParameter smParameter) {
		try {
			RmiService rmiService = RmiService.getInstance();
			SmSendClientService clientService = new SmSendClientService(
					rmiService);
			String xmlFileId = smParameter.getXmlFileId();
			String simId = smParameter.getSimId();
			if (StringUtils.isNotBlank(simId)) {
				simId = simId.trim();
			}
			String xmlMessageId = smParameter.getXmlMessageId();
			String[] contentParameters = smParameter.getContentParameters();
			if (smParameter.isSentSm()) {
				clientService.sendMessage(xmlFileId, simId, xmlMessageId,
						contentParameters);
			}
		} catch (Exception ex) {
			logger.error("发送短信失败:", ex);
		}
	}

	/**
	 * 执行发送短信信息到Jms
	 * 
	 * @param smParameter
	 *            SmParameter 短信发送参数
	 */
	public void sendMessageToJms(SmParameter smParameter) {
		try {
			String xmlFileId = smParameter.getXmlFileId();
			String simId = smParameter.getSimId();
			if (StringUtils.isNotBlank(simId)) {
				simId = simId.trim();
			}
			String xmlMessageId = smParameter.getXmlMessageId();
			String[] contentParameters = smParameter.getContentParameters();
			if (smParameter.isSentSm()) {
				String content = messageTemplateService.getMessageContent(
						xmlFileId, xmlMessageId, contentParameters);
				JmsSmParameter parameter = JmsSmParameter
						.getInstanceForNotNeedResponse(simId, content);
				sender.sendMessage(parameter);
			}
		} catch (Exception ex) {
			logger.error("发送短信失败:", ex);
		}
	}

	/**
	 * 将要发送的信息写入到待发送信息的数据表中
	 * 
	 * @param smParameter
	 *            SmParameter
	 */
	@Transactional
	public void writeMessage(SmParameter smParameter) {
		if (StringUtils.isBlank(smParameter.getSimId())) {
			return;
		}
		List<Map<String, Object>> smValidList = smParameterDao
				.getSmValidList(smParameter);
		if (CollectionUtils.isEmpty(smValidList)) {
			return;
		}
		List<Map<String, Object>> smTimeSetList = smParameterDao
				.getSmTimeSetList(smParameter);
		if (CollectionUtils.isEmpty(smTimeSetList)) {
			return;
		}
		for (int i = 0; i < smValidList.size(); i++) {
			Map<String, Object> smValidMap = smValidList.get(i);
			if (MapUtils.isEmpty(smValidMap)) {
				continue;
			}
			int timeOutNum = 0;
			for (int j = 0; j < smTimeSetList.size(); j++) {
				Map<String, Object> smTimeSetMap = smTimeSetList.get(j);
				if (MapUtils.isEmpty(smTimeSetMap)) {
					continue;
				}
				if (smValidMap.get("SMS_TYPE").equals(
						smTimeSetMap.get("SMS_TYPE"))) {
					timeOutNum = Integer.parseInt((String) smTimeSetMap
							.get("TIMEOUT_NUM_DIS"));
					break;
				}
			}
			Calendar c = Calendar.getInstance();
			c.setTime(smParameter.getHandleLimit());
			if (SmParameter.CLOSED_DEADLINE_SMS_TYPE.equals(smValidMap
					.get("SMS_TYPE"))) {
				c.add(Calendar.HOUR_OF_DAY, -timeOutNum);
			}
			if (SmParameter.TIMEOUT_UPGRADE_SMS_TYPE.equals(smValidMap
					.get("SMS_TYPE"))) {
				c.add(Calendar.HOUR_OF_DAY, timeOutNum);
			}
			smParameter.setHandleLimit(c.getTime());
			SmParameter newSmParameter = new SmParameter();
			try {
				BeanUtils.copyProperties(newSmParameter, smParameter);
				smParameterDao.save(newSmParameter);
			} catch (Exception ex) {
				logger.error("", ex);
			}
		}
	}
}
