package com.hospitalsaude.scheduling.exception;

import java.util.Map;

public class DataValidationException extends BusinessRuleException {

    private final Map<String, String> validationErrors;

    public DataValidationException(String message, Map<String, String> validationErrors) {
        super("DATA_VALIDATION_ERROR", message, message);
        this.validationErrors = validationErrors;
    }

    public DataValidationException(String message, String userMessage, Map<String, String> validationErrors) {
        super("DATA_VALIDATION_ERROR", message, userMessage);
        this.validationErrors = validationErrors;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
}