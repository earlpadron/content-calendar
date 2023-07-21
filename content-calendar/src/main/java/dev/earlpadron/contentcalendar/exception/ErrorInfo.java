package dev.earlpadron.contentcalendar.exception;

import java.time.LocalDateTime;

public class ErrorInfo {

    private String message;
    private Integer code;
    private LocalDateTime time;

    public ErrorInfo(){};
    public ErrorInfo(String message, Integer code, LocalDateTime time) {
        this.message = message;
        this.code = code;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
