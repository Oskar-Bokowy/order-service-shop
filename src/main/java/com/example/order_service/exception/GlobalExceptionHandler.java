package com.example.order_service.exception;

import com.example.order_service.exception.exception.OrderNotFoundException;
import com.example.order_service.exception.exception.RemoteServiceException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private static final Logger log = createLogger();

    private static Logger createLogger() {
        return LoggerFactory.getLogger(GlobalExceptionHandler.class);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleOrderNotFound(OrderNotFoundException ex) {
        ErrorMessage error = new ErrorMessage(
                ex.getMessage(),
                LocalDateTime.now(),
                ex.getHttpStatus().value()
        );
        return ResponseEntity.status(ex.getHttpStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationException(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage error = new ErrorMessage(
                returnDefaultMessageFromValidationException(ex.getMessage()),
                LocalDateTime.now(),
                status.value()
        );
        return ResponseEntity.status(status).body(error);
    }

    private static String returnDefaultMessageFromValidationException(String message) {
        Pattern pattern = Pattern.compile("default message \\[(.*?)]");
        Matcher matcher = pattern.matcher(message);

        String result = "";
        while (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }


    @ExceptionHandler(RemoteServiceException.class)
    public ResponseEntity<ErrorMessage> handleRemoteService(RemoteServiceException ex) {
        ErrorMessage error = returnDefaultMessageFromRemoteService(ex.getMessage());
        return ResponseEntity.status(error.getHttpStatus()).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrationException(DataIntegrityViolationException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorMessage error = new ErrorMessage(
                "DB ERROR",
                LocalDateTime.now(),
                status
        );
        return ResponseEntity.status(status).body(error);
    }


    //    @ExceptionHandler(HttpClientErrorException.class)
//    public ResponseEntity<ErrorMessageFromClient> handleClientNotFound(HttpClientErrorException ex){
//        ErrorMessageFromClient error = new ErrorMessageFromClient(
//            ex.getMessage()
//        );
//        return ResponseEntity.status(404).body(error);
//    }

//    @ExceptionHandler(HttpClientErrorException.class)
//    public ResponseEntity<ErrorMessage> handleClientNotFound(HttpClientErrorException ex){
//        ErrorMessage error = returnDefaultMessage(ex.getMessage());
//        return ResponseEntity.status(404).body(error);
//    }

    private static ErrorMessage returnDefaultMessageFromRemoteService(String message) {
        Pattern messagePattern = Pattern.compile("\"message\":\"(.*?)\"");
        Pattern timePattern = Pattern.compile("\"localDateTime\":\"(.*?)\"");
        Pattern statusPattern = Pattern.compile("\"httpStatus\":\"(\\d+)");
        Matcher messageMatcher = messagePattern.matcher(message);
        Matcher timeMatcher = timePattern.matcher(message);
        Matcher statusMatcher = statusPattern.matcher(message);

        String errorMessage = "External service error";
        LocalDateTime dateTime = LocalDateTime.now();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (messageMatcher.find()) {
            errorMessage = messageMatcher.group(1);
        }

        if (timeMatcher.find()) {
            dateTime = LocalDateTime.parse(timeMatcher.group(1));
        }

        if (statusMatcher.find()) {
            httpStatus = Integer.parseInt(statusMatcher.group(1));
        }
        return new ErrorMessage(errorMessage, dateTime, httpStatus);
    }

}
