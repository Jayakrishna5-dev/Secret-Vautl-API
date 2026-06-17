package com.secret.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secret.entity.Secret;

@Repository
public interface SecretRepository extends JpaRepository<Secret, Long> {

	void deleteByExpiresAtBefore(LocalDateTime now);
	
	Optional<Secret> findBySecretKey(String secretKey);
	
	List<Secret> findBycreatedById(long id);
		
}
