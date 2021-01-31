package com.shutl.controller;

import com.shutl.constants.PostalTypes;
import com.shutl.constants.QuoteConstants;
import com.shutl.constants.VehicleConstants;
import com.shutl.exceptions.InvalidPostCodeException;
import com.shutl.exceptions.InvalidVehicleTypeException;
import com.shutl.exceptions.MissingAttributesException;
import com.shutl.model.Quote;
import com.shutl.model.VehicleQuote;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Main Quote controller for the REST APIs that handle both standard `/quote` requests and
 * `/quote/vehicle` requests.
 */
@RestController
public class QuoteController {

  /**
   * Handles POST requests for `/quote` requests, ensuring that all attributes are present and
   * postcodes specified are valid before a result is provided. Otherwise, a specific error will be
   * reported to the user.
   *
   * @param quote Deserialised request attributes to the required Quote object.
   * @return A Quote object with the calculated price for the postcodes specified.
   */
  @RequestMapping(value = QuoteConstants.ENDPOINT, method = POST)
  public @ResponseBody Quote quote(@RequestBody Quote quote) {
    if (Boolean.TRUE.equals(quote.missingAttributes())) {
      throw new MissingAttributesException(QuoteConstants.REQUIRED_ATTRIBUTES);
    }

    if (!Quote.validPostcode(quote.getDeliveryPostcode())) {
      throw new InvalidPostCodeException(
          PostalTypes.DELIVERY_POSTCODE, quote.getDeliveryPostcode());
    }

    if (!Quote.validPostcode(quote.getPickupPostcode())) {
      throw new InvalidPostCodeException(PostalTypes.PICKUP_POSTCODE, quote.getPickupPostcode());
    }

    Long price =
        Math.abs(
            (Long.valueOf(quote.getDeliveryPostcode(), 36)
                    - Long.valueOf(quote.getPickupPostcode(), 36))
                / 100000000);

    return new Quote(quote.getPickupPostcode(), quote.getDeliveryPostcode(), price);
  }

  /**
   * Handles POST requests for `/quote/vehicle` requests, ensuring that all attributes are present,
   * postcodes specified are valid and types are also valid before a result is provided. Otherwise,
   * a specific error will be reported to the user.
   *
   * @param quote Deserialised request attributes to the required VehicleQuote object.
   * @return A VehicleQuote object with the calculated price for the postcodes and type specified.
   */
  @RequestMapping(value = VehicleConstants.ENDPOINT, method = POST)
  public @ResponseBody VehicleQuote vehicleQuote(@RequestBody VehicleQuote quote) {
    if (Boolean.TRUE.equals(quote.missingAttributes())) {
      throw new MissingAttributesException(VehicleConstants.REQUIRED_ATTRIBUTES);
    }

    if (!Quote.validPostcode(quote.getDeliveryPostcode())) {
      throw new InvalidPostCodeException(
          PostalTypes.DELIVERY_POSTCODE, quote.getDeliveryPostcode());
    }

    if (!Quote.validPostcode(quote.getPickupPostcode())) {
      throw new InvalidPostCodeException(PostalTypes.PICKUP_POSTCODE, quote.getPickupPostcode());
    }

    if (!VehicleQuote.validVehicleType(quote.getType())) {
      throw new InvalidVehicleTypeException(Arrays.toString(VehicleConstants.VEHICLE_TYPES));
    }

    Long price =
        Math.abs(
            (Long.valueOf(quote.getDeliveryPostcode(), 36)
                    - Long.valueOf(quote.getPickupPostcode(), 36))
                / 100000000);

    return new VehicleQuote(
        quote.getPickupPostcode(), quote.getDeliveryPostcode(), quote.getType(), price);
  }
}
