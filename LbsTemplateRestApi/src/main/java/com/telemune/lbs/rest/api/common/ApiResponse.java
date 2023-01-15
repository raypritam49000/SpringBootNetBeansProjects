package com.telemune.lbs.rest.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ApiResponse {

    private String message;
    private String statusMessage;
    private Integer statusCode;
    private Boolean isSuccess;
    private Object data;

}
