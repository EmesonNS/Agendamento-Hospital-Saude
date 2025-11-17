# Agendamento Hospital Saúde (API Backend)

Este repositório contém o backend completo para um sistema de agendamento hospitalar, construído com Java, Spring Boot e Spring Security.

O projeto fornece uma API RESTful segura para gerenciar pacientes, médicos, agendas e consultas, com autenticação baseada em JWT e controle de acesso por papéis (Roles).

-----

## ✨ Funcionalidades Principais

  * **Autenticação JWT:** Sistema de login seguro (`/auth/login`) que retorna um JSON Web Token.
  * **Controle de Acesso (Roles):** Acesso diferenciado para 3 perfis:
      * `ROLE_PATIENT`: Pode se cadastrar, ver seus próprios dados e agendar consultas.
      * `ROLE_DOCTOR`: Pode gerenciar o status das consultas e ver dados de pacientes.
      * `ROLE_ADMIN`: Tem controle total sobre o cadastro de médicos e suas agendas.
  * **Gerenciamento de Médicos:** Endpoints de CRUD para Doutores (restrito ao Admin).
  * **Gerenciamento de Agendas:** Endpoints para definir os dias e horários de trabalho dos médicos (restrito ao Admin).
  * **Sistema de Agendamento:** Lógica de negócio para verificar horários disponíveis e prevenir agendamentos duplicados (usando *constraints* de banco de dados).
  * **Migrações de Banco:** O schema do banco é totalmente gerenciado pelo **Flyway**.
  * **Documentação da API:** A API é 100% documentada com **Swagger (OpenAPI 3)**.
  * **Contêinerização:** O projeto está pronto para ser executado com **Docker** e **Docker Compose**.

-----

## 🛠️ Stack Tecnológica

  * **Java 21**
  * **Spring Boot 3**
  * **Spring Security 6** (Autenticação JWT)
  * **Spring Data JPA** (Hibernate)
  * **MySQL** (Banco de Dados Relacional)
  * **Flyway** (Gerenciamento de Migrações do DB)
  * **Docker / Docker Compose**
  * **Springdoc OpenAPI 3** (Swagger)
  * **Maven**

-----

## 🚀 Como Executar

Existem duas maneiras de subir a aplicação:

### Opção 1: Docker (Recomendado)

Esta é a forma mais simples e rápida. Você só precisa ter o Docker e o Docker Compose instalados.

1.  **Construa e Suba os Containers:**
    (Se você estiver no Linux, pode precisar do `sudo`)
    ```bash
    docker compose up --build
    ```
2.  **Pronto\!** A API estará rodando em `http://localhost:8080` e o banco de dados MySQL em `http://localhost:3306`.

### Opção 2: Localmente

1.  **Inicie um Banco MySQL:**
    Certifique-se de ter um servidor MySQL rodando (localmente ou em um container) e crie um banco de dados vazio:
    ```sql
    CREATE DATABASE db_hospitalsaude;
    ```
2.  **Configure o `application.properties`:**
    Abra `scheduling/src/main/resources/application.properties` e verifique se as credenciais do seu banco local estão corretas:
    ```properties
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.url=jdbc:mysql://localhost:3306/db_hospitalsaude?useTimeZone=true&serverTimeZone=America/Brasilia
    ```
3.  **Execute a Aplicação:**
    Use o Maven para rodar o projeto. O Flyway será executado automaticamente na inicialização, criando todas as tabelas e inserindo o usuário admin.
    ```bash
    # Navegue até a pasta que contém o pom.xml principal
    cd scheduling

    # Rode a aplicação
    mvn spring-boot:run
    ```

-----

## 📚 Documentação da API (Swagger)

Uma vez que a aplicação esteja rodando (com qualquer um dos métodos acima), a documentação completa e interativa da API estará disponível em:

**`http://localhost:8080/swagger-ui/index.html`**

### Como Usar o Swagger com Segurança:

1.  Use o endpoint `POST /auth/login` no Swagger para obter um token.
2.  Clique no botão "Authorize" no topo da página.
3.  Cole o token (ex: `Bearer ey...`) para autenticar suas requisições.

-----

## 🔑 Acesso e Endpoints Principais

O sistema possui 3 perfis de usuário. O primeiro admin é criado automaticamente pela migração `V2` do Flyway.

#### 👤 Admin Padrão

  * **Email:** `admin@hospital.com`
  * **Senha:** `admin123`

#### 👮 Regras de Acesso da API

| Rota(s) | Método(s) | Acesso | Descrição |
| :--- | :--- | :--- | :--- |
| `/auth/login` | `POST` | **Público** | Login de qualquer usuário. |
| `/patient` | `POST` | **Público** | Registro de um novo paciente. |
| `/doctor/specialty` | `GET` | **Público** | Lista todas as especialidades. |
| `/doctor/{id}/available-times` | `GET` | **Público** | Busca horários livres de um médico. |
| `/doctor/**` | `GET`, `POST`, `PUT`, `DELETE` | `ROLE_ADMIN` | Gerenciamento completo dos médicos. |
| `/schedule/**` | `GET`, `POST`, `PUT`, `DELETE` | `ROLE_ADMIN` | Gerenciamento completo das agendas. |
| `/patient/**` | `GET`, `PUT`, `DELETE` | `ROLE_ADMIN`, `ROLE_DOCTOR` | Doutores e Admins podem gerenciar pacientes. |
| `/appointment/**` | `GET`, `POST`, `PATCH` | `ROLE_ADMIN`, `ROLE_DOCTOR`, `ROLE_PATIENT` | Todos os usuários logados podem interagir com agendamentos. |
