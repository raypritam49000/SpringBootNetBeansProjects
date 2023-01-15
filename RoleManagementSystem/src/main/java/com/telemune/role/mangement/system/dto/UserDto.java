package com.telemune.role.mangement.system.dto;

public class UserDto {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String mobileNumber;
    private String firstLogin;
    private String userType;
    private String createdBy;
    private RoleDto roles;

    public UserDto(String username, String password, String email, String mobileNumber, String firstLogin, String userType, String createdBy, RoleDto roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.firstLogin = firstLogin;
        this.userType = userType;
        this.createdBy = createdBy;
        this.roles = roles;
    }

    public UserDto() {
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

    public RoleDto getRoles() {
        return roles;
    }

    public void setRoles(RoleDto roles) {
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
        return "UserDto{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email + ", mobileNumber=" + mobileNumber + ", firstLogin=" + firstLogin + ", userType=" + userType + ", createdBy=" + createdBy + ", roles=" + roles + '}';
    }

}
