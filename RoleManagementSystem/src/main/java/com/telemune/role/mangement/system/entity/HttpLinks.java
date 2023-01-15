package com.telemune.role.mangement.system.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "http_links")
public class HttpLinks implements Serializable {

    @Id
    @Column(name = "LINK_ID")
    private Integer linkId;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    /**
     * @return the linkId
     */
    public Integer getLinkId() {
        return linkId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param linkId the linkId to set
     */
    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HttpLinks [linkId=" + linkId + ", description=" + description + "]";
    }

}
