# Diagrama de Arquitetura - Logger API

## Arquitetura MVC com Camadas

```mermaid
graph TB
    subgraph "Presentation Layer"
        Controller[UserTrackingController]
        DTO_Req[UserTrackingRequest]
        DTO_Res[UserTrackingResponse]
    end

    subgraph "Business Layer"
        Service[UserTrackingService]
        Validation[Bean Validation]
    end

    subgraph "Data Access Layer"
        Repository[UserTrackingRepository]
        Entity[UserTracking Entity]
        Enum[EventType Enum]
    end

    subgraph "Database Layer"
        Oracle[(Oracle Database)]
        Flyway[Flyway Migrations]
    end

    subgraph "Configuration"
        App[LoggerApiApplication]
        Props[application.properties]
        Swagger[SpringDoc OpenAPI]
    end

    %% Connections
    Controller --> DTO_Req
    Controller --> DTO_Res
    Controller --> Service
    Service --> Repository
    Service --> Validation
    Repository --> Entity
    Entity --> Enum
    Repository --> Oracle
    Flyway --> Oracle
    App --> Props
    Controller --> Swagger

    %% Styling
    classDef controller fill:#e1f5fe
    classDef service fill:#f3e5f5
    classDef repository fill:#e8f5e8
    classDef database fill:#fff3e0
    classDef config fill:#fce4ec

    class Controller,DTO_Req,DTO_Res controller
    class Service,Validation service
    class Repository,Entity,Enum repository
    class Oracle,Flyway database
    class App,Props,Swagger config
```

## Fluxo de Dados

```mermaid
sequenceDiagram
    participant Client
    participant Controller as UserTrackingController
    participant Service as UserTrackingService
    participant Repository as UserTrackingRepository
    participant Database as Oracle DB

    Client->>Controller: HTTP Request (POST /api/v1/user-tracking)
    Controller->>Controller: Validate @Valid UserTrackingRequest
    Controller->>Service: create(UserTrackingRequest)
    Service->>Service: Map Request to Entity
    Service->>Repository: save(UserTracking)
    Repository->>Database: INSERT INTO USER_TRACKING
    Database-->>Repository: UserTracking Entity
    Repository-->>Service: UserTracking Entity
    Service->>Service: Map Entity to Response
    Service-->>Controller: UserTrackingResponse
    Controller-->>Client: HTTP Response (201 Created)
```

## Componentes Principais

### 1. Presentation Layer
- **UserTrackingController**: Controlador REST com 8 endpoints
- **UserTrackingRequest**: DTO de entrada com validações Bean Validation
- **UserTrackingResponse**: DTO de saída com dados completos

### 2. Business Layer
- **UserTrackingService**: Lógica de negócio e mapeamento entre DTOs e entidades
- **Bean Validation**: Validações automáticas com anotações Jakarta

### 3. Data Access Layer
- **UserTrackingRepository**: Interface JPA com métodos customizados
- **UserTracking**: Entidade JPA com mapeamento para Oracle
- **EventType**: Enum com 15 tipos de eventos predefinidos

### 4. Database Layer
- **Oracle Database**: Banco de dados empresarial (FIAP)
- **Flyway**: Versionamento e migrações automáticas

### 5. Configuration
- **LoggerApiApplication**: Classe principal Spring Boot
- **application.properties**: Configurações de banco e aplicação
- **SpringDoc OpenAPI**: Documentação automática da API

## Características Arquiteturais

- **Padrão MVC**: Separação clara entre Controller, Service e Repository
- **Injeção de Dependência**: Uso de @RequiredArgsConstructor (Lombok)
- **Transações**: @Transactional para operações de escrita
- **Paginação**: Suporte nativo com Spring Data Pageable
- **Validação**: Bean Validation no nível de entrada
- **Mapeamento**: Conversão manual entre camadas
- **Tratamento de Erros**: @ExceptionHandler básico no controller