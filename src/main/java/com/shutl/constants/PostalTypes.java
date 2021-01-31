package com.shutl.constants;

/** Enum for the different postal types. */
public enum PostalTypes {
  PICKUP_POSTCODE("pickup postcode"),
  DELIVERY_POSTCODE("delivery postcode");

  private final String postalType;

  PostalTypes(String deliveryType) {
    this.postalType = deliveryType;
  }

  public String getPostalType() {
    return this.postalType;
  }
}
