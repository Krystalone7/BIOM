package com.biom.controller.advice;

import com.biom.controller.advice.exception.UserProfileNotExistException;
import com.biom.pojo.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(UserProfileNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<Exception> handleException(UserProfileNotExistException e){
        return new ResponseDto<>("Error", e.getMessage(), null);
    }

}
