package com.onlinestore.exception;

import com.onlinestore.base.FieldError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.ServerException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationEx(MethodArgumentNotValidException ex) {
        Map<String, Object> errorDto = new HashMap<>();
        List<FieldError> errors = new ArrayList<>();
        ex.getFieldErrors().forEach(
                fieldError -> {
                    errors.add(new FieldError(fieldError.getField(),
                            fieldError.getDefaultMessage()));
                }
        );
        errorDto.put("code", 7000);
        errorDto.put("message", "Input validation failed, please check your input");
        errorDto.put("status", false);
        errorDto.put("timestamp", LocalDateTime.now());
        errorDto.put("errors", errors);
        return errorDto;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, Object> errorDto = new HashMap<>();
        errorDto.put("code", 9001);
        errorDto.put("message", "Data integrity violation: Duplicate key");
        errorDto.put("status", false);
        //errorDto.put("detail", ex.getMessage());
        errorDto.put("timestamp", LocalDateTime.now());

        return errorDto;
    }
}
