package com.shutl.exceptions;

import com.shutl.constants.PostalTypes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Runtime Exception that is reported when a postcode specified as part of the request is invalid.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidPostCodeException extends RuntimeException {
  private static final String INVALID_POSTCODE_MESSAGE = "Please enter a valid";
  private static final String DISALLOWED_CHARACTERS = "with no spaces or special characters";
  private static final String VALID_EXAMPLE = "A valid example is EC2A3LT";
  private static final String USER_ENTERED = "You have entered:";
  private static final String END = ". ";
  private static final String PERIOD = ".";
  private static final String SPACE = " ";

  private InvalidPostCodeException(String message) {
    super(message);
  }

  public InvalidPostCodeException(PostalTypes type, String postcode) {
    this(constructMessage(type.getPostalType(), postcode));
  }

  private static String constructMessage(String type, String postcode) {
    return INVALID_POSTCODE_MESSAGE
        + SPACE
        + type
        + SPACE
        + DISALLOWED_CHARACTERS
        + END
        + USER_ENTERED
        + SPACE
        + postcode
        + END
        + VALID_EXAMPLE
        + PERIOD;
  }
}
