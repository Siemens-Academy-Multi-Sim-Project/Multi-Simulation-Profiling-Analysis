package com.example.multisimulationprofilinganalysisbackend.registration;


import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.multisimulationprofilinganalysisbackend.dto.LoginDTO;
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
@RequestMapping(path = "auth")
public class AuthController {
    private  AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        try{
            var registerationToken = authService.register(request);
            return ResponseEntity.ok(registerationToken);
        }catch(IllegalStateException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    // since we're using basic auth. this endpoints only checks if the user creds are valid
    @PostMapping("login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDTO loginDto){
        return ResponseEntity.ok(
            authService.checkUser(loginDto)
        );
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return authService.confirmToken(token);
    }
}