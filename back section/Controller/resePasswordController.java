package com.example.Uponinon.Controller;

import java.net.MalformedURLException;
import java.util.Locale;

import javax.mail.MessagingException;

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
import org.springframework.web.bind.annotation.RequestParam;


import com.example.Uponinon.Model.userAuth;

import servicesMethod.forgotPasswordService;

@Controller
@RequestMapping("/")
public class resePasswordController {


	@Autowired
	forgotPasswordService FPS;
	
	 
	   
	@GetMapping("/ForgotPassword")
    public String ForgotPassword( Model model ) {
		
		model.addAttribute("userAuth", new userAuth());

        return "authntucation/ForgotPassword";
        
    }
	
	@RequestMapping(value = "forgotPasswordAction", method = RequestMethod.POST)
		public String forgotPasswordAction( @Valid userAuth getEmail, BindingResult error, 
				 final Locale locale, Model model ) throws MalformedURLException, MessagingException {

		 
		  
		    
		    if(error.hasFieldErrors("email")) {
		    	
				return "authntucation/ForgotPassword";

		    } else {
			
		    String response = FPS.forgotPassword(getEmail.getEmail());   
			
			if (!response.startsWith("Invalid")) {
				
				String link = "https://www.jedaralthiqa.com/ResetPassword/" + response;	
				
				FPS.sendSimpleMessage(getEmail.getEmail(),"change Password jeddar althiqa",
						locale,
						link,
						"please Follow belwo Link to reset Password",
						"RESET PASSWORD");
				
				return "messageSent";

			}
			model.addAttribute("token", response);
			return "authntucation/ForgotPassword";
		   
		    }
		 
	}
	
	@GetMapping(value = "/ResetPassword/{token}")
    public String ResetPassword( @PathVariable("token") String token,Model model ) {
		
		model.addAttribute("token", token);

        return "authntucation/ResetPassword";
        
    }
	
	
	//try to use Put Method
	 @PostMapping("/reset-password")
	public String resetPassword(@RequestParam String token,
			@RequestParam String password, Model model) {
		String response = FPS.resetPassword(token, password);
		
		model.addAttribute("token", response);
		
		return "redirect:authntucation/login";

	}
	
		

	   
}
