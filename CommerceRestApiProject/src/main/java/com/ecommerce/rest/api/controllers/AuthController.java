package com.ecommerce.rest.api.controllers;

import com.ecommerce.rest.api.entity.User;
import com.ecommerce.rest.api.exceptions.InvalidCredentials;
import com.ecommerce.rest.api.payload.JwtApiResponse;
import com.ecommerce.rest.api.payload.UserDto;
import com.ecommerce.rest.api.repository.UserRepository;
import com.ecommerce.rest.api.response.ApiResponse;
import com.ecommerce.rest.api.util.JwtUtil;
import com.ecommerce.rest.api.validation.ParameterValidator;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParameterValidator parameterValidator;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> authenticate(@RequestBody UserDto userDto) {
        try {

            Boolean isValid = parameterValidator.validateForLogin(userDto);

            if (!isValid) {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "All Parameter are required", false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            }

            Optional<User> optionalUser = this.userRepository.findByUsername(userDto.getUsername());

            if (!optionalUser.isPresent()) {
                return new ResponseEntity<>(new ApiResponse(404, "NOT_FOUND", "User does not register", false, Arrays.asList()), HttpStatus.NOT_FOUND);
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);

            return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Login Successfully", true, new JwtApiResponse("Bearer", jwt, optionalUser.get())), HttpStatus.NOT_FOUND);
        } catch (InvalidCredentials | AuthenticationException e) {
            return new ResponseEntity<>(new ApiResponse(401, "UNAUTHORIZED", "Invalid Username and Password", false, Arrays.asList()), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
