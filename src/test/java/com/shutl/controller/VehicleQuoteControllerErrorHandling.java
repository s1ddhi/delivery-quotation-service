package com.shutl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shutl.Application;
import com.shutl.constants.VehicleConstants;
import com.shutl.exceptions.InvalidVehicleTypeException;
import com.shutl.exceptions.MissingAttributesException;
import com.shutl.model.VehicleQuote;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Tests to verify expected error handling behaviour of the QuoteController specifically for the
 * `/quote` endpoint.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class VehicleQuoteControllerErrorHandling {

  private final String uniqueVehicleQuoteMissingAttributesMessageArtifact =
      Arrays.toString(VehicleConstants.REQUIRED_ATTRIBUTES);
  private final String uniqueVehicleQuoteInvalidTypeArtifact =
      Arrays.toString(VehicleConstants.VEHICLE_TYPES);
  ObjectMapper objectMapper = new ObjectMapper();
  @Autowired private WebApplicationContext webApplicationContext;
  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  public void testVehicleQuoteMissingAllAttributes() throws Exception {
    VehicleQuote quoteData = new VehicleQuote();
    MvcResult result =
        this.mockMvc
            .perform(
                post(VehicleConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isBadRequest())
            .andExpect(
                error ->
                    assertTrue(error.getResolvedException() instanceof MissingAttributesException))
            .andReturn();

    assertTrue(
        result
            .getResolvedException()
            .getMessage()
            .contains(uniqueVehicleQuoteMissingAttributesMessageArtifact));
  }

  @Test
  public void testVehicleQuoteMissingTypeAttribute() throws Exception {
    VehicleQuote quoteData = new VehicleQuote();
    quoteData.setDeliveryPostcode("EC2A3LT");
    quoteData.setPickupPostcode("SW1A1AA");
    MvcResult result =
        this.mockMvc
            .perform(
                post(VehicleConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isBadRequest())
            .andExpect(
                error ->
                    assertTrue(error.getResolvedException() instanceof MissingAttributesException))
            .andReturn();

    assertTrue(
        result
            .getResolvedException()
            .getMessage()
            .contains(uniqueVehicleQuoteMissingAttributesMessageArtifact));
  }

  @Test
  public void testVehicleQuoteMissingDeliveryPostcodeAttribute() throws Exception {
    VehicleQuote quoteData = new VehicleQuote();
    quoteData.setPickupPostcode("SW1A1AA");
    quoteData.changeType("bicycle");
    MvcResult result =
        this.mockMvc
            .perform(
                post(VehicleConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isBadRequest())
            .andExpect(
                error ->
                    assertTrue(error.getResolvedException() instanceof MissingAttributesException))
            .andReturn();

    assertTrue(
        result
            .getResolvedException()
            .getMessage()
            .contains(uniqueVehicleQuoteMissingAttributesMessageArtifact));
  }

  @Test
  public void testVehicleQuoteMissingPickupPostcodeAttribute() throws Exception {
    VehicleQuote quoteData = new VehicleQuote();
    quoteData.setDeliveryPostcode("EC2A3LT");
    quoteData.changeType("bicycle");
    MvcResult result =
        this.mockMvc
            .perform(
                post(VehicleConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isBadRequest())
            .andExpect(
                error ->
                    assertTrue(error.getResolvedException() instanceof MissingAttributesException))
            .andReturn();

    assertTrue(
        result
            .getResolvedException()
            .getMessage()
            .contains(uniqueVehicleQuoteMissingAttributesMessageArtifact));
  }

  @Test
  public void testVehicleQuoteMissingDeliveryPostcodePickupPostcodeAttributes() throws Exception {
    VehicleQuote quoteData = new VehicleQuote();
    quoteData.changeType("bicycle");
    MvcResult result =
        this.mockMvc
            .perform(
                post(VehicleConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isBadRequest())
            .andExpect(
                error ->
                    assertTrue(error.getResolvedException() instanceof MissingAttributesException))
            .andReturn();

    assertTrue(
        result
            .getResolvedException()
            .getMessage()
            .contains(uniqueVehicleQuoteMissingAttributesMessageArtifact));
  }

  @Test
  public void testVehicleQuoteMissingDeliveryPostcodeTypeAttributes() throws Exception {
    VehicleQuote quoteData = new VehicleQuote();
    quoteData.setPickupPostcode("SW1A1AA");
    MvcResult result =
        this.mockMvc
            .perform(
                post(VehicleConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isBadRequest())
            .andExpect(
                error ->
                    assertTrue(error.getResolvedException() instanceof MissingAttributesException))
            .andReturn();

    assertTrue(
        result
            .getResolvedException()
            .getMessage()
            .contains(uniqueVehicleQuoteMissingAttributesMessageArtifact));
  }

  @Test
  public void testVehicleQuoteMissingPickupPostcodeTypeAttributes() throws Exception {
    VehicleQuote quoteData = new VehicleQuote();
    quoteData.setDeliveryPostcode("EC2A3LT");
    MvcResult result =
        this.mockMvc
            .perform(
                post(VehicleConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isBadRequest())
            .andExpect(
                error ->
                    assertTrue(error.getResolvedException() instanceof MissingAttributesException))
            .andReturn();

    assertTrue(
        result
            .getResolvedException()
            .getMessage()
            .contains(uniqueVehicleQuoteMissingAttributesMessageArtifact));
  }

  /**
   * To test requests with invalid types, the setType(String type) method is used which does no
   * verification against valid vehicle types as deserialising utilises the setter used to initially
   * set the value. Thus, to replicate the instance where an external request with a JSON object
   * with the invalid type is made, the check is bypassed.
   *
   * @throws Exception when an invalid request is made, in this case, an
   *     InvalidVehicleTypeException.
   */
  @Test
  public void testInvalidTypeAttribute() throws Exception {
    VehicleQuote quoteData = new VehicleQuote();
    quoteData.setPickupPostcode("SW1A1AA");
    quoteData.setDeliveryPostcode("EC2A3LT");
    quoteData.setType("boat");

    MvcResult result =
        this.mockMvc
            .perform(
                post(VehicleConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isBadRequest())
            .andExpect(
                error ->
                    assertTrue(error.getResolvedException() instanceof InvalidVehicleTypeException))
            .andReturn();

    assertTrue(
        result.getResolvedException().getMessage().contains(uniqueVehicleQuoteInvalidTypeArtifact));
  }
}
