package com.example.multisimulationprofilinganalysisbackend.controller;

import com.example.multisimulationprofilinganalysisbackend.appuser.AppUser;
import com.example.multisimulationprofilinganalysisbackend.appuser.AppUserRepository;
import com.example.multisimulationprofilinganalysisbackend.appuser.AppUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController

public class UserController {
    AppUserService appUserService;
    @GetMapping("/")
  public String getLogedInUser(){
        String email;
        String firstname;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }

      return email;
    }
}