package com.practice.board.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.practice.board.dao.AppUserDao;
import com.practice.board.entity.AppUser;
import com.practice.board.formbean.AppUserForm;

@Component
public class AppUserValidator implements Validator {
	
	//commons-validator 라이브러리
	private EmailValidator emailValidator = EmailValidator.getInstance();
	
	@Autowired
	private AppUserDao appUserDao;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == AppUserForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		AppUserForm appUserForm = (AppUserForm) target;
		
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.appUserForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.appUserForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.appUserForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.appUserForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.appUserForm.gender");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryCode", "NotEmpty.appUserForm.countryCode");
		
        if(!this.emailValidator.isValid(appUserForm.getEmail())) {
        	//이메일 형식 체크
        	errors.rejectValue("email", "Pattern.appUserForm.email");
        } else {
        	AppUser dbUser = appUserDao.findUserAccountByEmail(appUserForm.getEmail());
        	if(dbUser != null) {
        		errors.rejectValue("email", "Duplicate.appUserForm.email");
        	}
        }
        
        if (!errors.hasFieldErrors("userName")) {
        	AppUser dbUser = appUserDao.findUserAccountByUserName(appUserForm.getUserName());
        	if(dbUser != null) {
        		errors.rejectValue("userName", "Duplicate.appUserForm.userName");
        	}
        }
        
        if (!errors.hasErrors()) {
        	if (!appUserForm.getConfirmPassword().equals(appUserForm.getPassword())) {
        		errors.rejectValue("confirmPassword", "Match.appUserForm.confirmPassword");
        	}
        }
        
	}

}
