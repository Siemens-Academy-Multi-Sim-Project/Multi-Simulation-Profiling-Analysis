package com.example.multisimulationprofilinganalysisbackend.email;

public interface EmailSender {
    void send(String to, String email);
}