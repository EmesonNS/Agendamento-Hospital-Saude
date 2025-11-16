package com.hospitalsaude.scheduling.exception;

public class ResourceNotFoundException extends BusinessRuleException {

    public ResourceNotFoundException(String message) {
        super("RESOURCE_NOT_FOUND", "Recurso não encontrado: " + message, message);
    }

    public ResourceNotFoundException(String resourceType, String identifier) {
        super("RESOURCE_NOT_FOUND",
              resourceType + " " + identifier + " não encontrado.",
              resourceType + " " + identifier + " não encontrado no sistema.");
    }
}
