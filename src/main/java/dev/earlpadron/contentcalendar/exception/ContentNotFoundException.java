package dev.earlpadron.contentcalendar.exception;



public class ContentNotFoundException extends RuntimeException{
    public ContentNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
