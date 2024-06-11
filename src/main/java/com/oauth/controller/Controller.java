package com.oauth.controller;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.config.TokenGenerator;
import com.oauth.proxy.Login;
import com.oauth.proxy.SignUp;
import com.oauth.userdetails.User;

import ch.qos.logback.core.subst.Token;

@RestController
@RequestMapping("/api/auth")
public class Controller {

	@Autowired
	public UserDetailsManager userDetailsManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public TokenGenerator tokenGenerator;

	@Autowired
	public DaoAuthenticationProvider daoAuthenticationProvider;

	@Autowired
	@Qualifier("jwtRefreshTokenAuthProvider")
	public JwtAuthenticationProvider refreshTokenAuthProvider;

//	    @PostMapping("/register") 
//	    public ResponseEntity register(@RequestBody SignUp signupDTO) { 
//	        User user = new User(signupDTO.getUserName(), signupDTO.getPassword()); 
//	        userDetailsManager.createUser(user); 
//	  
//	        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(user, signupDTO.getPassword(), Collections.emptyList()); 
//	  
//	        return ResponseEntity.ok(tokenGenerator.createToken(authentication)); 
//	    } 
	
	@PostMapping("registerUser")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		System.err.println("Response From Register Api---> " + user);
	try {
		userDetailsManager.createUser(user);
		UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(user, passwordEncoder.encode(user.getPassword()), null);
		return ResponseEntity.ok(tokenGenerator.createToken(authenticated)); 
		
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	 
		
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login loginDTO) {
		System.out.println("data"+loginDTO	);
		try {
			Authentication authentication = daoAuthenticationProvider.authenticate(
					UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getUserName(), loginDTO.getPassword()));
			System.err.println("AUTHENTICATION----> " + authentication);
			return ResponseEntity.ok(tokenGenerator.createToken(authentication));
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/token")
	public ResponseEntity token(@RequestBody com.oauth.proxy.Token tokenDTO) {
		System.out.println("token"+tokenDTO);
		
	try {
		Authentication authentication = refreshTokenAuthProvider
				.authenticate(new BearerTokenAuthenticationToken(tokenDTO.getRefreshToken()));
//		Jwt jwt = (Jwt) authentication.getCredentials();
		authentication.getCredentials();
		return ResponseEntity.ok(tokenGenerator.createToken(authentication));
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return null;
		
	}
		// check if present in db and not revoked, etc


	}
	
}
