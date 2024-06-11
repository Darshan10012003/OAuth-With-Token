package com.oauth.proxy;

import com.oauth.userdetails.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

	  private String id; 
	    private String username; 
	  
	    public static UserDTO from(User user) { 
	        return UserDTO.builder()  // Corrected the reference to the builder 
	                .id(user.getId()) 
	                .username(user.getUsername()) 
	                .build(); 
	    } 
}
