package com.fliurkevych.pdp.pdpspringcore.exception;

import com.fliurkevych.pdp.pdpspringcore.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<Object> handleBaseException(final Exception e,
    final WebRequest request) {
    log.error(e.getMessage(), e);

    var error = ErrorResponse.builder()
      .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .text(e.getMessage())
      .build();
    return handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
      request);
  }

  @ExceptionHandler(value = {NotFoundException.class})
  protected ResponseEntity<Object> handleNotFoundException(final NotFoundException e,
    final WebRequest request) {
    log.error(e.getMessage(), e);

    var error = ErrorResponse.builder()
      .code(HttpStatus.NOT_FOUND.value())
      .text(e.getMessage())
      .build();
    return handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.NOT_FOUND,
      request);
  }

  @ExceptionHandler(value = {NotSupportedException.class})
  protected ResponseEntity<Object> handleNotSupportedException(final NotFoundException e,
    final WebRequest request) {
    log.error(e.getMessage(), e);

    var error = ErrorResponse.builder()
      .code(HttpStatus.NOT_IMPLEMENTED.value())
      .text(e.getMessage())
      .build();
    return handleExceptionInternal(e, error, new HttpHeaders(),
      HttpStatus.NOT_IMPLEMENTED, request);
  }

  @ExceptionHandler(value = {ValidationException.class})
  protected ResponseEntity<Object> handleValidationException(final NotFoundException e,
    final WebRequest request) {
    log.error(e.getMessage(), e);

    var error = ErrorResponse.builder()
      .code(HttpStatus.BAD_REQUEST.value())
      .text(e.getMessage())
      .build();
    return handleExceptionInternal(e, error, new HttpHeaders(),
      HttpStatus.BAD_REQUEST, request);
  }

}
