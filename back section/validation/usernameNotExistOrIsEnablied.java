package validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = userNameNotOrEnableld.class)
@Documented
public @interface usernameNotExistOrIsEnablied {
	 String message() default "{أسم المستخدم غير موجود أو ان الحساب مفعل، تأكد من أدخال أسم صحيح}";

	    Class<?>[] groups() default { };

	    Class<? extends Payload>[] payload() default { };
}
