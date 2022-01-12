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
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.Uponinon.Model.jpa.verfiyEmailJpa;
import com.example.Uponinon.Model.user;
import com.example.Uponinon.Model.userVerifyEmail;
import com.example.Uponinon.Model.jpa.userJpa;

@Service
public class verfiyEmailService {

	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 60*24;

	
	@Autowired
	verfiyEmailJpa VEJ;
	
	@Autowired
	userJpa UJ;
	
	
	    
	
	
		public String checkEmail(String email) {
				
			
			
				
				try {
					
					user user = UJ.findByEmail(email);	
					
				
				if (VEJ.findByUser(user) != null ) {
                   
					if(isTokenExpired(VEJ.findByUser(user).getTokenDate()) == false) {
                    	

						return "Invalid, check your Email Box please ";
                  
					} else {
								
						VEJ.update(VEJ.findByUser(user).getUser().getId(),generateToken(),LocalDateTime.now());	
						

						return VEJ.findByUser(user).getToken();
        				//return saveToken(user);
                    }
					
				} else {
				

			     	return saveToken(user);
				
				}
				
				} catch (Exception e) {
					
					//System.out.println(   e.getMessage());

						return "something Wrong ....";
					
				}
				
			}
		
		
		
		
		private String  saveToken(user user) {
			
			userVerifyEmail uVerfiyEmail = new userVerifyEmail(); 
			
		


			
			uVerfiyEmail.setToken(generateToken());
			uVerfiyEmail.setTokenDate(LocalDateTime.now());

			uVerfiyEmail.setUser(user);

			VEJ.save(uVerfiyEmail);

			return uVerfiyEmail.getToken();
			
				}
		

		
		
		
		
		
		
			private String generateToken() {
				// TODO Auto-generated method stub
				StringBuilder token = new StringBuilder();
				return token.append(UUID.randomUUID().toString())
						.append(UUID.randomUUID().toString()).toString();
				
			}
			
			
			
			
			public String verifactionEamil(String token) {


				
				try {
					userVerifyEmail uVerfiyEmail = VEJ.findBytoken(token);
				

				LocalDateTime tokenCreationDate = uVerfiyEmail.getTokenDate();

				if (isTokenExpired(tokenCreationDate)) {
					
					return "filed Token expired.";

				}

				user user = UJ.findByUsername(uVerfiyEmail.getUser().getUsername());
				
				
				
				user.setIsEnabled(true);
		        uVerfiyEmail.setToken(null);
		        uVerfiyEmail.setTokenDate(null);
		        uVerfiyEmail.setUser(user);
		        //data transaction Create
		        UJ.save(user);
				VEJ.save(uVerfiyEmail);

				return "Your account successfully verfaction.";

				} catch (Exception e) {
					// TODO: handle exception
				
					return e.getMessage();

				}
			}
			
			
			private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

				LocalDateTime now = LocalDateTime.now();
				Duration diff = Duration.between(tokenCreationDate, now);

				return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
			}
			
			@Autowired
		    JavaMailSender emailSender;
		   
			@Autowired
		    private TemplateEngine htmlTemplateEngine;
		    
		    private static final String EMAIL_SIMPLE_TEMPLATE_NAME = "email-simple";

		    public void sendVerfiactionMessage(
				      String to, String subject,final Locale locale, String url,String emailMessage, String linkName) throws MessagingException {
				
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
