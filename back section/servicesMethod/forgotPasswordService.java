package servicesMethod;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.Uponinon.Model.user;
import com.example.Uponinon.Model.userForgotPassword;
import com.example.Uponinon.Model.jpa.forgetPasswordJpa;
import com.example.Uponinon.Model.jpa.userJpa;




@Service
public class forgotPasswordService {

	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;


    
	@Autowired
	forgetPasswordJpa FPJ;
	
	@Autowired
	userJpa UJ;
	
	public String forgotPassword(String email) {
		
	
		
		try {
			
		user user = UJ.findByEmail(email);
		if(user == null) {
			return "Invalid user or email not found";
		} 
		//change to update IfExistsUser in forgot table
		//FPJ.delete(FPJ.findByUser(user));
		if(FPJ.findByUser(user) != null) {
			

			
			if(FPJ.findByUser(user).getTokenDate() == null)  {
				FPJ.update(FPJ.findByUser(user).getUser().getId(),generateToken(),LocalDateTime.now());				
				return FPJ.findByUser(user).getToken();
			} else {
			
			
			if(isTokenExpired(FPJ.findByUser(user).getTokenDate()) == false) {    

            	return "Invalid check your Email";         	
			}
			else {
				
			
			FPJ.update(FPJ.findByUser(user).getUser().getId(),generateToken(),LocalDateTime.now());
			return FPJ.findByUser(user).getToken();
		
			} 	
			
		}
			} else { 
		userForgotPassword uForgotPass = new userForgotPassword(); 
		
		uForgotPass.setToken(generateToken());
		uForgotPass.setTokenDate(LocalDateTime.now());		
		uForgotPass.setUser(user);

		FPJ.save(uForgotPass);
		

		return uForgotPass.getToken();
	    	
		}
		
		} catch (Exception e) {
			

				return "Invalid email" + e.getMessage();
			
		}
	}

	private String generateToken() {
		// TODO Auto-generated method stub
		StringBuilder token = new StringBuilder();
		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
		
	}
	
	
	
	
	
	
	public String resetPassword(String token, String password) {

//		Optional<User> userOptional = Optional
//				.ofNullable(userRepository.findByToken(token));
//
//		if (!userOptional.isPresent()) {
//			return "Invalid token.";
//		}
		
		try {
		userForgotPassword uForgotPass = FPJ.findBytoken(token);
		

		LocalDateTime tokenCreationDate = uForgotPass.getTokenDate();

		if (isTokenExpired(tokenCreationDate)) {
			
			return "Token expired.";

		}

		user user = UJ.findByUsername(uForgotPass.getUser().getUsername());
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String passEnco = encoder.encode(password);
		
		user.setPassword(passEnco);
		uForgotPass.setToken(null);
		uForgotPass.setTokenDate(null);
        uForgotPass.setUser(user);
		
        //data transaction Create
        UJ.save(user);
		FPJ.save(uForgotPass);

		return "Your password successfully updated.";

		} catch (Exception e) {
			// TODO: handle exception
		
			return e.getMessage();

		}
	}
	
	
	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);
         boolean isEx = diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
      ///   System.out.println(isEx + "hi"); 
		 return isEx;
	}
	
	
	
	
	@Autowired
    JavaMailSender emailSender;
   
	@Autowired
    private TemplateEngine htmlTemplateEngine;
    
    private static final String EMAIL_SIMPLE_TEMPLATE_NAME = "email-simple";

    public void sendSimpleMessage(
		      String to, String subject,final Locale locale, String url, String emailMessage, String linkName) throws MessagingException {
		
		       final Context ctx = new Context(locale);
		        ctx.setVariable("url", url);
		        ctx.setVariable("message", emailMessage);
		        ctx.setVariable("linkName", linkName);
		      
		       final String htmlContent = this.htmlTemplateEngine.process(EMAIL_SIMPLE_TEMPLATE_NAME, ctx);

		       MimeMessage messageMime  = emailSender.createMimeMessage();
		       MimeMessageHelper message = new MimeMessageHelper(messageMime, true);
		       
		        message.setFrom("support@jedaralthiqa.com");
		        message.setTo(to); 
		        message.setSubject(subject); 
		        message.setText(htmlContent, true);
		        
		        emailSender.send(messageMime);
		        
		        
		    }
	
	
	
	
	
	
	
}
