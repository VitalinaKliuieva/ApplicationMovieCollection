package com.kliuieva.repositories;

import com.kliuieva.entity.StreamingPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingPlatformsRepository  extends JpaRepository<StreamingPlatform, Long> {
}
