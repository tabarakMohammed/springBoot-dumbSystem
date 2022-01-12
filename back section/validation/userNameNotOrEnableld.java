package validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Uponinon.Model.jpa.userJpa;

public class userNameNotOrEnableld  implements ConstraintValidator<usernameNotExistOrIsEnablied, String> {

	@Autowired
	userJpa userRepo;
	

    @Override
    public void initialize(usernameNotExistOrIsEnablied constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintContext) {
    
    	if ( userRepo == null) {
            return false;
        }
    
    		
    		if(userRepo.findByUsername(username) == null || userRepo.findByUsername(username).getIsEnabled() == true ) {
    	    	
    			return false;
    	    	
    		}
    	    	
    		else {
    			
    			return true;
    	    
    		}
    	}
    	
    
    
}