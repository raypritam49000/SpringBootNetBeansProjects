package com.telemune.role.mangement.system.response;

public class ApiResponse {
    private int statusCode;
    private String statusMessage;
    private String message;
    private Object data;
    private Boolean isSuccess;

    public ApiResponse() {
    }
    
    public ApiResponse(int statusCode, String statusMessage, String message, Object data, Boolean isSuccess) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.message = message;
        this.data = data;
        this.isSuccess = isSuccess;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Override
    public String toString() {
        return "ApiResponse{" + "statusCode=" + statusCode + ", statusMessage=" + statusMessage + ", message=" + message + ", data=" + data + ", isSuccess=" + isSuccess + '}';
    }
    
    
}
