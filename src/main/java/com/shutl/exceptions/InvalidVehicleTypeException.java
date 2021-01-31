package com.shutl.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Runtime Exception that is reported when a vehicle type specified as part of the request is
 * invalid.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidVehicleTypeException extends RuntimeException {
  private static final String INVALID_VEHICLE_TYPE_MESSAGE =
      "Please enter a valid vehicle. Current permitted vehicle types are: ";

  public InvalidVehicleTypeException(String vehicleTypes) {
    super(INVALID_VEHICLE_TYPE_MESSAGE + vehicleTypes);
  }
}
