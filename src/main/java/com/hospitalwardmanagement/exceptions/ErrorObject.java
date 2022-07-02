package com.hospitalwardmanagement.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorObject {

    private Integer statusCode;
    private String message;
    private String details;
    private String timestamp;

}
