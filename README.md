Biblioteca - Sistema de Gerenciamento

Sistema de gerenciamento de gêneros, autores e livros, com relatórios de empréstimos. Desenvolvido em Spring Boot, JPA e PostgreSQL.

--------Pré-requisitos--------------

Java 21 ou superior

Maven 3.8 ou superior

PostgreSQL

IDE de sua preferência (opcional)

------Configuração do Banco de Dados--------

Crie um banco de dados no PostgreSQL (exemplo: biblioteca).

Configure usuário e senha.

Atualize o arquivo application.yml com as credenciais do banco. Exemplo:

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/biblioteca
    username: seu_usuario
    password: sua_senha
----------------------------------------------
para rodar mvn clean spring-boot:run 

ou configure um perfil na sua ide

SWAGGER ----->>  http://localhost:8080/swagger-ui/index.html
