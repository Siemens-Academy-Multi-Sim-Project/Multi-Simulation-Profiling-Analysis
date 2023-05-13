package com.example.multisimulationprofilinganalysisbackend.registration;


import com.example.multisimulationprofilinganalysisbackend.appuser.AppUser;
import com.example.multisimulationprofilinganalysisbackend.appuser.AppUserRole;
import com.example.multisimulationprofilinganalysisbackend.appuser.AppUserService;
import com.example.multisimulationprofilinganalysisbackend.dto.LoginDTO;
import com.example.multisimulationprofilinganalysisbackend.email.EmailBuilder;
import com.example.multisimulationprofilinganalysisbackend.email.EmailSender;
import com.example.multisimulationprofilinganalysisbackend.email.EmailValidator;
import com.example.multisimulationprofilinganalysisbackend.registration.token.ConfirmationToken;
import com.example.multisimulationprofilinganalysisbackend.registration.token.ConfirmationTokenService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class AuthService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private final EmailBuilder emailBuilder;


    public AuthService(
        AppUserService appUserService, 
        EmailValidator emailValidator, 
        ConfirmationTokenService confirmationTokenService, 
        EmailSender emailSender, EmailBuilder builder
    ) {
        this.appUserService = appUserService;
        this.emailValidator = emailValidator;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSender = emailSender;
        this.emailBuilder = builder;
    }



    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        String token = appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        emailSender.send(
                request.getEmail(),
                emailBuilder.confirmationEmail(request.getFirstName(), link));

        return token;
    }

    public boolean checkUser(LoginDTO loginDTO){
        return appUserService.checkUser(loginDTO);
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }
}