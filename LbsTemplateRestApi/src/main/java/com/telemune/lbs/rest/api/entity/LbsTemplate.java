package com.telemune.lbs.rest.api.entity;

import com.telemune.lbs.rest.api.embedded.LbsTemplateIds;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lbs_templates")
public class LbsTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private LbsTemplateIds lbsTemplateIds;

    @Column(name = "TEMPLATE_TYPE", nullable = false)
    private Integer templateType;

    @Column(name = "TEMPLATE_MESSAGE", nullable = false)
    private String templateMessage;

    @Column(name = "TOKENS_ALLOWED", nullable = false)
    private String tokensAllowed;

    @Column(name = "TEMPLATE_DESCRIPTION", nullable = false)
    private String templateDescription;

    @Column(name = "TEMPLATE_NAME", nullable = false)
    private String templateName;

    /**
     * @return the templateType
     */
    public Integer getTemplateType() {
        return templateType;
    }

    /**
     * @param templateType the templateType to set
     */
    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    /**
     * @return the templateMessage
     */
    public String getTemplateMessage() {
        return templateMessage;
    }

    /**
     * @param templateMessage the templateMessage to set
     */
    public void setTemplateMessage(String templateMessage) {
        this.templateMessage = templateMessage;
    }

    /**
     * @return the tokensAllowed
     */
    public String getTokensAllowed() {
        return tokensAllowed;
    }

    /**
     * @param tokensAllowed the tokensAllowed to set
     */
    public void setTokensAllowed(String tokensAllowed) {
        this.tokensAllowed = tokensAllowed;
    }

    /**
     * @return the templateDescription
     */
    public String getTemplateDescription() {
        return templateDescription;
    }

    /**
     * @param templateDescription the templateDescription to set
     */
    public void setTemplateDescription(String templateDescription) {
        this.templateDescription = templateDescription;
    }

    /**
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName the templateName to set
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * @return the lbsTemplateIds
     */
    public LbsTemplateIds getLbsTemplateIds() {
        return lbsTemplateIds;
    }

    /**
     * @param lbsTemplateIds the lbsTemplateIds to set
     */
    public void setLbsTemplateIds(LbsTemplateIds lbsTemplateIds) {
        this.lbsTemplateIds = lbsTemplateIds;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LbsTemplate [lbsTemplateIds=" + lbsTemplateIds + ", templateType=" + templateType + ", templateMessage="
                + templateMessage + ", tokensAllowed=" + tokensAllowed + ", templateDescription=" + templateDescription
                + ", templateName=" + templateName + "]";
    }

}
