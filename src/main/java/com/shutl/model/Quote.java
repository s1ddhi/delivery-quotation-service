package com.shutl.model;

/** Quote class representing a basic quote for deliveries between 2 postcodes. */
public class Quote {
  String pickupPostcode;
  String deliveryPostcode;
  Long price;

  public Quote() {}

  public Quote(String pickupPostcode, String deliveryPostcode) {
    this.pickupPostcode = pickupPostcode;
    this.deliveryPostcode = deliveryPostcode;
  }

  public Quote(String pickupPostcode, String deliveryPostcode, Long price) {
    this.pickupPostcode = pickupPostcode;
    this.deliveryPostcode = deliveryPostcode;
    this.price = price;
  }

  /**
   * Checks that a postcode provided is valid. This means that there are only alphanumeric
   * characters without any special characters or whitespaces.
   *
   * @param postcode
   * @return whether a postcode is valid or not.
   */
  public static boolean validPostcode(String postcode) {
    return postcode.matches("^(\\w)+$");
  }

  /**
   * Checks whether there are missing parameters to create a Quote object completely.
   *
   * @return whether there are missing parameters.
   */
  public Boolean missingParameters() {
    return (this.deliveryPostcode == null || this.pickupPostcode == null);
  }

  public String getPickupPostcode() {
    return pickupPostcode;
  }

  public void setPickupPostcode(String pickupPostcode) {
    this.pickupPostcode = pickupPostcode;
  }

  public String getDeliveryPostcode() {
    return deliveryPostcode;
  }

  public void setDeliveryPostcode(String deliveryPostcode) {
    this.deliveryPostcode = deliveryPostcode;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }
}
