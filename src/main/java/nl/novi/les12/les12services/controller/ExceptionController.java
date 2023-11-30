package nl.novi.les12.les12services.controller;

import nl.novi.les12.les12services.exceptions.DuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
//    @ExceptionHandler(value = RecordNotFoundException.class)
//    public ResponseEntity<Object> recordNotFoundException (RecordNotFoundException recordNotFoundException) {
//        return new ResponseEntity<>(recordNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
//    }
    @ExceptionHandler(value = DuplicateException.class)
    public ResponseEntity<Object> duplicateException (DuplicateException duplicateException) {
        return new ResponseEntity<>(duplicateException.getMessage(), HttpStatus.CONFLICT);
    }
}