package com.serasa.user.presentation.erros;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.serasa.user.domain.exception.UserInvalidException;
import com.serasa.user.domain.exception.UserNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleOthersExceptions(Exception ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<String>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({ BindException.class, MethodArgumentNotValidException.class, UserInvalidException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> badRequestException(Exception ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ MethodNotAllowedException.class, HttpRequestMethodNotSupportedException.class })
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<String> methodNotAllowedException(Exception ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({ UserNotFoundException.class, NoHandlerFoundException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> notFoundException(Exception ex) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<String>(ex.getMessage(),new HttpHeaders(), HttpStatus.NOT_FOUND);
    }



}