package com.telemune.lbs.rest.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class LbsTemplateDto {

	private Integer templateId;

	private Integer languageId;

	private Integer templateType;

	private String templateMessage;

	private String tokensAllowed;    

	private String templateDescription;

	private String templateName;
	
	private List<SmsTemplateDetail>  smsTemplateDetaillst;

	/**
	 * @return the templateId
	 */
	public Integer getTemplateId() {
		return templateId;
	}

	/**
	 * @return the languageId
	 */
	public Integer getLanguageId() {
		return languageId;
	}

	/**
	 * @return the templateType
	 */
	public Integer getTemplateType() {
		return templateType;
	}

	/**
	 * @return the templateMessage
	 */
	public String getTemplateMessage() {
		return templateMessage;
	}

	/**
	 * @return the tokensAllowed
	 */
	public String getTokensAllowed() {
		return tokensAllowed;
	}

	/**
	 * @return the templateDescription
	 */
	public String getTemplateDescription() {
		return templateDescription;
	}

	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @return the smsTemplateDetaillst
	 */
	public List<SmsTemplateDetail> getSmsTemplateDetaillst() {
		return smsTemplateDetaillst;
	}

	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	/**
	 * @param languageId the languageId to set
	 */
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	/**
	 * @param templateType the templateType to set
	 */
	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	/**
	 * @param templateMessage the templateMessage to set
	 */
	public void setTemplateMessage(String templateMessage) {
		this.templateMessage = templateMessage;
	}

	/**
	 * @param tokensAllowed the tokensAllowed to set
	 */
	public void setTokensAllowed(String tokensAllowed) {
		this.tokensAllowed = tokensAllowed;
	}

	/**
	 * @param templateDescription the templateDescription to set
	 */
	public void setTemplateDescription(String templateDescription) {
		this.templateDescription = templateDescription;
	}

	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * @param smsTemplateDetaillst the smsTemplateDetaillst to set
	 */
	public void setSmsTemplateDetaillst(List<SmsTemplateDetail> smsTemplateDetaillst) {
		this.smsTemplateDetaillst = smsTemplateDetaillst;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LbsTemplateVO [templateId=" + templateId + ", languageId=" + languageId + ", templateType="
				+ templateType + ", templateMessage=" + templateMessage + ", tokensAllowed=" + tokensAllowed
				+ ", templateDescription=" + templateDescription + ", templateName=" + templateName
				+ ", smsTemplateDetaillst=" + smsTemplateDetaillst + "]";
	}	
	
	

}
