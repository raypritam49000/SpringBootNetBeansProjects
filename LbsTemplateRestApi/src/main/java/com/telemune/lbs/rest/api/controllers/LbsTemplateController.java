package com.telemune.lbs.rest.api.controllers;

import com.telemune.lbs.rest.api.common.ApiResponse;
import com.telemune.lbs.rest.api.services.ILbsTemplateService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/lbstemp")
public class LbsTemplateController {

    @Autowired
    private ILbsTemplateService lbsTemplateService;

    @DeleteMapping("/{Id}/")
    public ResponseEntity<ApiResponse> deleteByTempId(@PathVariable(name = "Id") Integer tempId) {
        try {
            if (null != tempId) {
                if (lbsTemplateService.templateDeleteById(tempId)) {
                    return new ResponseEntity<>(new ApiResponse("LbsTemplate are Deleted", "SUCCESS", 200, Boolean.TRUE, Arrays.asList()), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ApiResponse("LbsTemplate Not Found", "not found", 404, Boolean.FALSE, Arrays.asList()), HttpStatus.NOT_FOUND);
                }

            } else {
                return new ResponseEntity<>(new ApiResponse("LbsTemplate Not Found", "not found", 404, Boolean.FALSE, Arrays.asList()), HttpStatus.NOT_FOUND);
            }

        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse("Server Error", "INTERNAL_SERVER_ERROR", 501, Boolean.FALSE, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
