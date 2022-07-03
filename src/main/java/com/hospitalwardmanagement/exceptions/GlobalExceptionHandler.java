package com.hospitalwardmanagement.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleResourceNotFoundException(
                                                    ResourceNotFoundException exception,
                                                    WebRequest request) {

        ErrorObject errorObject = new ErrorObject(
                                        HttpStatus.NOT_FOUND.value(),
                                        exception.getMessage(),
                                        request.getDescription(false),
                                        new Date().toString()
        );

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleMethodArgumentTypeMismatchException(
                                                    MethodArgumentTypeMismatchException exception,
                                                    WebRequest request){
        ErrorObject errorObject = new ErrorObject(
                                        HttpStatus.BAD_REQUEST.value(),
                                        exception.getMessage(),
                                        request.getDescription(false),
                                        new Date().toString()
        );

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGeneralException(
                                        Exception exception,
                                        WebRequest request){
        ErrorObject errorObject = new ErrorObject(
                                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                        exception.getMessage(),
                                        request.getDescription(false),
                                        new Date().toString()
        );

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, Object> body = new HashMap<String, Object>();
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(x->x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("statusCode", HttpStatus.BAD_REQUEST.value());
        body.put("message", errors);
        body.put("details", request.getDescription(false));
        body.put("timestamp", new Date().toString());

        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }
}
