package com.secret.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecretResponse {
	private String secretKey;
	private String expiresAt;
	private Boolean viewed;
}
