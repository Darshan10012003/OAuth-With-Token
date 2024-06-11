package com.oauth.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

	private String userId;
	private String accessToken;
	private String refreshToken;
}
