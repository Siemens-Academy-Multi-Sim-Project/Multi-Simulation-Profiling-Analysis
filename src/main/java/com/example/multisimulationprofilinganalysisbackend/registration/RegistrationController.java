package com.example.multisimulationprofilinganalysisbackend.registration;


import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {
    private  RegistrationService registrationService;
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        try{
            var registerationToken = registrationService.register(request);
            return ResponseEntity.ok(registerationToken);
        }catch(IllegalStateException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}