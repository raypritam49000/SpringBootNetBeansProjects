package com.telemune.role.mangement.system.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "marketplace_roles")
public class Roles implements Serializable {

    @Id
    @Column(name = "ROLE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(name = "ROLE_NAME", unique = true, nullable = false)
    private String roleName;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(cascade = javax.persistence.CascadeType.PERSIST)
    @JoinTable(name = "web_access", joinColumns = @JoinColumn(name = "ROLE_ID"), 
            inverseJoinColumns = @JoinColumn(name = "LINK_ID"))
    private List<HttpLinks> httpLinks;

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

    /**
     * @return the httpLinks
     */
    public List<HttpLinks> getHttpLinks() {
        return httpLinks;
    }

    /**
     * @param httpLinks the httpLinks to set
     */
    public void setHttpLinks(List<HttpLinks> httpLinks) {
        this.httpLinks = httpLinks;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MarketPlaceRoles [roleId=" + roleId + ", roleName=" + roleName + ", description=" + description
                + ", httpLinks=" + httpLinks + "]";
    }

}
