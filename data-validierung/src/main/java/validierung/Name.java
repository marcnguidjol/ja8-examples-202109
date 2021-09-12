package validierung;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validierung.impl.NameValidator;

@Retention(RUNTIME)
@Target({ FIELD, PARAMETER, TYPE_USE })
@Constraint(validatedBy = NameValidator.class)
public @interface Name {

	//@formatter:off
	String message() default "Gueltige Werte: Laenge zwischen " 
							+ ValidierungContants.MIN_LAENGE_NAME 
							+ " und " 
							+ ValidierungContants.MAX_LAENGE_NAME 
							+ " Zeichen";
	//@formatter:on
	Class<? extends Payload>[] payload() default {};

	Class<?>[] groups() default {};

	int minLaenge();
	int maxLaenge();

}
