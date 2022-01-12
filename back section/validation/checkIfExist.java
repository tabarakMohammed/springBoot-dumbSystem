package validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Uponinon.Model.jpa.userJpa;

public class checkIfExist implements ConstraintValidator<checkUsername, String> {

	@Autowired
	userJpa userRepo;
	
    //private checkUsername CheckUsername;

    @Override
    public void initialize(checkUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintContext) {
    
    	if ( userRepo == null ) {
            return true;
        }
    
    		
    		if(userRepo.findByUsername(username) == null  ) {
    	    	
    			return true;
    	    	
    		}
    	    	
    		else {
    			
    			return false;
    	    
    		}
    	}
    	
    
    
}