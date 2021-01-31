package com.shutl.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

/** Runtime Exception that is reported when parameter(s) within the request body is missing. */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MissingParametersException extends RuntimeException {
  private static final String ERROR = "You are missing some parameters. Please include: ";

  private MissingParametersException(String message) {
    super(message);
  }

  public MissingParametersException(String[] requiredParameters) {
    this(getMessage(requiredParameters));
  }

  private static String getMessage(String[] requiredParameters) {
    return ERROR + Arrays.toString(requiredParameters);
  }
}
