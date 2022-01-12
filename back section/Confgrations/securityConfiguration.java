
package com.example.Uponinon.Confgrations;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



public class securityConfiguration  extends WebSecurityConfigurerAdapter{
	
	 
	    @Autowired
	    private userService myuserService;
    
	  
		public securityConfiguration(userService userService) {
			super();
			this.myuserService = userService;
		
			System.out.print(userService + "confegration");
		}

		PasswordEncoder passwordEncoder;
		@Bean
		public PasswordEncoder passwordEncoder(){
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
		}

		    @Bean
		        DaoAuthenticationProvider AuthenticationProvider()  {
		        PasswordEncoder encoder =  PasswordEncoderFactories.createDelegatingPasswordEncoder();		        
		    	 DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		         daoAuthenticationProvider.setPasswordEncoder(encoder);
		    	 daoAuthenticationProvider.setUserDetailsService(this.myuserService);
		    	 daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
		    	 return daoAuthenticationProvider;
		     }
		
		    

			
	
	
	
	@Configuration
	@EnableWebSecurity
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		 @Autowired
		    private  UserDetailsService userDetailsService;
		    

		    public class NoPopupBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {	   

				@Override
				public void commence(HttpServletRequest request, HttpServletResponse response,
						AuthenticationException authException) throws IOException, ServletException {
					// TODO Auto-generated method stub
		            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

				}

		    }

	 
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	
	    http
	    
	    .httpBasic().authenticationEntryPoint(new NoPopupBasicAuthenticationEntryPoint())
	    .and()
	  
	      .authorizeRequests()	  
	      
	      
	      
	        .antMatchers("/register","/saveUsers","/ForgotPassword",
	        		"/ResetPassword/{token}", "/forgotPasswordAction","/resendVeryfication",
	        		"/resendVeryficationToken","/reset-password","/activeAccount/{token}","/error").permitAll()
	        
		   

	       // .antMatchers(HttpMethod.POST, "/login").permitAll()
            .antMatchers("/CC/getAllUser","/CC/deleteUnactiveUser","/CC/ShowReportMsg","/CC/editingReportMsg").hasAnyAuthority("ADMIN", "manager")
            .antMatchers("/edit","/CC/ChangeRole","/CC/deleteRole").hasAnyAuthority("manager")
	        .anyRequest().authenticated()
	        

	        
	      

	        .and()
	        .formLogin()
	        .loginPage("/login")
           .defaultSuccessUrl("/welcome", true)
            .permitAll().
            and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).deleteCookies("JSESSIONID","remember-me")
            .logoutSuccessUrl("/login")
            .permitAll();
	    http.
            rememberMe().key("uniqueAndSecret")
            
            .userDetailsService(userDetailsService)
            
            .and()
            .csrf().disable();
	    
	 
	   
	    
      System.out.print("start now with tabarak Hi !! ... ");
		
	    
	  }

	}
	

}
