package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.model.AuthRequest;
import com.example.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    HomeController (JwtUtil jwtUti) {
        this.jwtUtil = jwtUtil;
    }
    @Autowired
    HomeController (JwtUtil jwtUti, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }
    @GetMapping("/")
    public String welcome() {
        return "Welcome to the new world";
    }

    @PostMapping("/auth")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {

        try {
            //this line authenticate the user by the username and password given
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username and passowrd");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }
}
