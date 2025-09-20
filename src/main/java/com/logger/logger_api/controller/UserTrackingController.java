package com.logger.logger_api.controller;

import com.logger.logger_api.dto.UserTrackingRequest;
import com.logger.logger_api.dto.UserTrackingResponse;
import com.logger.logger_api.service.UserTrackingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-tracking")
@RequiredArgsConstructor
public class UserTrackingController {

    private final UserTrackingService service;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Logger API is running");
    }

    @PostMapping
    public ResponseEntity<UserTrackingResponse> create(@Valid @RequestBody UserTrackingRequest request) {
        UserTrackingResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTrackingResponse> findById(@PathVariable String id) {
        UserTrackingResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserTrackingResponse>> findByUserId(@PathVariable String userId) {
        List<UserTrackingResponse> responses = service.findByUserId(userId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserTrackingResponse> update(@PathVariable String id,
                                                       @Valid @RequestBody UserTrackingRequest request) {
        UserTrackingResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteByUserId(@PathVariable String userId) {
        service.deleteByUserId(userId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}