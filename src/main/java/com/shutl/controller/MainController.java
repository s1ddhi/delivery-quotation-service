package com.shutl.controller;

import com.shutl.model.VehicleQuote;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 * This controls the user interface portion of the application.
 *
 * <p>Implicitly, the declared `error.html` page is mapped when dealing with any requests that error
 * out.
 */
@Controller
public class MainController {
  private static final String VEHICLE_QUOTE_ENDPOINT = "http://localhost:8080/quote/vehicle";

  /**
   * Maps requests to the `/getvehiclequote` path, that are GETs, to the form built.
   *
   * @param model
   * @return The form page that for the user to interact with.
   */
  @RequestMapping(value = "/getvehiclequote", method = RequestMethod.GET)
  public String vehicleQuoteForm(Model model) {
    model.addAttribute("vehicleQuote", new VehicleQuote());
    return "vehicleQuoteForm";
  }

  /**
   * Maps requests to the `/getvehiclequote` path, that are POSTs, to the form built following a
   * submission by the user.
   *
   * @param model
   * @return The result page, with the populated information received from the `/quote/vehicle`
   *     endpoint.
   */
  @RequestMapping(value = "/getvehiclequote", method = RequestMethod.POST)
  public String vehicleQuoteRetrieval(@ModelAttribute VehicleQuote vehicleQuote, Model model) {
    RestTemplate restTemplate = new RestTemplate();
    VehicleQuote finalQuote =
        restTemplate.postForObject(VEHICLE_QUOTE_ENDPOINT, vehicleQuote, VehicleQuote.class);
    model.addAttribute("vehicleQuote", finalQuote);
    return "VehicleQuoteResponse";
  }
}
