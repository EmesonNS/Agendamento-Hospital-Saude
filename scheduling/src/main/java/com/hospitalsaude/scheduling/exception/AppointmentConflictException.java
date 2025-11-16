package com.hospitalsaude.scheduling.exception;

public class AppointmentConflictException extends BusinessRuleException {

    public AppointmentConflictException(String message) {
        super("APPOINTMENT_CONFLICT", message, message);
    }

    public AppointmentConflictException(String message, String userMessage) {
        super("APPOINTMENT_CONFLICT", message, userMessage);
    }

    public AppointmentConflictException(String message, String userMessage, Throwable cause) {
        super("APPOINTMENT_CONFLICT", message, userMessage, cause);
    }
}