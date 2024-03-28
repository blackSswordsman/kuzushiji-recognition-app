package com.astra.kuzushiji.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR,reason = "TensorFLowServer returned an error")
public class TensorFlowServerException extends RuntimeException{
    public TensorFlowServerException(){
        super("TensorFLowServer returned an error");
    }


}
