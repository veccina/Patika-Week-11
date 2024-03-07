package dev.patika.libraryManagementnew.core.config;

import dev.patika.libraryManagementnew.core.exception.NotFoundException;
import dev.patika.libraryManagementnew.core.result.Result;
import dev.patika.libraryManagementnew.core.result.ResultData;
import dev.patika.libraryManagementnew.core.utilies.ResultHelper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e) {

        List<String> validationErrorList = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResultHelper.validateError(validationErrorList), HttpStatus.BAD_REQUEST);

    }
}
