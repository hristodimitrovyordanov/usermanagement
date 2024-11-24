package com.hristo.usermanagement.user.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class UserExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public Object handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        if (isApiRequest(request)) {
            UserErrorResponse errorResponse = new UserErrorResponse();
            errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
            errorResponse.setMessage(ex.getMessage());
            errorResponse.setTimeStamp(System.currentTimeMillis());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } else {
            ModelAndView mav = new ModelAndView("error"); // Name of the HTML template (error.html)
            mav.addObject("status", HttpStatus.NOT_FOUND.value());
            mav.addObject("message", ex.getMessage());
            mav.addObject("timeStamp", System.currentTimeMillis());
            return mav;
        }
    }

    @ExceptionHandler(Exception.class)
    public Object handleGeneralException(Exception ex, HttpServletRequest request) {
        if (isApiRequest(request)) {
            UserErrorResponse errorResponse = new UserErrorResponse();
            errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            errorResponse.setMessage(ex.getMessage());
            errorResponse.setTimeStamp(System.currentTimeMillis());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } else {
            ModelAndView mav = new ModelAndView("error"); // Name of the HTML template (error.html)
            mav.addObject("status", HttpStatus.BAD_REQUEST.value());
            mav.addObject("message", ex.getMessage());
            mav.addObject("timeStamp", System.currentTimeMillis());
            return mav;
        }
    }

    private boolean isApiRequest(HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        boolean browserRequest = acceptHeader == null || acceptHeader.contains("text/html");
        boolean apiRequest = acceptHeader != null && (acceptHeader.contains("application/json") || acceptHeader.contains("*/*"));

        return apiRequest && !browserRequest;
    }
}
