package com.telemune.role.mangement.system.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "marketplace_adminuser")
public class AdminUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;
    
    @Column(name = "USER_NAME", nullable = false,unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MOBILE_NUM")
    private String mobileNumber;

    @Column(name = "FIRST_LOGIN")
    private String firstLogin;

    @Column(name = "USER_TYPE", unique = true, nullable = false)
    private String userType;

    @Column(name = "CREATED_BY", unique = true, nullable = false)
    private String createdBy;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ROLE_ID")
    private Roles roles;

    public AdminUser(String username, String password, String email, String mobileNumber, String firstLogin, String userType, String createdBy, Roles roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.firstLogin = firstLogin;
        this.userType = userType;
        this.createdBy = createdBy;
        this.roles = roles;
    }

    public AdminUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(String firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AdminUser{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email + ", mobileNumber=" + mobileNumber + ", firstLogin=" + firstLogin + ", userType=" + userType + ", createdBy=" + createdBy + ", roles=" + roles + '}';
    }
}
