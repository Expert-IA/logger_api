-- Script de configuração inicial do Oracle Database para Logger API
-- Execute este script como SYSDBA para preparar o ambiente

-- 1. Criar usuário/schema logger_user
CREATE USER logger_user IDENTIFIED BY logger_password;

-- 2. Conceder privilégios necessários
GRANT CONNECT TO logger_user;
GRANT RESOURCE TO logger_user;
GRANT CREATE SESSION TO logger_user;
GRANT CREATE TABLE TO logger_user;
GRANT CREATE SEQUENCE TO logger_user;
GRANT CREATE TRIGGER TO logger_user;
GRANT CREATE VIEW TO logger_user;
GRANT CREATE PROCEDURE TO logger_user;

-- 3. Definir quota no tablespace USERS
ALTER USER logger_user QUOTA UNLIMITED ON USERS;

-- 4. Definir esquema padrão (opcional)
ALTER USER logger_user DEFAULT TABLESPACE USERS;
ALTER USER logger_user TEMPORARY TABLESPACE TEMP;

-- 5. Verificar a criação do usuário
SELECT username, default_tablespace, temporary_tablespace, account_status 
FROM dba_users 
WHERE username = 'LOGGER_USER';

-- 6. Conectar como logger_user para testar
-- CONNECT logger_user/logger_password

PROMPT 'Usuário logger_user criado com sucesso!'
PROMPT 'Configure a aplicação com:'
PROMPT 'URL: jdbc:oracle:thin:@//localhost:1521/XEPDB1'
PROMPT 'Username: logger_user'
PROMPT 'Password: logger_password'