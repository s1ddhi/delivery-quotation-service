package com.shutl.constants;

/**
 * Constants pertaining to the VehicleQuote endpoint and VehicleQuote service provided by the
 * application.
 *
 * <p>Specifically, the markups are accessed via getters (and remain private) to provide a layer
 * between the actual raw markups and the code.
 */
public class VehicleConstants {
  public static final String ENDPOINT = "/quote/vehicle";

  public static final String[] VEHICLE_TYPES =
      new String[] {"bicycle", "motorbike", "parcel_car", "small_van", "large_van"};
  public static final String[] REQUIRED_PARAMETERS =
      new String[] {"type", "deliveryPostcode", "pickupPostcode"};
  private static final double BICYCLE_MARKUP = 1.1;
  private static final double MOTORBIKE_MARKUP = 1.15;
  private static final double PARCEL_CAR_MARKUP = 1.2;
  private static final double SMALL_VAN_MARKUP = 1.3;
  private static final double LARGE_VAN_MARKUP = 1.4;

  public static double getBicycleMarkup() {
    return BICYCLE_MARKUP;
  }

  public static double getLargeVanMarkup() {
    return LARGE_VAN_MARKUP;
  }

  public static double getSmallVanMarkup() {
    return SMALL_VAN_MARKUP;
  }

  public static double getParcelCarMarkup() {
    return PARCEL_CAR_MARKUP;
  }

  public static double getMotorbikeMarkup() {
    return MOTORBIKE_MARKUP;
  }
}
