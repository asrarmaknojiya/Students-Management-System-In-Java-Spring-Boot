package com.example.sms.controller.advice;

import com.example.sms.dto.ResponseModel;
import com.example.sms.exception.DuplicateExceptionResource;
import com.example.sms.exception.MaxLimitExceptionResource;
import com.example.sms.exception.NotFoundExceptionResource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

@ExceptionHandler(DuplicateExceptionResource.class)
    public ResponseModel handleDuplicateExceptionResource (DuplicateExceptionResource ex){
return ResponseModel.conflict(ex.getMessage(),null);
    }

    @ExceptionHandler(NotFoundExceptionResource.class)
    public ResponseModel handleNotFoundExceptionResource(NotFoundExceptionResource no){
    return ResponseModel.not_found(
            no.getMessage(),null
    );
    }

    @ExceptionHandler(MaxLimitExceptionResource.class)
    public ResponseModel MaxLimitExceptionResource(MaxLimitExceptionResource no){
        return ResponseModel.not_found(
                no.getMessage(),null
        );
    }
    @ExceptionHandler(Exception.class)
    public ResponseModel handleException(Exception ex){
       return new  ResponseModel(
               HttpStatus.INTERNAL_SERVER_ERROR,
               ex.getMessage(),
               null
       );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseModel MethodArgumentNotValidException(MethodArgumentNotValidException ex){

        Map<String ,String> errors=new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach((error)->{
                    String fieldName=((FieldError) error).getField();
                    String errorMessage=error.getDefaultMessage();
                    errors.put(fieldName,errorMessage);
                });
        return new  ResponseModel(
                HttpStatus.BAD_REQUEST,
              "Validation Error",
                errors
        );
    }
}
