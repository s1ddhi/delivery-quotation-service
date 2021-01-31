package com.shutl.model;

import com.shutl.constants.VehicleConstants;

import java.util.Arrays;

/**
 * VehicleQuote class representing a basic quote for deliveries between 2 postcodes with a specified
 * vehicle type.
 */
public class VehicleQuote extends Quote {
  String type;

  public VehicleQuote() {}

  public VehicleQuote(String pickupPostcode, String deliveryPostcode, String type) {
    super(pickupPostcode, deliveryPostcode);
    this.type = type.toLowerCase();
  }

  private VehicleQuote(String pickupPostcode, String deliveryPostcode, Long price) {
    super(pickupPostcode, deliveryPostcode, price);
  }

  public VehicleQuote(String pickupPostcode, String deliveryPostcode, String type, Long price) {
    this(pickupPostcode, deliveryPostcode, markedUpPrice(price, type));
    this.type = type.toLowerCase();
  }

  /**
   * Checks whether the provided vehicle type is permitted.
   *
   * @param type to check.
   * @return whether the type to check is permitted or not.
   */
  public static boolean validVehicleType(String type) {
    return Arrays.asList(VehicleConstants.VEHICLE_TYPES).contains(type.toLowerCase());
  }

  /**
   * Depending on the vehicle type specified, will calculate the final cost of the delivery.
   *
   * <p>Note: For invalid vehicle types, the price returned will be the original inputted. This
   * method provides no error handling for invalid types.
   *
   * @param price of the base delivery between 2 postcodes.
   * @param type of vehicle.
   * @return the marked up price.
   */
  private static Long markedUpPrice(Long price, String type) {
    switch (type.toLowerCase()) {
      case "bicycle":
        return Math.round(price * VehicleConstants.getBicycleMarkup());
      case "motorbike":
        return Math.round(price * VehicleConstants.getMotorbikeMarkup());
      case "parcel_car":
        return Math.round(price * VehicleConstants.getParcelCarMarkup());
      case "small_van":
        return Math.round(price * VehicleConstants.getSmallVanMarkup());
      case "large_van":
        return Math.round(price * VehicleConstants.getLargeVanMarkup());
      default:
        return price;
    }
  }

  /**
   * Checks whether there are missing parameters to create a Quote object completely.
   *
   * @return whether there are missing parameters.
   */
  @Override
  public Boolean missingParameters() {
    return (this.type == null || super.missingParameters());
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type.toLowerCase();
  }
}
