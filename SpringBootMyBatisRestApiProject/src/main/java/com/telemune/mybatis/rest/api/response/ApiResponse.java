package com.telemune.mybatis.rest.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private String statusMessage;
    private int statusCode;
    private Boolean isSuccess;
    private Object data;
}
