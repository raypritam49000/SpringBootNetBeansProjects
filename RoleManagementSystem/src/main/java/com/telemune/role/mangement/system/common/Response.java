package com.telemune.role.mangement.system.common;

import java.util.List;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response {

    private HttpStatus httpStatus;
    private Integer httpStatusCode;
    private String errorMessage;
    private String message;
    private List<?> body;
    private Integer status;
    private String statusMessage;

    /**
     * @param httpStatus This param define the status of http request
     * @param httpStatusCode This param define the httpStatusCode of http
     * request
     * @param errorMessage This param define the errorMessage of http request
     * @param body This param define the of body request
     * @param status This param define the status of http request
     * @param statusMessage This param define the statusMessage of http request
     */
    public Response(HttpStatus httpStatus, Integer httpStatusCode, String errorMessage, List<?> body, Integer status,
            String statusMessage) {
        this.httpStatus = httpStatus;
        this.httpStatusCode = httpStatusCode;
        this.errorMessage = errorMessage;
        this.body = body;
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public Response() {

    }

    /**
     * @param httpStatus This param define the status of http request
     * @param httpStatusCode This param define the httpStatusCode of http
     * request
     * @param message This param define the errorMessage of http request
     * @param body This param define the of body request
     * @param status This param define the status of http request
     * @param statusMessage This param define the statusMessage of http request
     */
    public Response(HttpStatus httpStatus, Integer httpStatusCode, List<?> body, String message, Integer status,
            String statusMessage) {
        this.httpStatus = httpStatus;
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.body = body;
        this.status = status;
        this.statusMessage = statusMessage;
    }

    /**
     * @return the httpStatus
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * @return the httpStatusCode
     */
    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param httpStatus the httpStatus to set
     */
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    /**
     * @param httpStatusCode the httpStatusCode to set
     */
    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * @return the body
     */
    public List<?> getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(List<?> body) {
        this.body = body;
    }

}
