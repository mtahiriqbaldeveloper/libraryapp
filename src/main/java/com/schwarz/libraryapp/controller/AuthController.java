package com.schwarz.libraryapp.controller;


import com.schwarz.libraryapp.entity.Customer;
import com.schwarz.libraryapp.payload.ApiResponse;
import com.schwarz.libraryapp.payload.JwtAuthenticationResponse;
import com.schwarz.libraryapp.payload.LoginRequest;
import com.schwarz.libraryapp.payload.SignUpRequest;
import com.schwarz.libraryapp.repository.RoleRepository;
import com.schwarz.libraryapp.security.JwtTokenProvider;
import com.schwarz.libraryapp.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RequestMapping( "/api/auth/")
@RestController
@Controller
public class AuthController {

    private CustomerService customerService;
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;


    public AuthController(CustomerService customerService, AuthenticationManager authenticationManager,
                          JwtTokenProvider tokenProvider) {
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if(customerService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        Customer result = customerService.saveCustomer(signUpRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
