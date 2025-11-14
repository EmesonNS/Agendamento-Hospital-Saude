-- V1__Create_Initial_Tables.sql

-- Tabela Base para Pacientes e Médicos
-- Mapeada de: User.java (com @Inheritance(strategy = InheritanceType.JOINED))
CREATE TABLE tbl_usuario (
                             id_usuario INT AUTO_INCREMENT PRIMARY KEY,
                             nome VARCHAR(50) NOT NULL,
                             email VARCHAR(100) NOT NULL UNIQUE,
                             senha VARCHAR(255) NOT NULL,
                             cpf VARCHAR(255) UNIQUE,
                             telefone VARCHAR(255),
                             role VARCHAR(255) NOT NULL
);

-- Tabela de Pacientes
-- Mapeada de: Patient.java (extends User)
CREATE TABLE tbl_paciente (
                              id_usuario INT PRIMARY KEY,
                              data_nascimento DATE,
                              genero VARCHAR(50),
                              tipo_sanguineo VARCHAR(10),
                              observacao VARCHAR(500),
                              CONSTRAINT fk_paciente_usuario FOREIGN KEY (id_usuario) REFERENCES tbl_usuario(id_usuario)
);

-- Tabela de Médicos
-- Mapeada de: Doctor.java (extends User)
CREATE TABLE tbl_medico (
                            id_usuario INT PRIMARY KEY,
                            crm INT NOT NULL UNIQUE,
                            especialidade VARCHAR(255) NOT NULL,
                            CONSTRAINT fk_medico_usuario FOREIGN KEY (id_usuario) REFERENCES tbl_usuario(id_usuario)
);

-- Tabela de Agendas dos Médicos
-- Mapeada de: ScheduleDoctor.java
CREATE TABLE tbl_agenda (
                            id_agenda INT AUTO_INCREMENT PRIMARY KEY,
                            id_medico INT,
                            horario_inicio TIME NOT NULL,
                            horario_fim TIME NOT NULL,
                            CONSTRAINT fk_agenda_medico FOREIGN KEY (id_medico) REFERENCES tbl_medico(id_usuario)
);

-- Tabela associativa para os dias da semana da agenda
-- Mapeada de: @ElementCollection em ScheduleDoctor.java
CREATE TABLE agenda_dias (
                             id_agenda INT NOT NULL,
                             dia_da_semana VARCHAR(255) NOT NULL,
                             CONSTRAINT fk_agenda_dias_agenda FOREIGN KEY (id_agenda) REFERENCES tbl_agenda(id_agenda)
);

-- Tabela de Consultas (Agendamentos)
-- Mapeada de: Appointment.java
CREATE TABLE tbl_consulta (
                              id_consulta INT AUTO_INCREMENT PRIMARY KEY,
                              id_medico INT,
                              id_paciente INT,
                              data_agendamento DATETIME,
                              data_consulta DATE NOT NULL,
                              hora_consulta TIME NOT NULL,
                              status_consulta VARCHAR(255) NOT NULL,
                              tipo_consulta VARCHAR(255) NOT NULL,
                              observacao VARCHAR(255),

    -- Chaves Estrangeiras
                              CONSTRAINT fk_consulta_medico FOREIGN KEY (id_medico) REFERENCES tbl_medico(id_usuario),
                              CONSTRAINT fk_consulta_paciente FOREIGN KEY (id_paciente) REFERENCES tbl_paciente(id_usuario),

    -- Constraint de Unicidade para evitar agendamento duplo
    -- Mapeada de: @UniqueConstraint em Appointment.java
                              CONSTRAINT uq_appointment_slot UNIQUE (id_medico, data_consulta, hora_consulta)
);