package com.example.Uponinon.Controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Uponinon.Model.ResponseJson;
import com.example.Uponinon.Model.message;
import com.example.Uponinon.Model.messageReporter;
import com.example.Uponinon.Model.profile;
import com.example.Uponinon.Model.user;
import com.example.Uponinon.Model.jpa.messageJpa;
import com.example.Uponinon.Model.jpa.messageReporterRepo;
import com.example.Uponinon.Model.jpa.profileJpa;
import com.example.Uponinon.Model.jpa.userJpa;

@RestController
@RequestMapping("/api")
public class meassageRest {

	@Autowired
	private messageReporterRepo MReporterRepo;
	@Autowired
    private userJpa userRepo;
	@Autowired
    private profileJpa pRepo;
	@Autowired
	private messageJpa Mjpa;
	
	 @PostMapping("/save")
	 public ResponseJson postCustomer( @RequestBody  @Validated message message, BindingResult bindingResult) {
	
		 
		 

		   if(bindingResult.hasErrors()){
	    		 
	    		 			 
	    		 
	    		  ResponseJson Response = new ResponseJson("Error" , "not Valid");
	    		  
	  		    
	    		  return Response;
	    		
	    	 } else {
	    	 
	    		
	    	
//	    		 try {
//	    			    
//	    			   Thread.sleep(1 * 1000);
//	    			    
//	    			    
//	    			} catch (InterruptedException ie) {
//	    			
//	    				Thread.currentThread().interrupt();
//	    			
//	    			}
	    		 
	    		 
	    		 
	    		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	//	 String userName = authentication.getName();
	    		 
	    	      
	  
	    		 user userObj = userRepo.findByUsername(authentication.getName());
	    		 
	    		 if(message.getId_recive() == userObj.getId()) {
	    			 
	    			 
		    		  ResponseJson Response = new ResponseJson("Error" , "super nice" );
		    		  
		  		    
		    		  return Response;

	    		 } else {
	    			 Date date = new Date();
	    		 message.setId_sender(userObj.getId());
	    		 message.setMesssageDate(date);
	    		 Optional<user> userObject = userRepo.findById(message.getId_recive());
	    		 
	    		 
	    		 profile userPro = pRepo.findByUser(userObject);
	    		 
	    		 
	    		 Set<message> msg = new HashSet<message>();
	    		 msg.add(message);
	    		 userPro.setMessage(msg); 
	    		
	    		 message.setProfile(userPro);

	    		
	    		 
	    		 try {
	    			 
	    			 
	        		 Mjpa.save(message);

	        			
					 ///System.out.println("Done");

	        		  
	        		  ResponseJson Response = new ResponseJson("Done" , message.getMessageContent());
	        		  
	        		    
	        		  return Response;
	        		  
				} catch (Exception e) {
					// TODO: handle exception
					 System.out.println(e.getMessage());
					 
	        		  ResponseJson Response = new ResponseJson("Error" , "I am sorry !!, i cant send");
	        		  
	        		    
	        		  return Response;
				}
	    		  
	  
		 
		 
		 
		
		  }
	 }


	 }
	 @PostMapping("/report")
	 public ResponseJson messageReport( @RequestBody @Validated messageReporter Reporter, BindingResult bindingResult) {
		   
		 if(bindingResult.hasErrors() ){
	    		 
	    		 			 
	    		 
	    		  ResponseJson Response = new ResponseJson("Error" , "not Valid");
	    		  
	  		    
	    		  return Response;
	    		
	    	 } else {
	    		
	    		 MReporterRepo.save(Reporter);
	    		 ResponseJson Response = new ResponseJson("Done" , "Valid");

		 return Response;
	    	 }
	 }
}

