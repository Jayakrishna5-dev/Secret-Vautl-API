package com.secret.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.secret.dto.request.CreateSecretRequest;
import com.secret.dto.response.SecretResponse;
import com.secret.entity.Secret;
import com.secret.exception.SecretNotFoundException;
import com.secret.exception.UserNotFoundException;
import com.secret.repository.SecretRepository;
import com.secret.user.repository.UserRepository;
import com.secret.user.userEntity.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecretService {
	private final SecretRepository secretRepo;
	private final UserRepository userRepo;
	

	public SecretResponse saveSecret(CreateSecretRequest reqdto) {
		SecretResponse sres = new SecretResponse();
		Secret ent = new Secret();
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
		String secretKey = UUID.randomUUID().toString();
		
		LocalDateTime time = LocalDateTime.now().plusMinutes(reqdto.getExpirationMinutes());
		String formattedTime = time.format(
		        DateTimeFormatter.ofPattern("dd MMM yyyy, HH : mm : ss", Locale.ENGLISH)
		).toLowerCase();
		ent.setContent(reqdto.getContent());
		ent.setCreatedBy(user);
		ent.setExpiresAt(time);
		ent.setSecretKey(secretKey);
		ent.setViewed(false);
		
		secretRepo.save(ent);
		
		sres.setSecretKey(secretKey);
		sres.setExpiresAt(formattedTime);
		sres.setViewed(false);
		
		return sres;
	}

	public String viewSecret(String token) {
		Secret ent = secretRepo.findBySecretKey(token).orElseThrow(() -> new SecretNotFoundException("Secret expired or does not exist"));
		
		if (ent.getViewed()) {
			return "Secret already destroyed";
		}
		
		ent.setViewed(true);
		ent.setViewedAt(LocalDateTime.now());
		secretRepo.save(ent);
		
		return ent.getContent();
	}

	public List<SecretResponse> viewSecrets() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
		List<Secret> result = secretRepo.findBycreatedById(user.getId());
		List<SecretResponse> response = new ArrayList<SecretResponse>();
		for(Secret s : result) {
			SecretResponse r = new SecretResponse();
			LocalDateTime time = s.getExpiresAt();
			String formattedTime = time.format(
			        DateTimeFormatter.ofPattern("dd MMM yyyy, HH : mm : ss", Locale.ENGLISH)
			).toLowerCase();
			r.setExpiresAt(formattedTime);
			r.setSecretKey(s.getSecretKey());
			r.setViewed(s.getViewed());
			response.add(r);
		}
		return response;
	}

	public String deleteSecret(long id) {
		Secret secret = secretRepo.findById(id)
	            .orElseThrow(() ->
	                    new SecretNotFoundException("Secret not found with id : " + id));

	    secretRepo.delete(secret);
		return "Secret Deleted";
	}

}
