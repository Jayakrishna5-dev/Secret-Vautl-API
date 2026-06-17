package com.secret.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSecretRequest {
	private String content;
	private long expirationMinutes;
}
