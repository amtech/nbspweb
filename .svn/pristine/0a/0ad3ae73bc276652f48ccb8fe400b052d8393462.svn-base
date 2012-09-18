package com.cabletech;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cabletech.business.webservice.interfaces.ExternalWebService;

/**
 * 工单接口的WEBSERVICE客户端测试类
 * 
 * @author 杨隽 2012-08-16 创建
 * 
 */
public class WebServiceClient {
	/**
	 * 日志输出
	 */
	protected static Logger logger = Logger.getLogger("WebServiceClient");

	/**
	 * 客户端测试入口
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext-test.xml");
		ExternalWebService client = ctx.getBean("workOrderClient",
				ExternalWebService.class);
		StringBuffer xmlText = new StringBuffer();
		// xmlText = getPatrolGroupXmlReq();
		// xmlText = getUserInfoXmlReq();
		// xmlText = getWaitHandledTaskXmlReq();
		// xmlText = getHandledTaskXmlReq();
		// xmlText = getLoginUserXmlReq();
		// xmlText = getWorkorderDetailReq();
		// xmlText = getTaskTypeXmlReq();
		// xmlText = getWorkorderSigninReq();
		// xmlText = getWorkorderRefuseConfirmReq();
		// xmlText = getWorkorderTransferReq();
		// xmlText = getWorkorderReplyReq();
		// xmlText = getWorkorderReplyCheckReq();
		client.invokeService(xmlText.toString());
	}

	/**
	 * 获取巡检组请求XML字串
	 * 
	 * @return
	 */
	private static StringBuffer getPatrolGroupXmlReq() {
		StringBuffer xmlText = new StringBuffer("");
		xmlText.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlText.append("<request>");
		xmlText.append("<cmd>wlbpatrolGroupList</cmd>");
		xmlText.append("<usercode>zyhefei</usercode>");
		xmlText.append("<page>1</page>");
		xmlText.append("<pagesize>10</pagesize>");
		xmlText.append("<patrolgroupname></patrolgroupname>");
		xmlText.append("</request>");
		return xmlText;
	}

	/**
	 * 获取用户请求XML字串
	 * 
	 * @return
	 */
	private static StringBuffer getUserInfoXmlReq() {
		StringBuffer xmlText = new StringBuffer("");
		xmlText.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlText.append("<request>");
		xmlText.append("<cmd>wlbuserinfo</cmd>");
		xmlText.append("<usercode>zyhefei</usercode>");
		xmlText.append("<page>1</page>");
		xmlText.append("<pagesize>10</pagesize>");
		xmlText.append("<username></username>");
		xmlText.append("</request>");
		return xmlText;
	}

	/**
	 * 获取工单类型请求XML字串
	 * 
	 * @return
	 */
	private static StringBuffer getTaskTypeXmlReq() {
		StringBuffer xmlText = new StringBuffer("");
		xmlText.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlText.append("<request>");
		xmlText.append("<cmd>wlbtasktypelist</cmd>");
		xmlText.append("<usercode>zyhefei</usercode>");
		xmlText.append("</request>");
		return xmlText;
	}

	/**
	 * 获取待办工单列表请求XML字串
	 * 
	 * @return
	 */
	private static StringBuffer getWaitHandledTaskXmlReq() {
		StringBuffer xmlText = new StringBuffer("");
		xmlText.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlText.append("<request>");
		xmlText.append("<cmd>wlbtasklist</cmd>");
		xmlText.append("<usercode>zyhefei</usercode>");
		xmlText.append("<page>1</page>");
		xmlText.append("<pagesize>10</pagesize>");
		xmlText.append("</request>");
		return xmlText;
	}

	/**
	 * 获取已办工单列表请求XML字串
	 * 
	 * @return
	 */
	private static StringBuffer getHandledTaskXmlReq() {
		StringBuffer xmlText = new StringBuffer("");
		xmlText.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlText.append("<request>");
		xmlText.append("<cmd>wlbhandledtasklist</cmd>");
		xmlText.append("<usercode>zyhefei</usercode>");
		xmlText.append("<page>1</page>");
		xmlText.append("<pagesize>10</pagesize>");
		xmlText.append("</request>");
		return xmlText;
	}

	/**
	 * 获取登录用户请求XML字串
	 * 
	 * @return
	 */
	private static StringBuffer getLoginUserXmlReq() {
		StringBuffer xmlText = new StringBuffer("");
		xmlText.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlText.append("<request>");
		xmlText.append("<cmd>wlblogin</cmd>");
		xmlText.append("<usercode>zyhefei</usercode>");
		xmlText.append("<password>admin1111</password>");
		xmlText.append("</request>");
		return xmlText;
	}

	/**
	 * 获取工单详细信息请求XML字串
	 * 
	 * @return
	 */
	private static StringBuffer getWorkorderDetailReq() {
		StringBuffer xmlText = new StringBuffer("");
		xmlText.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlText.append("<request>");
		xmlText.append("<cmd>wlbtaskdetail</cmd>");
		xmlText.append("<usercode>zyhefei</usercode>");
		xmlText.append("<id>000000000041</id>");
		xmlText.append("<pid>000000010594</pid>");
		xmlText.append("<taskcode>AHSYDGS20120800001</taskcode>");
		xmlText.append("<step>70018</step>");
		xmlText.append("</request>");
		return xmlText;
	}

	/**
	 * 获取签收工单请求XML字串
	 * 
	 * @return
	 */
	private static StringBuffer getWorkorderSigninReq() {
		StringBuffer xmlText = new StringBuffer("");
		xmlText.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlText.append("<request>");
		xmlText.append("<cmd>wlbtaskdealsign</cmd>");
		xmlText.append("<usercode>zyhefei</usercode>");
		xmlText.append("<id>000000000063</id>");
		xmlText.append("<pid>000000010614</pid>");
		xmlText.append("<taskcode>AH-C30-001-120815-00002</taskcode>");
		xmlText.append("<step>70163</step>");
		xmlText.append("<patrolgroupcode></patrolgroupcode>");
		xmlText.append("<signflag>pass</signflag>");
		xmlText.append("<signdetail>aaabbbccc</signdetail>");
		xmlText.append("</request>");
		return xmlText;
	}

	/**
	 * 获取拒签确认工单请求XML字串
	 * 
	 * @return
	 */
	private static StringBuffer getWorkorderRefuseConfirmReq() {
		StringBuffer xmlText = new StringBuffer("");
		xmlText.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlText.append("<request>");
		xmlText.append("<cmd>wlbtaskdealrefuse</cmd>");
		xmlText.append("<usercode>zyhefei</usercode>");
		xmlText.append("<id>000000000063</id>");
		xmlText.append("<pid>000000010614</pid>");
		xmlText.append("<taskcode>AH-C30-001-120815-00002</taskcode>");
		xmlText.append("<step>70159</step>");
		xmlText.append("<result>reject</result>");
		xmlText.append("<remark>aaabbbccc</remark>");
		xmlText.append("<approvercode></approvercode>");
		xmlText.append("</request>");
		return xmlText;
	}

	/**
	 * 获取转派工单请求XML字串
	 * 
	 * @return
	 */
	private static StringBuffer getWorkorderTransferReq() {
		StringBuffer xmlText = new StringBuffer("");
		xmlText.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlText.append("<request>");
		xmlText.append("<cmd>wlbtaskdealsend</cmd>");
		xmlText.append("<usercode>zyhefei</usercode>");
		xmlText.append("<id>000000000063</id>");
		xmlText.append("<taskcode>AH-C30-001-120815-00002</taskcode>");
		xmlText.append("<receivercode>000000029250</receivercode>");
		xmlText.append("<tasktype>D02</tasktype>");
		xmlText.append("<taskdate>2012-08-17 03:34:32</taskdate>");
		xmlText.append("<taskrequest>aaabbbccc</taskrequest>");
		xmlText.append("<taskname>dddeeeaaa</taskname>");
		xmlText.append("</request>");
		return xmlText;
	}

	/**
	 * 获取回复工单请求XML字串
	 * 
	 * @return
	 */
	private static StringBuffer getWorkorderReplyReq() {
		StringBuffer xmlText = new StringBuffer("");
		xmlText.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlText.append("<request>");
		xmlText.append("<cmd>wlbtaskdealrequest</cmd>");
		xmlText.append("<usercode>zyhefei</usercode>");
		xmlText.append("<id>000000000041</id>");
		xmlText.append("<pid>000000010594</pid>");
		xmlText.append("<taskcode>AHSYDGS20120800001</taskcode>");
		xmlText.append("<step>70018</step>");
		xmlText.append("<issubmit>1</issubmit>");
		xmlText.append("<remark>aaabbb</remark>");
		xmlText.append("</request>");
		return xmlText;
	}

	/**
	 * 获取验证工单回复请求XML字串
	 * 
	 * @return
	 */
	private static StringBuffer getWorkorderReplyCheckReq() {
		StringBuffer xmlText = new StringBuffer("");
		xmlText.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlText.append("<request>");
		xmlText.append("<cmd>wlbtaskdealcheck</cmd>");
		xmlText.append("<usercode>tangjizhi</usercode>");
		xmlText.append("<id>000000000041</id>");
		xmlText.append("<pid>000000010594</pid>");
		xmlText.append("<taskcode>AHSYDGS20120800001</taskcode>");
		xmlText.append("<step>70203</step>");
		xmlText.append("<result>reject</result>");
		xmlText.append("<remark>aaabbbccc</remark>");
		xmlText.append("<approvercode></approvercode>");
		xmlText.append("</request>");
		return xmlText;
	}
}
