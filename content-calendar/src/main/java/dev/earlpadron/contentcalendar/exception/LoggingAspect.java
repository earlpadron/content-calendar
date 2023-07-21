package dev.earlpadron.contentcalendar.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect

public class LoggingAspect {
    private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

//    @AfterThrowing(pointcut = "execution(* dev.earlpadron.contentcalendar.service.ContentServiceImpl.*(..))", throwing = "exception")
//    public void logServiceException(ContentNotFoundException exception){
//        LOGGER.error(exception.getMessage(), exception);
//    }
}
