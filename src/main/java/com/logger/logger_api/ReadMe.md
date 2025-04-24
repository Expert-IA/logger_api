br.com..userinteractionlogger
├── domain
│   ├── entity
│   │   └── InteractionLog.java         ← entidade principal
│   ├── valueobject
│   │   └── ClickType.java              ← enum ou VO, ex: BUTTON_CLICK, LINK_CLICK
│   ├── repository
│   │   └── InteractionLogRepository.java ← interface
│   └── service
│       └── InteractionLoggerService.java ← lógica de domínio, se necessário
├── application
│   ├── LogUserInteractionUseCase.java  ← caso de uso principal
│   └── GetLogsByUserUseCase.java       ← para buscas/auditoria
├── infra
│   └── repository
│       └── JpaInteractionLogRepository.java ← implementação JPA ou JDBC
├── interfaces
│   └── controller
│       └── InteractionLoggerController.java ← recebe os eventos

