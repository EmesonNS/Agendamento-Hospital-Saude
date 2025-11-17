-- V2__Add_Default_Admin.sql
-- Insere um usuário Admin padrão.

-- A senha é 'admin123'

-- 1. Insere na tabela pai (tbl_usuario)
INSERT INTO tbl_usuario (nome, email, senha, cpf, role)
VALUES (
           'Admin Padrão',
           'admin@hospital.com',
           '$2a$12$F2H.G37vuny2dSA7sg5LTewl2NOIIsb076cc0ThJzGGFxYFjZrbV6',
           '614.465.600-66',
           'ROLE_ADMIN'
       );

-- 2. Insere na tabela filha (tbl_admin)
INSERT INTO tbl_admin (id_usuario)
VALUES (LAST_INSERT_ID());