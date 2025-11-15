package com.linectf2024.g0tchag0tchadoggy.util

import com.linectf2024.g0tchag0tchadoggy.dto.GotchaRequest
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class GotchaValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return GotchaRequest::class.java.isAssignableFrom(clazz)
    }
    override fun validate(target:Any, errors: Errors){
        val gotchaRequest = target as GotchaRequest

        if ( gotchaRequest.userName.length > 50){
            errors.rejectValue("userName", "userName.length", "Message size too long.")
        }
        if ( gotchaRequest.dateTime.length > 25){
            errors.rejectValue("dateTime", "dateTime.length", "Size too long.")
        }
        if(gotchaRequest.userNumbers.size>20){
            errors.rejectValue("userNumbers", "userNumbers.size", "Message size must be less than or equal to 20 ")
        }
        if ( gotchaRequest.dateTime.isEmpty()){
            errors.rejectValue("dateTime", "dateTime.length", "DateTime is empty.")
        }
        if (gotchaRequest.userNumbers.isEmpty()) {
            errors.rejectValue("userNumbers", "field.required", "userNumbers cannot be null or empty")
        }


    }
}