package com.oauth.config;

import java.util.Collections;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.oauth.userdetails.User;

@Component
public  class JWTtoUserConvertor implements Converter<Jwt, UsernamePasswordAuthenticationToken> {
	@Override
	public UsernamePasswordAuthenticationToken convert(Jwt source) {
		User user = new User();
		user.setId(source.getSubject());
		return new UsernamePasswordAuthenticationToken(user, source, Collections.emptyList());
	}


}
