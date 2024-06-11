package com.oauth.userdetails;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "user_table")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

	@Id
    private String id; 
  
    @NonNull
    private String userName; 
  
    @NonNull
    private String password; 
      
  
    // Methods required by Spring Security for user details 
    public Collection<? extends GrantedAuthority> getAuthorities() { 
        return Collections.emptyList(); 
    } 
  
    public boolean isAccountNonExpired() { 
        return true; 
    } 
  
    public boolean isAccountNonLocked() { 
        return true; 
    } 
  
    public boolean isCredentialsNonExpired() { 
        return true; 
    } 
  
    public boolean isEnabled() { 
        return true; 
    }

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	} 
    

      
    
}
