package com.oauth.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {

	@GetMapping("/mess")
	public String getMethodNahjh() {
		return "hello";
	}
	
}
