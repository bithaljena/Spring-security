package com.example.security.controller;

import com.example.security.entity.LoginRequest;
import com.example.security.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody LoginRequest request){

        if("admin".equals(request.getUsername())
                && "password".equals(request.getPassword())){

            String token = jwtUtil.generateToken(request.getUsername());

            return Map.of("token",token);
        }

        throw new RuntimeException("Invalid credentials");
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello, authenticated user!";
    }
}

