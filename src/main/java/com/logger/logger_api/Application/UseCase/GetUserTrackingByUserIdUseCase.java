package com.logger.logger_api.Application.UseCase;

import com.logger.logger_api.Application.Dtos.UserTrackingDTO;
import com.logger.logger_api.Domain.Entity.UserTracking;
import com.logger.logger_api.Domain.Repository.InteractionLogRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.projection.EntityProjection.ProjectionType.DTO;

@Component
public class GetUserTrackingByUserIdUseCase {

    private  final InteractionLogRepository repository;

    public GetUserTrackingByUserIdUseCase(InteractionLogRepository repository){
        this.repository = repository;
    }

    public UserTracking execute(String userId) {
        try {
            Long id = Long.parseLong(userId);
            UserTracking entity = repository.findById(id);
            System.out.println(entity.getId());
            if (entity == null) {
                throw new RuntimeException("No UserTracking found for ID: " + userId);
            }

            return  entity;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid ID format: " + userId, e);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching UserTracking", e);
        }
    }

}
