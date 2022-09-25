package com.fliurkevych.pdp.pdpspringcore.exception;

import com.fliurkevych.pdp.pdpspringcore.util.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler implements HandlerExceptionResolver {

  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
    Object handler, Exception ex) {
    try {
      if (ex instanceof NotFoundException) {
        return handleNotFoundException(ex);
      } else if (ex instanceof NotSupportedException) {
        return handleNotSupportedException(ex);
      } else if (ex instanceof ValidationException) {
        return handleValidationException(ex);
      }
    } catch (Exception handlerException) {
      log.warn("Handling of [" + ex.getClass().getName() + "] " +
        " resulted in Exception", handlerException);
    }
    return null;
  }

  private ModelAndView handleNotFoundException(Exception ex) {
    return ExceptionUtils.buildErrorModelAndView(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  private ModelAndView handleNotSupportedException(Exception ex) {
    return ExceptionUtils.buildErrorModelAndView(ex.getMessage(), HttpStatus.NOT_IMPLEMENTED);
  }

  private ModelAndView handleValidationException(Exception ex) {
    return ExceptionUtils.buildErrorModelAndView(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

}
