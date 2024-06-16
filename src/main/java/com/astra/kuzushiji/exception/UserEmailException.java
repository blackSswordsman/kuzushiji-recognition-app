package com.astra.kuzushiji.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason ="Could not get user's email" )
public class UserEmailException extends RuntimeException{
    public UserEmailException (){
        super("Could not get user's email");
    }


}
