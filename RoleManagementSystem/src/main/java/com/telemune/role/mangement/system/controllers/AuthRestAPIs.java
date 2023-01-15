package com.telemune.role.mangement.system.controllers;

import com.telemune.role.mangement.system.common.Constants;
import com.telemune.role.mangement.system.common.JwtResponse;
import com.telemune.role.mangement.system.common.Response;
import com.telemune.role.mangement.system.dto.RoleDto;
import com.telemune.role.mangement.system.dto.UserDto;
import com.telemune.role.mangement.system.jwt.JwtProvider;
import com.telemune.role.mangement.system.service.HttpLinksService;
import com.telemune.role.mangement.system.service.RoleService;
import com.telemune.role.mangement.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
    private static final Logger logger = LogManager.getLogger(AuthRestAPIs.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private HttpLinksService httpLinksService;

    @PostMapping("/signin")
    public Response authenticateUser(@Valid @RequestBody UserDto userVO) {

        try {

            logger.info("Inside authenticateUser method in AuthRestAPIs class and --- user input --- " + userVO.toString());
            
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userVO.getUsername(), userVO.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtProvider.generateJwtToken(authentication);

            logger.debug(jwt);

            if (jwt != null) {

                logger.info("User Authenticate with user details ----" + userVO.toString());
                List<Object> userlst = new ArrayList<>();

                UserDto userVOdb = userService.findByUsername(userVO.getUsername());
                System.out.println(userVOdb);
                
                userlst.add(userVOdb);
                userlst.add(new JwtResponse(jwt));

                RoleDto roleVO = roleService.findById(userVOdb.getRoles().getRoleId());
                userlst.add(roleVO);

                logger.info("login Details ---" + userlst.toString());

                logger.info("Exit authenticateUser method in AuthRestAPIs class");
                return new Response(HttpStatus.OK, Constants.HTTP_STATUS_CODE_SCCUESS, userlst, "Login suceessfully",
                        Constants.STATUS_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE);

            }

            logger.info("User Authentication failed --User details-- " + userVO.toString());
            logger.info("Exit Authenticate user method in AuthRestAPIs class");

            return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
                    "failed login", Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
        } catch (Exception exp) {
            logger.error(" Exception ===>" + exp.toString());
            return new Response(HttpStatus.BAD_REQUEST, Constants.HTTP_STATUS_CODE_BAD_REQUEST, new ArrayList<>(),
                    exp.toString(), Constants.STATUS_FAILURE, Constants.STATUS_FAILURE_MESSAGE);
        }

    }
}
