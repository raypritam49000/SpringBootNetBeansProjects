package com.telemune.lbs.rest.api.embedded;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LbsTemplateIds implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "TEMPLATE_ID", nullable = false)
    private Integer templateId;
    @Column(name = "LANGUAGE_ID ", nullable = false)
    private Integer languageId;

    public LbsTemplateIds() {
    }

    public LbsTemplateIds(Integer templateId, Integer languageId) {
        this.languageId = languageId;
        this.templateId = templateId;
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

    /**
     * @return the languageId
     */
    public Integer getLanguageId() {
        return languageId;
    }

    /**
     * @param languageId the languageId to set
     */
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.templateId);
        hash = 47 * hash + Objects.hashCode(this.languageId);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LbsTemplateIds that = (LbsTemplateIds) o;

        if (!templateId.equals(that.templateId)) {
            return false;
        }
        return languageId.equals(that.languageId);
    }

    @Override
    public String toString() {
        return "LbsTemplateIds [templateId=" + templateId + ", languageId=" + languageId + "]";
    }

}
