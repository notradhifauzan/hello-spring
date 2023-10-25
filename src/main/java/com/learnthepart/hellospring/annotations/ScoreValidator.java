package com.learnthepart.hellospring.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// this is the validation logic
public class ScoreValidator implements ConstraintValidator<ValidScore,Double> {

    @Override
    public boolean isValid(Double scores, ConstraintValidatorContext arg1) {
        if(scores > 100 || scores < 0) return false;
        return true;
    }
    

   

}
