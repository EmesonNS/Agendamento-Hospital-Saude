package com.hospitalsaude.scheduling.exception;

public class ScheduleUnavailableException extends BusinessRuleException {

    public ScheduleUnavailableException(String message) {
        super("SCHEDULE_UNAVAILABLE", "Horário não disponível: " + message, message);
    }

    public ScheduleUnavailableException(String doctorName, String dateTime) {
        super("SCHEDULE_UNAVAILABLE",
              "O médico " + doctorName + " não possui atendimento disponível em " + dateTime,
              "O médico selecionado não possui horários disponíveis para esta data e horário.");
    }
}