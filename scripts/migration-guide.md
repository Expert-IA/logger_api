# Guia de Migrations - Logger API

## Estrutura das Migrations

### V1__create_user_tracking_table.sql
- **Objetivo**: Criação inicial da tabela USER_TRACKING
- **Estrutura**: UUID como PK, campos de tracking, índices otimizados
- **Constraints**: Check constraint para EventType enum

## Como Usar

### 1. Reset Completo do Banco (Desenvolvimento)
```sql
-- Execute no Oracle SQL*Plus ou similar
@scripts/reset-database.sql
```

### 2. Executar Migrations Automaticamente
```bash
# As migrations rodam automaticamente na inicialização da aplicação
./mvnw spring-boot:run
```

### 3. Verificar Status (quando aplicação estiver rodando)
- As migrations são controladas pelo Flyway integrado
- Tabela de controle: `flyway_schema_history_v3`
- Logs aparecem durante a inicialização

## Configuração do Flyway

**application.properties:**
```properties
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true
spring.flyway.table=flyway_schema_history_v3
```

## Estrutura da Tabela USER_TRACKING

```sql
CREATE TABLE USER_TRACKING (
    id VARCHAR2(36) NOT NULL,           -- UUID
    user_id VARCHAR2(255) NOT NULL,     -- ID do usuário
    event_type VARCHAR2(50) NOT NULL,   -- Tipo do evento (enum)
    page_url VARCHAR2(500),             -- URL da página
    element_id VARCHAR2(255),           -- ID do elemento HTML
    log_level VARCHAR2(50),             -- Nível do log
    timestamp TIMESTAMP(6) NOT NULL,    -- Data/hora do evento
    updated_at TIMESTAMP(6),            -- Data/hora da atualização
    PRIMARY KEY (id)
);
```

## Índices Criados
- `idx_user_tracking_user_id` - Otimização para consultas por usuário
- `idx_user_tracking_timestamp` - Otimização para consultas temporais
- `idx_user_tracking_event_type` - Otimização para filtros por tipo

## Troubleshooting

### Problema: Migration não executa
**Solução**: Execute o reset e reinicie a aplicação

### Problema: Tabela já existe
**Solução**: Use o script `reset-database.sql` para limpeza completa

### Problema: Erro de constraint
**Solução**: Verifique se os valores de EventType estão no enum definido