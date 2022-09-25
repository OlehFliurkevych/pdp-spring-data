package com.fliurkevych.pdp.pdpspringcore.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Oleh Fliurkevych
 */
public final class ExceptionUtils {

  public static ModelAndView buildErrorModelAndView(String message, HttpStatus httpStatus) {
    var modelAndView = new ModelAndView();
    modelAndView.setStatus(httpStatus);
    modelAndView.addObject("message", message);
    return modelAndView;
  }

}
