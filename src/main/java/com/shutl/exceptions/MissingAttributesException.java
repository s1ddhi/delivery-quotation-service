package com.shutl.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

/** Runtime Exception that is reported when attribute(s) within the request body is missing. */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MissingAttributesException extends RuntimeException {
  private static final String ERROR = "You are missing some attributes. Please include: ";

  private MissingAttributesException(String message) {
    super(message);
  }

  public MissingAttributesException(String[] requiredAttributes) {
    this(getMessage(requiredAttributes));
  }

  private static String getMessage(String[] requiredAttributes) {
    return ERROR + Arrays.toString(requiredAttributes);
  }
}
