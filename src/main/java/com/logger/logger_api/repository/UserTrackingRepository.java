package com.logger.logger_api.repository;

import com.logger.logger_api.model.UserTracking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTrackingRepository extends JpaRepository<UserTracking, String> {

    List<UserTracking> findByUserIdOrderByTimestampDesc(String userId);

    Page<UserTracking> findAll(Pageable pageable);

    void deleteByUserId(String userId);
}