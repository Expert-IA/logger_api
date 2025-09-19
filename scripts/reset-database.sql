-- Script para reset completo do banco de dados
-- Use este script quando quiser resetar completamente a estrutura

-- Remover tabela se existir
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE USER_TRACKING CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

-- Remover tabela de histórico do Flyway antiga (para reset limpo)
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE flyway_schema_history CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

-- Remover nova tabela de histórico do Flyway
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE flyway_schema_history_v3 CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

-- Limpar possíveis índices órfãos
BEGIN
    EXECUTE IMMEDIATE 'DROP INDEX idx_user_tracking_user_id';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP INDEX idx_user_tracking_timestamp';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP INDEX idx_user_tracking_event_type';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

-- Limpar índices antigos se existirem
BEGIN
    EXECUTE IMMEDIATE 'DROP INDEX idx_user_id_99134';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP INDEX idx_timestamp_99134';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP INDEX idx_event_type_99134';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

COMMIT;

-- Mostrar status após limpeza
SELECT 'Database reset completed. Ready for fresh migration.' as status FROM dual;