package com.onlinestore.exception;

import com.onlinestore.base.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class FileUploadException {
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public BaseError<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        return BaseError.builder().message("Something went wrong!")
                .status(false)
                .code(7020)
                .timestamp(LocalDateTime.now())
                .errors(ex.getMessage())
                .build();
    }
}
