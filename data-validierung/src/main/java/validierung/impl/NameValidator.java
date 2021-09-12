package validierung.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import validierung.Name;

public class NameValidator implements ConstraintValidator<Name, String> {

	private int min;
	private int max;
	
	@Override
	public void initialize(Name constraintAnnotation) {
		this.min = constraintAnnotation.minLaenge();
		this.max = constraintAnnotation.maxLaenge();
	}
	
	@Override
	public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		if (StringUtils.trim(str).length() < min) {
			return false;
		}
		if (StringUtils.trim(str).length() > max) {
			return false;
		}
		return true;
	}

}
