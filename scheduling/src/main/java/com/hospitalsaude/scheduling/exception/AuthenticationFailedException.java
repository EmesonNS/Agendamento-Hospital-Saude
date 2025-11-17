package com.hospitalsaude.scheduling.exception;

public class AuthenticationFailedException extends BusinessRuleException {

    public AuthenticationFailedException(String message) {
        super("AUTHENTICATION_FAILED", "Falha na autenticação: " + message, message);
    }

    public AuthenticationFailedException(String message, String userMessage) {
        super("AUTHENTICATION_FAILED", "Falha na autenticação: " + message, userMessage);
    }

    public AuthenticationFailedException(String message, String userMessage, Throwable cause) {
        super("AUTHENTICATION_FAILED", "Falha na autenticação: " + message, userMessage, cause);
    }
}