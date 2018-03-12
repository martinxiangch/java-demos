package org.validationDemo.Controller;


import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.validationDemo.model.UserInfo;
import org.validationDemo.model.UserInfo.First;

@RestController
public class UserController {
	
	 final static Logger logger = LoggerFactory.getLogger(UserController.class);
	 
	 @Autowired
	 private Validator validator;
	
	@RequestMapping("/user")
    public String user(@Validated UserInfo foo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
            	logger.error("Error:{}",fieldError.toString());
            	logger.error("Error:{}",fieldError.getDefaultMessage());
            }
            return "fail";
        }
        return "success";
    }
	
	@RequestMapping("/swim")
	public String swim(@Validated({First.class}) UserInfo user, BindingResult result) {
		if(result.hasErrors()){
            for (FieldError fieldError : result.getFieldErrors()) {
            	logger.error("Error:{}",fieldError.toString());
            	logger.error("Error:{}",fieldError.getDefaultMessage());
            }
            return "fail";
        }
        return "success";
	}
	
	@RequestMapping("/football")
	public String football(@Validated UserInfo user, BindingResult result) {
		if(result.hasErrors()){
            for (FieldError fieldError : result.getFieldErrors()) {
            	logger.error("Error:{}",fieldError.toString());
            	logger.error("Error:{}",fieldError.getDefaultMessage());
            }
            return "fail";
        }
        return "success";
	}
	
	@RequestMapping("/validate")
	public void VaildateSelf() {
		UserInfo userInfo=new UserInfo();
		userInfo.setAge(10);
		userInfo.setName("administrator");
		userInfo.setPhone("123456789");
		Set<ConstraintViolation<UserInfo>> set=validator.validate(userInfo);
		for (ConstraintViolation<UserInfo> constraintViolation : set) {
			logger.error("Error:{}",constraintViolation.getMessageTemplate());
        	logger.error("Error:{}",constraintViolation.getMessage());
	    }
	}
}
