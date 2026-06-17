package com.secret.service;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.secret.repository.SecretRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CleanupService {
	private final SecretRepository secretRepo;
	
	@Transactional
	@Scheduled(fixedRate = 60000)
    public void removeExpiredSecrets() {

        secretRepo.deleteByExpiresAtBefore(
                LocalDateTime.now()
        );
    }
}
