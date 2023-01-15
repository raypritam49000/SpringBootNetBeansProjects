package com.telemune.role.mangement.system.dto;

import java.util.List;

public class RoleDto {

    private Integer roleId;

    private String roleName;

    private String description;

    private List<HttpLinksDto> httpLinkslst;

    /**
     * @return the roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public List<HttpLinksDto> getHttpLinkslst() {
        return httpLinkslst;
    }

    public void setHttpLinkslst(List<HttpLinksDto> httpLinkslst) {
        this.httpLinkslst = httpLinkslst;
    }
    
    

    @Override
    public String toString() {
        return "RoleDto{" + "roleId=" + roleId + ", roleName=" + roleName + ", description=" + description + ", httpLinkslst=" + httpLinkslst + '}';
    }

    

}
