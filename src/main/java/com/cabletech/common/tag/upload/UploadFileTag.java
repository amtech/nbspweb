package com.cabletech.common.tag.upload;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.cabletech.business.base.model.UploadFile;
import com.cabletech.business.base.service.UploadFileService;
import com.cabletech.common.tag.AbstractTagSupport;
import com.cabletech.common.util.SpringContext;

/**
 * 上传文件Tag
 * 
 * @author zhaobi
 * 
 */
public class UploadFileTag extends AbstractTagSupport {
	private static final long serialVersionUID = 1L;
	/**
	 * css样式
	 */
	private String cssClass = "";
	/**
	 * 实体ID
	 */
	private String entityId = "";
	/**
	 * 实体类型
	 */
	private String entityType = "";
	/**
	 * 是否可用
	 */
	private String useable = "1";
	/**
	 * 上传文件
	 */
	private List<FileItem> value;
	/**
	 * 是否输出脚本
	 */
	private String isOutScript = "true";
	/**
	 * 状态 add;edit;look
	 */
	private String state = "";// add； edit；look；
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	@SuppressWarnings("unchecked")
	public StringBuffer writeHtmlString() {
		HttpSession session = this.pageContext.getSession();
		String ctx=this.pageContext.getServletContext().getContextPath();
		// 上传文件服务
		UploadFileService  uploadFileService = (UploadFileService)SpringContext.getApplicationContext().getBean("uploadFileServiceImpl");
		session.setAttribute("FILES", null);
	    //文件信息
		List<UploadFile> fileList=null;
		try {
			fileList = uploadFileService.getFilesByIds(entityId,entityType,getUseable());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger .error(e);
		}
		StringBuffer builder = new StringBuffer("");
		if ("look".equals(state)) {
			builder.append("<div id=\"lookfile\">");
			int i = 0;
			for (UploadFile upfile : fileList) {

				builder.append("<a class=\"upload\" href='" + ctx + "/download!download.action?fileid="
						+ upfile.getFileId()
						+ "'><img border='0' src='" + ctx + "/css/images/attachment.gif' width='15' height='15' />"
						+ upfile.getOriginalName() + "</a><br>");
				i++;
			}
			if (i == 0) {
				builder.append("<font style='color:red'>无附件</font>");
			}
			builder.append("</div>");
		} else {
			if ("true".equals(isOutScript)) {
				StringBuilder script = new StringBuilder();
				script.append("<script type='text/javascript' src='" + ctx + "/js/jQuery/jupload/ajaxupload.js'></script>");
				script.append("<script type='text/javascript' defer='defer'>");
				script.append("function removefile(delid){jQuery.ajax({type:\"POST\",url:'" + ctx + "/UploadServlet?type=remove&delid='+delid,cache : false,success:function(result){jQuery('#newfile').empty();jQuery('#newfile').html(result);}	});	}");
				script.append("function del(delid){jQuery.ajax({type:\"POST\",url:'" + ctx + "/UploadServlet?type=del&delid='+delid,cache : false,success:function(result){jQuery('#oldfile').empty();jQuery('#oldfile').html(result);}	});	}");

				script.append("jQuery(document).ready(function(){var button = jQuery('#upload'), interval;new AjaxUpload(button, {action: '" + ctx + "/UploadServlet?type=upload', name: 'myfile',");
				script.append("onSubmit : function(file, ext){	button.text('正在上传');	jQuery('#msg').empty();this.disable();	interval = window.setInterval(function(){var text = button.text();if (text.length < 13){button.text(text + '.');}");
				script.append("else {button.text('正在上传');}}, 200);},");
				script.append("onComplete: function(file, response){button.text('添加附件');window.clearInterval(interval);this.enable();if(response.indexOf(\"msg:\") == -1){jQuery('#msg').empty();jQuery('#newfile').empty();");
				script.append("jQuery('#newfile').html(response);}else{error = response.split(\":\");jQuery('#msg').html(error[1]);	}}});});");
				script.append("/*]]>*/</script>");
				builder.append(script);
			}
			builder.append("<a href=\"javascript:;\" id=\"upload\" class=\"upload\">添加附件</a><span id=\"msg\" style=\"color:red;\"></span>");
			if ("edit".equals(state)) {
				session.setAttribute("OLDFILE", fileList);
				builder.append("<div id=\"oldfile\">");
				int i = 0;
				for (UploadFile upfile : fileList) {
					builder.append("<a href='javascript:;' onclick='del(\""
							+ i
							+ "\")'><img src=\"" + ctx + "/js/jQuery/jupload/del.gif\" border=\"0\" alt=\"移除\"/></a>");
					builder.append("<a class=\"oldfile\" href='" + ctx + "/download!download.action?fileid="
							+ upfile.getFileId()
							+ "'><img border='0' src='" + ctx + "/css/images/attachment.gif' width='15' height='15'>"
							+ upfile.getOriginalName() + "</a><br>");
					i++;
				}
				builder.append("</div>");
			}
			builder.append("<div id=\"newfile\" class=\"newfile\">");
			if (value != null) {
				session.setAttribute("FILES", value);
				int i = 0;
				for (FileItem item : value) {
					builder.append("<a href='javascript:;' onclick='remove(\""
							+ i
							+ "\")'><img src=\"" + ctx + "/js/jQuery/juploader/del.gif\"/  border=\"0\" alt=\"移除\"></a>  "
							+ item.getName() + "<br>");// 终于成功了,还不到你的上传文件中看看,你要的东西都到齐了吗
					i++;
				}
			}
			builder.append("</div>");
		}
		return builder;
		}

	/**
	 * @return the cssClass
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * @param cssClass the cssClass to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * @return the entityId
	 */
	public String getEntityId() {
		return entityId;
	}

	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	/**
	 * @return the entityType
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * @param entityType the entityType to set
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * @return the useable
	 */
	public String getUseable() {
		return useable;
	}

	/**
	 * @param useable the useable to set
	 */
	public void setUseable(String useable) {
		this.useable = useable;
	}

	/**
	 * @return the value
	 */
	public List<FileItem> getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(List<FileItem> value) {
		this.value = value;
	}

	/**
	 * @return the isOutScript
	 */
	public String getIsOutScript() {
		return isOutScript;
	}

	/**
	 * @param isOutScript the isOutScript to set
	 */
	public void setIsOutScript(String isOutScript) {
		this.isOutScript = isOutScript;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	
}
