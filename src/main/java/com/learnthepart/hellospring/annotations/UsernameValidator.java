package com.learnthepart.hellospring.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidUsername,String> {

    @Override
    public boolean isValid(String username, ConstraintValidatorContext arg1) {
        int numDigit = 0;
        int numLowerCase = 0;
        int numUpperCase = 0;

        for(int i=0;i<username.length();i++){
            if(Character.isDigit(username.charAt(i))){
                numDigit ++;
            }
            if(Character.isLowerCase(username.charAt(i))){
                numLowerCase++;
            }
            if(Character.isUpperCase(username.charAt(i))){
                numUpperCase++;
            }
        }

        if(numDigit > 0 && numLowerCase > 0 && numUpperCase > 0) return true;        
        return false;
    }
    
}
