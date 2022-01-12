package com.example.Uponinon.Controller;

import java.net.MalformedURLException;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Uponinon.Model.user;
import com.example.Uponinon.Model.userAuth;
import com.example.Uponinon.Model.jpa.userJpa;

import servicesMethod.userServces;
import servicesMethod.verfiyEmailService;


@Controller
@RequestMapping("/")
public class AuthRegController {
	
	@Autowired
	   private userJpa userRepo;
	@Autowired
      private verfiyEmailService veryfication;
 
	@Autowired
	private userServces nUserServces;
	
    @GetMapping("login")
    public String login() {
    	

    		
    		 return "authntucation/login";
    	
    	
       	
    }
    
    @GetMapping("/edit")
    public String edit(Model model) {
    	//create data Object and send it to view form
		model.addAttribute("user", new user());
    return "adminPages/edit";
    }
     
    @GetMapping("register")
    public String sginIn(Model model) {
    	//create data Object and send it to view form
		model.addAttribute("user", new user());
	    model.addAttribute("notValid", "notValid");
    return "authntucation/register";
    }


    @PostMapping("saveUsers")
    	public String saveUser(@Valid user user, BindingResult bindingResult,HttpServletRequest request
    			, final Locale locale, Model model) {
    	
    	 if(bindingResult.hasErrors()){
    		
    		 model.addAttribute("notValid", "notValid");
    		 return"authntucation/register";
    		 
    	 }  else {
    	 
			   nUserServces.createUser(user);

			  String response = veryfication.checkEmail(user.getEmail());
			
			  System.out.println(response);
			  
			  if (!response.startsWith("Invalid")) {
					

				  
				  String link = "https://www.test.com/activeAccount/" + response;									
				  


					try {
						
					
						
						veryfication.sendVerfiactionMessage(user.getEmail(),"jeddar althiqa",
								locale,
								link,
								"please Follow belwo Link to Active Account",
								"ACTIVE ACCOUNT");
					

					
						return "messageSent";
						
					} catch (Exception e) {
						System.out.print("from catch ...." + response);
						model.addAttribute("errorVerfey", e.getMessage());

						return "authntucation/register";
					}
					
					

				}
			 

				model.addAttribute("errorVerfey", response);

			return"authntucation/register";
    	 }
    	}
   
    
	@GetMapping(value = "/activeAccount/{token}")
    public String activeAccount( @PathVariable("token") String token ) {
		
		String response = veryfication.verifactionEamil(token);

		if(!response.startsWith("filed")){
        return "authntucation/login";
		}
		
		 return "/error";
    }
	


	@RequestMapping(value = "resendVeryficationToken", method = RequestMethod.POST)
	public String resendVeryficationToken ( @Valid userAuth username, BindingResult binidError,final Locale locale, Model model)
			throws MalformedURLException, MessagingException{
   

		
	    if(binidError.hasFieldErrors("username")) {
	    	

					return "authntucation/resendVeryfication";

			    }  
	  
	    String response ;
			
	    
        	user user  = userRepo.findByUsername(username.getUsername()); 
	        response = veryfication.checkEmail(user.getEmail());
			
	    
	 
	  if (!response.startsWith("Invalid") ) {
			
			String link = "https://www.test.com/activeAccount/" + response;	
			
			try {
			
				veryfication.sendVerfiactionMessage(user.getEmail(),"jeddar althiqa",
						locale,
						link,
						"please Follow belwo Link to Active Account",
						"ACTIVE ACCOUNT");
			
				System.out.print(response);
				return "messageSent";
				
			} catch (Exception e) {
				
				model.addAttribute("errorVerfey", "24 hours for resend" + e.getMessage());
				return "authntucation/resendVeryfication";
			}
			
		
        } else {
        	
			model.addAttribute("errorVerfey", "in++" + response);
      
		return "authntucation/resendVeryfication";
        
        }

		 
	}

	
	
	@GetMapping("/resendVeryfication")
	public String resendVeryfication( Model model ) {
		
		model.addAttribute("userAuth", new userAuth());

	    return "authntucation/resendVeryfication";
	    
	}

	
}






