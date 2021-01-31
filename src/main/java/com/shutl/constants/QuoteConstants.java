package com.shutl.constants;

/** Constants pertaining to the Quote endpoint and Quote service provided by the application. */
public class QuoteConstants {
  public static final String ENDPOINT = "/quote";

  public static final String[] REQUIRED_ATTRIBUTES =
      new String[] {"deliveryPostcode", "pickupPostcode"};
}
