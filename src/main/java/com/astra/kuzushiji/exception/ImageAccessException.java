package com.astra.kuzushiji.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN,reason = "User does not have access to the image")
public class ImageAccessException extends RuntimeException{
    public ImageAccessException(){
        super("User does not have access to the image");
    }


}
