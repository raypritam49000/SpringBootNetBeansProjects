package com.onpassive.rest.api.response;

import java.util.List;
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
    private List<?> data;
    private Boolean isSuccess;
}
