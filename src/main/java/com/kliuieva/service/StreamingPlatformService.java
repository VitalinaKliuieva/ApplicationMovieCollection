package com.kliuieva.service;


import com.kliuieva.entity.StreamingPlatform;
import com.kliuieva.repositories.StreamingPlatformsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreamingPlatformService {
    private final StreamingPlatformsRepository platformRepository;

    public StreamingPlatformService(StreamingPlatformsRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public List<StreamingPlatform> getAllPlatforms() {
        return platformRepository.findAll();
    }

    public Optional<StreamingPlatform> findById(Long id) {
        return platformRepository.findById(id);
    }

    public StreamingPlatform savePlatform(StreamingPlatform platform) {
        return platformRepository.save(platform);
    }

    public void deletePlatform(Long id) {
        platformRepository.deleteById(id);
    }
}
