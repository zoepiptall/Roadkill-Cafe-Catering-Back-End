package com.project2.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project2.entity.Customer;

public class CustomerValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		/*
		 * ValidationUtils.rejectIfEmpty(errors, "foodName", "foodName.empty", "foodName must have a value");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "calories", "field.required", "calories must have a value");
		Food food = (Food) target;
		if(food.getCalories() < 0) {
			errors.rejectValue("calories", "negative.value", "calories must not be negative");
		} else if(food.getCalories() > 3000) {
			errors.rejectValue("calories", "tooDangMuch", "too many calories");
		}
		 */
		
		ValidationUtils.rejectIfEmpty(errors, "username", "username.empty", "username must have a value");
		//ValidationUtils.rejectIfEmpty(errors, "password", "password.empty", "password must have a value");
		ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty", "first name must have a value");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty", "last name must have a value");
		ValidationUtils.rejectIfEmpty(errors, "email", "email.empty", "email must have a value");
		ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "phoneNumber.empty", "phone number must have a value");
		
	}

}
