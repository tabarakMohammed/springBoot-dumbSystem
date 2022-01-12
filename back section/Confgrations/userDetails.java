package com.example.Uponinon.Confgrations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Uponinon.Model.role;
import com.example.Uponinon.Model.user;

 

public class userDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	user userObj = new user();
	
	public userDetails(user userObje) {
		
		super();
		this.userObj = userObje;
	}

	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<role> roles = this.userObj.getRoles();
	    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	         
	        for (role role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
	        }
	         
	        return authorities;
	}

	public long getid() {
		return this.userObj.getId();
	}
	
	@Override
	public String getPassword() {
		return this.userObj.getPassword();
	}

	@Override
	public String getUsername() {
		return  this.userObj.getUsername();
	}

	
//	public String getEmail() {
//		return this.userObj.getEmail();
//	}

	
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
         if (this.userObj.getIsEnabled() == null) {
        	 return true;
         } else
        	 return this.userObj.getIsEnabled();
          
	//	return true;
	}

}
