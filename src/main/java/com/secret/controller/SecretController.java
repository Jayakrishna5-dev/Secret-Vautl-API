package com.secret.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secret.dto.request.CreateSecretRequest;
import com.secret.dto.response.SecretResponse;
import com.secret.service.SecretService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SecretController {
	private final SecretService secretService;
	
	@PostMapping("/secret")
	private SecretResponse addSecret(@Valid @RequestBody CreateSecretRequest reqdto) {
		return secretService.saveSecret(reqdto);
	}
	
	@GetMapping("/secret/{token}")
	private String viewSecret(@PathVariable String token) {
		return secretService.viewSecret(token);
	}
	
	@GetMapping("/secret")
	private List<SecretResponse> viewSecrets() {
		return secretService.viewSecrets();
	}
	
	@DeleteMapping("/secret/{id}")
	private String deleteSecret(@PathVariable long id) {
		return secretService.deleteSecret(id);
	}
}