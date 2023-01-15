package com.telemune.role.mangement.system.dto;

public class HttpLinksDto {
    private Integer linkId;
    private String description;

    public Integer getLinkId() {
        return linkId;
    }

    public String getDescription() {
        return description;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "HttpLinksVO [linkId=" + linkId + ", description=" + description + "]";
    }
}
