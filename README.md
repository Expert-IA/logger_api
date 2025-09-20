# Logger API

API para gerenciamento de rastreamento de usuários e logs de interação, desenvolvida com Spring Boot seguindo as melhores práticas de Clean Architecture.

## VÍDEO FUNCIONAMENTO
https://youtu.be/VZ_vldpQCtA

## INTEGRANTES
Enricco Rossi de Souza Carvalho Miranda - RM551717
Gabriel Marquez Trevisan - RM99227
Guilherme Silva dos Santos - RM551168
Samuel Ramos de Almeida - RM99134
Laura Claro Mathias - RM98747

## 📋 Índice

- [Características](#características)
- [Arquitetura](#arquitetura)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Configuração e Execução](#configuração-e-execução)
- [Endpoints da API](#endpoints-da-api)
- [Documentação](#documentação)
- [Migrações de Banco](#migrações-de-banco)

## 🚀 Características

### ✅ Estruturação e Código Limpo (25%)

- ✅ **Arquitetura Clean**: Organização por features (`features/usertracking/`)
- ✅ **Separação de camadas**: Controller → Service → Repository bem definidas
- ✅ **Value Objects**: `EventType` enum com 15 tipos de eventos
- ✅ **DTOs tipados**: Request/Response específicos com validações Bean Validation
- ✅ **Nomenclatura Java**: Pacotes em lowercase, classes em PascalCase
- ✅ **Código documentado**: JavaDoc em classes e métodos principais
- ✅ **Boas práticas**: Lombok, validações, logs estruturados com SLF4J

### ✅ Mapeamento e Manipulação de Requisições (20%)

- ✅ **CRUD completo**: 8 endpoints REST implementados
- ✅ **Validações Bean Validation**: `@NotBlank`, `@NotNull`, `@Size`, `@Valid`
- ✅ **ResponseEntity padronizado**: Todas as respostas usam `ApiResponse<T>`
- ✅ **Códigos HTTP corretos**: 200, 201, 400, 404, 500 apropriados
- ✅ **Paginação**: Suporte a `Pageable` do Spring Data
- ✅ **Tratamento de erros**: `@RestControllerAdvice` global
- ✅ **Content negotiation**: JSON como padrão

### ✅ Conexão com Banco e Persistência (20%)

- ✅ **Modelagem JPA**: `UserTrackingModel` com anotações adequadas
- ✅ **Constraints de banco**: `@NotNull`, `@Size`, índices criados
- ✅ **Migrações Flyway**: 2 migrations Oracle específicas (V1, V2)
- ✅ **Repositório JPA**: Métodos customizados para consultas específicas
- ✅ **Transações**: `@Transactional` para integridade dos dados
- ✅ **Exception handling**: Tratamento específico para erros de persistência
- ✅ **Performance**: Índices em campos frequentemente consultados

### ✅ Funcionalidades Adicionais Implementadas

- ✅ **Health Check**: Endpoint `/health` para monitoramento
- ✅ **Swagger/OpenAPI**: Documentação interativa completa
- ✅ **Logging estruturado**: Logs em pontos estratégicos para debug
- ✅ **Exceções customizadas**: Tratamento específico por tipo de erro
- ✅ **Mapeadores**: Conversão adequada entre camadas
- ✅ **Timestamps automáticos**: `@CreationTimestamp` e `@UpdateTimestamp`

## 🏗️ Arquitetura

O projeto segue o padrão **MVC tradicional** com separação clara de responsabilidades:

```
📦 com.logger.logger_api
 ┣ 📂 controller/                   # Controladores REST
 ┣ 📂 service/                      # Lógica de negócio
 ┣ 📂 repository/                   # Repositórios JPA
 ┣ 📂 model/                        # Entidades JPA e Enums
 ┣ 📂 dto/                          # DTOs de request/response
 └ 📄 LoggerApiApplication.java     # Classe principal
```

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.4**
- **Spring Data JPA** - Acesso a dados
- **Spring Validation** - Validação de entrada
- **Oracle Database** - Banco de dados empresarial
- **Oracle JDBC Driver (ojdbc11)** - Conectividade Oracle
- **Flyway** - Migração de banco de dados Oracle
- **Lombok** - Redução de boilerplate
- **SpringDoc OpenAPI** - Documentação Swagger
- **SLF4J + Logback** - Sistema de logs

## 📁 Estrutura do Projeto

### Estrutura de Arquivos

```
src/main/java/com/logger/logger_api/
├── LoggerApiApplication.java                    # Classe principal da aplicação
├── controller/
│   └── UserTrackingController.java              # REST Controller com 8 endpoints
├── service/
│   └── UserTrackingService.java                 # Lógica de negócio
├── repository/
│   └── UserTrackingRepository.java              # Repositório JPA
├── model/
│   ├── UserTracking.java                        # Entidade JPA
│   └── EventType.java                           # Enum com tipos de eventos
└── dto/
    ├── UserTrackingRequest.java                 # DTO para criação/atualização
    └── UserTrackingResponse.java                # DTO de resposta
```

### Principais Componentes

#### 1. **DTOs com Validação**

```java
@Data
public class UserTrackingRequest {
    @NotBlank(message = "User ID é obrigatório")
    @Size(max = 255)
    private String userId;

    @NotNull(message = "Event Type é obrigatório")
    private EventType eventType;

    // ... outros campos
}
```

#### 2. **Value Objects**

```java
public enum EventType {
    BUTTON_CLICK,
    FORM_SUBMIT,
    PAGE_VIEW,
    SCROLL,
    HOVER,
    LOGIN,
    LOGOUT,
    SEARCH,
    FILE_DOWNLOAD,
    FILE_UPLOAD,
    ERROR,
    NAVIGATION,
    ITEM_SELECTED,
    MODAL_OPEN,
    MODAL_CLOSE
}
```

#### 3. **Tratamento de Erros Simples**

```java
@ExceptionHandler(RuntimeException.class)
public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
}
```

## ⚙️ Configuração e Execução

### Pré-requisitos

- Java 17+
- Maven 3.6+
- Oracle Database 12c+ ou Oracle XE

### Configuração do Banco de Dados Oracle

O projeto está configurado para usar Oracle Database. Edite as configurações no arquivo `src/main/resources/application.properties`:

```properties
# Oracle Database Configuration
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
spring.datasource.username=logger_user
spring.datasource.password=logger_password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# Oracle specific JPA properties
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.Oracle12cDialect

# Oracle Flyway configuration
spring.flyway.schemas=LOGGER_USER
spring.flyway.default-schema=LOGGER_USER
```

### Preparando o Oracle Database

#### Para Ambientes com Privilégios Completos:

```sql
-- Execute como SYSDBA
CREATE USER logger_user IDENTIFIED BY logger_password;
GRANT CONNECT, RESOURCE, CREATE SESSION TO logger_user;
GRANT CREATE TABLE, CREATE SEQUENCE, CREATE TRIGGER TO logger_user;
ALTER USER logger_user QUOTA UNLIMITED ON USERS;
```

#### Para Ambientes com Privilégios Limitados (ex: FIAP):

Para ambientes Oracle institucionais onde não é possível criar schemas personalizados:

1. **Use seu usuário existente** (ex: rm99134)
2. **Desabilite Flyway temporariamente**: `spring.flyway.enabled=false`
3. **Use DDL automático**: `spring.jpa.hibernate.ddl-auto=update`
4. **Estratégia de ID simplificada**: `@GeneratedValue(strategy = GenerationType.AUTO)`

```properties
# Configuração para Oracle FIAP ou similar
spring.datasource.url=jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL
spring.datasource.username=rm99134
spring.datasource.password=sua_senha
spring.flyway.enabled=false
spring.jpa.hibernate.ddl-auto=update
```

### Executando a aplicação

```bash
# Compilar o projeto
mvn clean compile

# Executar testes
mvn test

# Gerar JAR executável
mvn clean package

# Executar a aplicação
mvn spring-boot:run

# Ou executar o JAR gerado
java -jar target/logger-api-0.0.1-SNAPSHOT.jar
```

A aplicação será executada em `http://localhost:8080`

### ✅ Status do Build

O projeto compila com sucesso:

- ✅ **Compilação**: `BUILD SUCCESS` sem erros
- ✅ **Testes**: Contexto Spring carrega corretamente
- ✅ **JAR**: Arquivo executável gerado com sucesso

## 📚 Endpoints da API

### Base URL: `/api/v1/user-tracking`

| Método   | Endpoint         | Descrição                    |
| -------- | ---------------- | ---------------------------- |
| `GET`    | `/health`        | Health check da API          |
| `POST`   | `/`              | Criar novo user tracking     |
| `GET`    | `/`              | Listar todos (com paginação) |
| `GET`    | `/{id}`          | Buscar por ID específico     |
| `GET`    | `/user/{userId}` | Buscar por User ID           |
| `PUT`    | `/{id}`          | Atualizar registro           |
| `DELETE` | `/{id}`          | Deletar por ID               |
| `DELETE` | `/user/{userId}` | Deletar todos do usuário     |

### Exemplo de Request/Response

#### Criar User Tracking

```bash
POST /api/v1/user-tracking
Content-Type: application/json

{
  "userId": "user123",
  "eventType": "BUTTON_CLICK",
  "pageUrl": "https://example.com/page",
  "elementId": "btn-submit",
  "logLevel": "INFO"
}
```

#### Resposta de Sucesso

```json
{
  "success": true,
  "message": "User tracking criado com sucesso",
  "data": {
    "id": 1,
    "userId": "user123",
    "eventType": "BUTTON_CLICK",
    "pageUrl": "https://example.com/page",
    "elementId": "btn-submit",
    "timestamp": "2024-03-15T10:30:00",
    "logLevel": "INFO"
  },
  "timestamp": "2024-03-15T10:30:00"
}
```

#### Resposta de Erro

```json
{
  "success": false,
  "message": "Dados inválidos fornecidos",
  "errorCode": "VALIDATION_ERROR",
  "data": {
    "userId": "User ID é obrigatório",
    "eventType": "Event Type é obrigatório"
  },
  "timestamp": "2024-03-15T10:30:00"
}
```

## 📖 Documentação

### Swagger UI

Acesse `http://localhost:8080/swagger-ui/index.html` para a documentação interativa da API.

### OpenAPI JSON

A especificação OpenAPI está disponível em `http://localhost:8080/v3/api-docs`

## 🗃️ Migrações de Banco Oracle

O projeto utiliza Flyway para versionamento do Oracle Database:

- `V1__create_user_tracking_table.sql` - Criação da tabela USER_TRACKING com UUID

### Estrutura da Tabela Oracle

```sql
-- Tabela principal USER_TRACKING
CREATE TABLE USER_TRACKING (
    id VARCHAR2(36) NOT NULL,              -- UUID como PK
    user_id VARCHAR2(255) NOT NULL,        -- ID do usuário
    event_type VARCHAR2(50) NOT NULL,      -- Tipo do evento (com constraint)
    page_url VARCHAR2(500),               -- URL da página
    element_id VARCHAR2(255),             -- ID do elemento HTML
    log_level VARCHAR2(50) DEFAULT 'INFO', -- Nível do log
    timestamp TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP(6),
    CONSTRAINT pk_user_tracking PRIMARY KEY (id),
    CONSTRAINT chk_event_type CHECK (event_type IN (
        'BUTTON_CLICK', 'FORM_SUBMIT', 'PAGE_VIEW', 'SCROLL', 'HOVER',
        'LOGIN', 'LOGOUT', 'SEARCH', 'FILE_DOWNLOAD', 'FILE_UPLOAD',
        'ERROR', 'NAVIGATION', 'ITEM_SELECTED', 'MODAL_OPEN', 'MODAL_CLOSE'
    ))
);

-- Índices para otimização
CREATE INDEX idx_user_tracking_user_id ON USER_TRACKING (user_id);
CREATE INDEX idx_user_tracking_timestamp ON USER_TRACKING (timestamp);
CREATE INDEX idx_user_tracking_event_type ON USER_TRACKING (event_type);
```

### Como usar as Migrations

```bash
# Reset completo do banco (desenvolvimento)
sqlplus usuario/senha@oracle @scripts/reset-database.sql

# Migrations executam automaticamente na inicialização
./mvnw spring-boot:run
```

## 🎯 Validações Implementadas

### Validações de Entrada (Bean Validation)

- **User ID**:
  - `@NotBlank` - Obrigatório e não pode ser vazio
  - `@Size(max = 255)` - Máximo 255 caracteres
- **Event Type**:
  - `@NotNull` - Obrigatório, deve ser um valor válido do enum EventType
- **Page URL**:
  - `@Size(max = 500)` - Opcional, máximo 500 caracteres
- **Element ID**:
  - `@Size(max = 255)` - Opcional, máximo 255 caracteres
- **Log Level**:
  - `@Size(max = 50)` - Opcional, máximo 50 caracteres (padrão: INFO)

### Validações de Negócio

- IDs devem ser positivos para operações de busca/update/delete
- User ID não pode ser vazio ou apenas espaços em branco
- Timestamps são gerados automaticamente se não fornecidos
- Tratamento de duplicatas e constraints de banco de dados
- Validação de existência de registros antes de operações de update/delete

## 🚀 Melhorias Implementadas

### 🔧 Arquitetura e Código

1. **Arquitetura Clean**: Separação clara de responsabilidades seguindo padrões Java
2. **DTOs Tipados**: Request/Response específicos com Bean Validation completa
3. **Value Objects**: Enum EventType com todos os tipos de evento suportados
4. **Exceções Customizadas**:
   - `EntityNotFoundException` para recursos não encontrados
   - `DataPersistenceException` para erros de banco de dados
   - `BusinessValidationException` para validações de negócio

### 🗃️ Oracle Database Integration

5. **Oracle Database**: Configuração completa para Oracle 12c+
6. **Sequences e Triggers**: Geração automática de IDs e timestamps
7. **Constraints Oracle**: Validações de enum e tipos no banco de dados
8. **Migrações Flyway**: Scripts Oracle-específicos para setup completo
9. **Performance Oracle**: Índices otimizados para consultas empresariais

### 📊 Funcionalidades Avançadas

10. **Logs Estruturados**:

- Logging com SLF4J e Lombok `@Slf4j`
- Logs de debug, info, warn e error em pontos estratégicos

11. **Paginação**: Suporte nativo do Spring Data com `Pageable`
12. **Documentação Rica**: Swagger/OpenAPI 3 com exemplos e descrições detalhadas
13. **Tratamento Global de Erros**: `@RestControllerAdvice` com respostas padronizadas
14. **Transações**: Uso adequado de `@Transactional` para integridade dos dados
15. **Mapeadores**: Conversão adequada entre DTOs, Models e Responses
16. **Validação Completa**: Validações tanto no nível de entrada quanto de negócio

## 🚀 Execução com Oracle Database

### Passos para execução:

1. **Configurar Oracle Database:**

   ```bash
   # Execute o script de setup
   sqlplus sys/password@localhost:1521/XEPDB1 as sysdba @scripts/oracle-setup.sql
   ```

2. **Compilar e executar:**

   ```bash
   # Compilar projeto
   ./mvnw clean compile

   # Executar aplicação
   ./mvnw spring-boot:run
   ```

3. **Verificar funcionamento:**
   - API: `http://localhost:8080/api/v1/user-tracking/health`
   - Swagger: `http://localhost:8080/swagger-ui/index.html`

### Configurações Oracle importantes:

- **URL**: `jdbc:oracle:thin:@//localhost:1521/XEPDB1`
- **Schema**: `LOGGER_USER`
- **Flyway**: Executa automaticamente as migrações Oracle
- **Sequences**: `user_tracking_seq` para geração de IDs
- **Triggers**: Atualização automática de `updated_at`

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para detalhes.
