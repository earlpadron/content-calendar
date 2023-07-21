package dev.earlpadron.contentcalendar.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;

/**
 * @RestControllerAdvice annotation is an interceptor that surrounds the logic in the controller and allows the application of some common logic
 * > objects returned is automatically serialized into JSON and passed to the HttpResponse object
 * @ExceptionHandler shares advice's methods across all controllers providing a centralized location for capturing exceptions and translating them
 * into HTTP responses
 *
 *
 * More Info :
 * https://www.baeldung.com/exception-handling-for-rest-with-spring
 * https://www.baeldung.com/spring-response-status-exception
 */

@RestControllerAdvice
public class ExceptionControllerAdvice {
    private static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);

    @Autowired
    private Environment environment;

    @ExceptionHandler(ContentNotFoundException.class)

    public ResponseEntity<ErrorInfo> contentNotFoundExceptionHandler(ContentNotFoundException exception){
        LOGGER.error(exception.getMessage(), exception);
        //1. create a payload containing exception details
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode(HttpStatus.NO_CONTENT.value());
        errorInfo.setTime(LocalDateTime.now());
        errorInfo.setMessage(environment.getProperty(exception.getMessage()));

        //2. return new response entity
       return new ResponseEntity<>(errorInfo, HttpStatus.NO_CONTENT);

        //return new ResponseStatusException(HttpStatus.NO_CONTENT, "no content");
    }

    @ExceptionHandler(ContentAlreadyExistsException.class)
    public ResponseEntity<ErrorInfo> contentAlreadyExistsException(ContentAlreadyExistsException exception){
        LOGGER.error(exception.getMessage(), exception);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setTime(LocalDateTime.now());
        errorInfo.setMessage(environment.getProperty(exception.getMessage()));
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }


}
