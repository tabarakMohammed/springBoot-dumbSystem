package validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = emailExistConstractor.class)
public @interface emailExist {
	 String message() default "{البريد موجود جرب بأسم أخر}";

	    Class<?>[] groups() default { };

	    Class<? extends Payload>[] payload() default { };
}
