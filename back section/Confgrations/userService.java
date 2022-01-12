package com.example.Uponinon.Confgrations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Uponinon.Model.user;
import com.example.Uponinon.Model.jpa.userJpa;

@Service("userDetails")
public class userService implements UserDetailsService{
	
	@Autowired
	userJpa userRepo;

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	public userService(userJpa userRepo) {
		super();
		this.userRepo = userRepo;
	}


	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws BadCredentialsException, UsernameNotFoundException  {
		user userOb;
		
 

		userOb = this.userRepo.findByUsername(username);	
		 
		if(userOb == null) {
			
			   
			   throw new BadCredentialsException(SpringSecurityMessageSource.getAccessor().
					   getMessage("AbstractUserDetailsAuthenticationProvider.UserUnknown",
							   new Object[] {},"User is not known"));

	    	} else 
		    	
	    		if(userOb.getIsEnabled() == null ||  userOb.getIsEnabled() == false)
		 
			   {
		    		throw new DisabledException("User is Disabled");
		    	//throw new BadCredentialsException("User is not enablid");
			  
			   } else 
			   
			   {
				   
				   userDetails userid = new userDetails(userOb);
		           return userid;
		           
		 	   }
        
	}

}
