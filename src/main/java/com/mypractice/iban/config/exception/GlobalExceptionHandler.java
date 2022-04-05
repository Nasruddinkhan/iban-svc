package com.mypractice.iban.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.mypractice.iban.service.entity.dto.ErrorDetails;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IbanException.class)
    public ResponseEntity<ErrorDetails> genericException(final IbanException ibanException) {
        return new ResponseEntity<>(ErrorDetails.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(ibanException.getMessage()).date(new Date()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException notValidException,
                                                                  final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final Map<String, String> errors = notValidException.getBindingResult().getAllErrors().stream().collect(Collectors.toMap(error -> ((FieldError) error).getField(), DefaultMessageSourceResolvable::getDefaultMessage, (a, b) -> b));
        return new ResponseEntity<>(errors, status);
    }
}
