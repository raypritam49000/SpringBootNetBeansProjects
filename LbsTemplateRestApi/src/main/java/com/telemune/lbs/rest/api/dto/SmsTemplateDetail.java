package com.telemune.lbs.rest.api.dto;

public class SmsTemplateDetail {

    private Integer templateId;

    private String templateMessage;

    private Integer languageId;

    /**
     * @return the templateMessage
     */
    public String getTemplateMessage() {
        return templateMessage;
    }

    /**
     * @return the languageId
     */
    public Integer getLanguageId() {
        return languageId;
    }

    /**
     * @param templateMessage the templateMessage to set
     */
    public void setTemplateMessage(String templateMessage) {
        this.templateMessage = templateMessage;
    }

    /**
     * @param languageId the languageId to set
     */
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    /**
     * @return the templateId
     */
    public Integer getTemplateId() {
        return templateId;
    }

    /**
     * @param templateId the templateId to set
     */
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SmsTemplateDetail [templateId=" + templateId + ", templateMessage=" + templateMessage + ", languageId="
                + languageId + "]";
    }

}
