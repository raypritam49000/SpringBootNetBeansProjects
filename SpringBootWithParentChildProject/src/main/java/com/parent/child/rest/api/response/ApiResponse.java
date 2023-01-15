package com.parent.child.rest.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private int statusCode;
    private String statusMessage;
    private String message;
    private Object data;
    private Boolean isSuccess;
}
