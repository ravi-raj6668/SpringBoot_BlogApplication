package io.innodev.blogapp.controller;

import io.innodev.blogapp.payloads.UserDTO;
import io.innodev.blogapp.security.JwtHelper;
import io.innodev.blogapp.security.UserSecurityUtil;
import io.innodev.blogapp.security.JwtRequest;
import io.innodev.blogapp.security.JwtResponse;
import io.innodev.blogapp.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@Slf4j
public class AuthController {
    private final UserSecurityUtil userSecurityUtil;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;
    private final IUserService userService;

    @Autowired
    public AuthController(UserSecurityUtil userSecurityUtil, AuthenticationManager authenticationManager, JwtHelper jwtHelper, IUserService userService) {
        this.userSecurityUtil = userSecurityUtil;
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
        this.userService = userService;
    }
    @Operation(summary = "LOGIN API", description = "Authenticate the user using login id and password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login Successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "An error occurred.", content = @Content) })
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
        doAuthenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        UserDetails userDetails = userSecurityUtil.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtHelper.generateToken(userDetails);

        JwtResponse jwtResponse = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            log.error("Invalid details : username {}, password {} : ", username, password);
            throw new BadCredentialsException("Invalid Username and Password..!");
        }
    }

    //creating custom exception
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    //api to register new user
    @PostMapping("/registerNewUser")
    public ResponseEntity<UserDTO> registerUSer (@RequestBody UserDTO userDTO){
        UserDTO registerNewUser = userService.registerNewUser(userDTO);
        return new ResponseEntity<>(registerNewUser, HttpStatus.CREATED);
    }
}
