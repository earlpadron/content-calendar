package dev.earlpadron.contentcalendar.exception;

/**
 * https://www.baeldung.com/java-new-custom-exception for more info on exceptions
 */
public class ContentAlreadyExistsException extends RuntimeException{

    public ContentAlreadyExistsException(String message){
        super(message);
    }

    public ContentAlreadyExistsException(String message, Throwable cause){
        super(message, cause);
    }
}
