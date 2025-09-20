package com.logger.logger_api.service;

import com.logger.logger_api.dto.UserTrackingRequest;
import com.logger.logger_api.dto.UserTrackingResponse;
import com.logger.logger_api.model.UserTracking;
import com.logger.logger_api.repository.UserTrackingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserTrackingService {

    private final UserTrackingRepository repository;

    @Transactional
    public UserTrackingResponse create(UserTrackingRequest request) {
        UserTracking entity = new UserTracking();
        entity.setUserId(request.getUserId());
        entity.setEventType(request.getEventType());
        entity.setPageUrl(request.getPageUrl());
        entity.setElementId(request.getElementId());
        entity.setLogLevel(request.getLogLevel() != null ? request.getLogLevel() : "INFO");
        entity.setTimestamp(LocalDateTime.now());

        UserTracking saved = repository.save(entity);
        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public UserTrackingResponse findById(String id) {
        UserTracking entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserTracking não encontrado com ID: " + id));
        return mapToResponse(entity);
    }

    @Transactional(readOnly = true)
    public List<UserTrackingResponse> findByUserId(String userId) {
        System.out.println("DEBUG: Buscando registros para userId: " + userId);
        List<UserTracking> entities = repository.findByUserIdOrderByTimestampDesc(userId);
        System.out.println("DEBUG: Encontrados " + entities.size() + " registros no banco");

        for (int i = 0; i < entities.size(); i++) {
            UserTracking entity = entities.get(i);
            System.out.println("DEBUG: Registro " + (i+1) + " - ID: " + entity.getId() + ", UserId: " + entity.getUserId() + ", Timestamp: " + entity.getTimestamp());
        }

        List<UserTrackingResponse> responses = entities.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        System.out.println("DEBUG: Retornando " + responses.size() + " responses");
        return responses;
    }

    @Transactional(readOnly = true)
    public Page<UserTrackingResponse> findAll(Pageable pageable) {
        Page<UserTracking> entities = repository.findAll(pageable);
        return entities.map(this::mapToResponse);
    }

    @Transactional
    public UserTrackingResponse update(String id, UserTrackingRequest request) {
        UserTracking entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserTracking não encontrado com ID: " + id));

        entity.setUserId(request.getUserId());
        entity.setEventType(request.getEventType());
        entity.setPageUrl(request.getPageUrl());
        entity.setElementId(request.getElementId());
        entity.setLogLevel(request.getLogLevel() != null ? request.getLogLevel() : "INFO");

        UserTracking updated = repository.save(entity);
        return mapToResponse(updated);
    }

    @Transactional
    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("UserTracking não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional
    public void deleteByUserId(String userId) {
        repository.deleteByUserId(userId);
    }

    private UserTrackingResponse mapToResponse(UserTracking entity) {
        UserTrackingResponse response = new UserTrackingResponse();
        response.setId(entity.getId());
        response.setUserId(entity.getUserId());
        response.setEventType(entity.getEventType());
        response.setPageUrl(entity.getPageUrl());
        response.setElementId(entity.getElementId());
        response.setLogLevel(entity.getLogLevel());
        response.setTimestamp(entity.getTimestamp());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}