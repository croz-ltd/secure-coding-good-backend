package net.croz.owasp.goodexample.advice;

import net.croz.owasp.goodexample.exception.EntityNotFoundException;
import net.croz.owasp.goodexample.exception.FileTypeException;
import net.croz.owasp.goodexample.exception.StorageFileNotFoundException;
import net.croz.owasp.goodexample.exception.TransactionLimitException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>(
            exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileTypeException.class)
    public ResponseEntity<Object> handleFileTypeException() {
        return new ResponseEntity<>(
            "Invalid file type", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<Object> handleStorageNotFoundException() {
        return new ResponseEntity<>(
            "File not found", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionLimitException.class)
    public ResponseEntity<Object> handleTransactionLimitException() {
        return new ResponseEntity<>(
            "Daily transaction limit reached", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
