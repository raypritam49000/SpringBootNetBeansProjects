package com.telemune.role.mangement.system.controllers;

import com.telemune.role.mangement.system.dto.UserDto;
import com.telemune.role.mangement.system.response.ApiResponse;
import com.telemune.role.mangement.system.service.UserParentService;
import com.telemune.role.mangement.system.service.UserService;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/user_parent")
public class UserParentController {
    private static final Logger LOGGER = LogManager.getLogger(UserParentController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserParentService userParentService;

    @GetMapping("/current_user_parent")
    public ResponseEntity<ApiResponse> home(HttpServletRequest request) {
        try {
            String username = request.getUserPrincipal().getName();
            UserDto userDto = this.userService.findByUsername(username);
            return userParentService.getAllDetailsById(userDto.getUserId())
                    .map((data) -> ResponseEntity.status(200).body(new ApiResponse(200, "SUCCESS", "UserParent Deatils", data, true)))
                    .orElse(ResponseEntity.status(404).body(new ApiResponse(404, "NOT_FOUND", "UserParent Not Deatils", Arrays.asList(), false)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResponse> getAllDetailsById(@PathVariable("id") Long id) {
        try {
            return userParentService.getAllDetailsById(id)
                    .map((data) -> ResponseEntity.status(200).body(new ApiResponse(200, "SUCCESS", "UserParent Deatils", data, true)))
                    .orElse(ResponseEntity.status(404).body(new ApiResponse(404, "NOT_FOUND", "UserParent Not Deatils", Arrays.asList(), false)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/siblings/{id}")
    public ResponseEntity<ApiResponse> getAllSiblings(@PathVariable("id") Long id) {
        try {
            return userParentService.getAllSiblings(id)
                    .map((data) -> ResponseEntity.status(200).body(new ApiResponse(200, "SUCCESS", "UserParent Deatils", data, true)))
                    .orElse(ResponseEntity.status(404).body(new ApiResponse(404, "NOT_FOUND", "UserParent Not Deatils", Arrays.asList(), false)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", Arrays.asList(), false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
