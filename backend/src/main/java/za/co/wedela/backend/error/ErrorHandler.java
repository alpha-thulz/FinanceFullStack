package za.co.wedela.backend.error;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFound(UsernameNotFoundException e) {
        return errorMessages(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgument(IllegalArgumentException e) {
        return errorMessages(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentials(BadCredentialsException e) {
        return errorMessages(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> nullPointer(NullPointerException e) {
        return errorMessages(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> signature(SignatureException e) {
        return errorMessages(HttpStatus.BAD_GATEWAY, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataIntegrityViolation(DataIntegrityViolationException e) {
        return errorMessages(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    private ResponseEntity<?> errorMessages(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of(
                "timestamp", new Date(),
                "message", message,
                "code", status.value(),
                "status", status.getReasonPhrase()
        ));
    }
}