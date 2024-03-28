package com.astra.kuzushiji.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler()
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            HttpStatus status = responseStatus.value();
            String reason = responseStatus.reason();
            if (reason.isEmpty()) {
                // Если атрибут reason пуст, можно использовать сообщение из исключения
                reason = ex.getMessage();
            }
            return ResponseEntity.status(status).body(reason);
        }
        // Если у исключения нет аннотации @ResponseStatus, вернуть стандартный ответ
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }
}