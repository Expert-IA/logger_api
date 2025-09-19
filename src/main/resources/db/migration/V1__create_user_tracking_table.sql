-- V1: Criação da tabela USER_TRACKING
-- Tabela para rastreamento de ações do usuário

CREATE TABLE USER_TRACKING (
    id VARCHAR2(36) NOT NULL,
    user_id VARCHAR2(255) NOT NULL,
    event_type VARCHAR2(50) NOT NULL,
    page_url VARCHAR2(500),
    element_id VARCHAR2(255),
    log_level VARCHAR2(50) DEFAULT 'INFO',
    timestamp TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP(6),
    CONSTRAINT pk_user_tracking PRIMARY KEY (id),
    CONSTRAINT chk_event_type CHECK (event_type IN (
        'BUTTON_CLICK',
        'FORM_SUBMIT',
        'PAGE_VIEW',
        'SCROLL',
        'HOVER',
        'LOGIN',
        'LOGOUT',
        'SEARCH',
        'FILE_DOWNLOAD',
        'FILE_UPLOAD',
        'ERROR',
        'NAVIGATION',
        'ITEM_SELECTED',
        'MODAL_OPEN',
        'MODAL_CLOSE'
    ))
);

-- Índices para otimização
CREATE INDEX idx_user_tracking_user_id ON USER_TRACKING (user_id);
CREATE INDEX idx_user_tracking_timestamp ON USER_TRACKING (timestamp);
CREATE INDEX idx_user_tracking_event_type ON USER_TRACKING (event_type);

-- Comentários
COMMENT ON TABLE USER_TRACKING IS 'Tabela de rastreamento de ações do usuário';
COMMENT ON COLUMN USER_TRACKING.id IS 'Identificador único (UUID)';
COMMENT ON COLUMN USER_TRACKING.user_id IS 'ID do usuário';
COMMENT ON COLUMN USER_TRACKING.event_type IS 'Tipo de evento';
COMMENT ON COLUMN USER_TRACKING.page_url IS 'URL da página';
COMMENT ON COLUMN USER_TRACKING.element_id IS 'ID do elemento HTML';
COMMENT ON COLUMN USER_TRACKING.log_level IS 'Nível do log';
COMMENT ON COLUMN USER_TRACKING.timestamp IS 'Data/hora do evento';
COMMENT ON COLUMN USER_TRACKING.updated_at IS 'Data/hora da última atualização';