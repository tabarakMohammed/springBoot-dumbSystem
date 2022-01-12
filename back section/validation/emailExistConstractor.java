package validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Uponinon.Model.jpa.userJpa;


public class emailExistConstractor implements ConstraintValidator<emailExist, String> {

	@Autowired
	userJpa userRepo;
	

    @Override
    public void initialize(emailExist constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintContext) {
    
    	if ( userRepo == null ) {
            return true;
        }
    
    		
    		if(userRepo.findByEmail(email) == null  ) {

    			return true;
    		}
    	    	
    		else {
    			return false;
    	    
    		}
    	}
    	
    
    
}