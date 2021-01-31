package com.shutl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shutl.Application;
import com.shutl.constants.VehicleConstants;
import com.shutl.model.Quote;
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

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Tests to verify the functionality of the QuoteController specifically for the `/quote/vehicle`
 * endpoint.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class VehicleQuoteControllerFunctionalTest {

  ObjectMapper objectMapper = new ObjectMapper();
  @Autowired private WebApplicationContext webApplicationContext;
  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  public void testBasicVehicleQuoteServiceBicycle() throws Exception {
    Quote quoteData = new VehicleQuote("SW1A1AA", "EC2A3LT", "bicycle");
    MvcResult result =
        this.mockMvc
            .perform(
                post(VehicleConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isOk())
            .andReturn();

    VehicleQuote quote =
        objectMapper.readValue(result.getResponse().getContentAsString(), VehicleQuote.class);
    assertEquals(quote.getPickupPostcode(), "SW1A1AA");
    assertEquals(quote.getDeliveryPostcode(), "EC2A3LT");
    assertEquals(quote.getType(), "bicycle");
    assertEquals(quote.getPrice(), new Long(348));
  }

  @Test
  public void testBasicVehicleQuoteServiceMotorbike() throws Exception {
    Quote quoteData = new VehicleQuote("SW1A1AA", "EC2A3LT", "motorbike");
    MvcResult result =
        this.mockMvc
            .perform(
                post(VehicleConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isOk())
            .andReturn();

    VehicleQuote quote =
        objectMapper.readValue(result.getResponse().getContentAsString(), VehicleQuote.class);
    assertEquals(quote.getPickupPostcode(), "SW1A1AA");
    assertEquals(quote.getDeliveryPostcode(), "EC2A3LT");
    assertEquals(quote.getType(), "motorbike");
    assertEquals(quote.getPrice(), new Long(363));
  }

  @Test
  public void testBasicVehicleQuoteServiceParcelCar() throws Exception {
    Quote quoteData = new VehicleQuote("SW1A1AA", "EC2A3LT", "parcel_car");
    MvcResult result =
        this.mockMvc
            .perform(
                post(VehicleConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isOk())
            .andReturn();

    VehicleQuote quote =
        objectMapper.readValue(result.getResponse().getContentAsString(), VehicleQuote.class);
    assertEquals(quote.getPickupPostcode(), "SW1A1AA");
    assertEquals(quote.getDeliveryPostcode(), "EC2A3LT");
    assertEquals(quote.getType(), "parcel_car");
    assertEquals(quote.getPrice(), new Long(379));
  }

  @Test
  public void testBasicVehicleQuoteServiceSmallVan() throws Exception {
    Quote quoteData = new VehicleQuote("SW1A1AA", "EC2A3LT", "small_van");
    MvcResult result =
        this.mockMvc
            .perform(
                post(VehicleConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isOk())
            .andReturn();

    VehicleQuote quote =
        objectMapper.readValue(result.getResponse().getContentAsString(), VehicleQuote.class);
    assertEquals(quote.getPickupPostcode(), "SW1A1AA");
    assertEquals(quote.getDeliveryPostcode(), "EC2A3LT");
    assertEquals(quote.getType(), "small_van");
    assertEquals(quote.getPrice(), new Long(411));
  }

  @Test
  public void testBasicVehicleQuoteServiceLargeVan() throws Exception {
    Quote quoteData = new VehicleQuote("SW1A1AA", "EC2A3LT", "large_van");
    MvcResult result =
        this.mockMvc
            .perform(
                post(VehicleConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isOk())
            .andReturn();

    VehicleQuote quote =
        objectMapper.readValue(result.getResponse().getContentAsString(), VehicleQuote.class);
    assertEquals(quote.getPickupPostcode(), "SW1A1AA");
    assertEquals(quote.getDeliveryPostcode(), "EC2A3LT");
    assertEquals(quote.getType(), "large_van");
    assertEquals(quote.getPrice(), new Long(442));
  }

  @Test
  public void testCapitalisationOfType() throws Exception {
    Quote quoteData = new VehicleQuote("SW1A1AA", "EC2A3LT", "BiCYcLE");
    MvcResult result =
        this.mockMvc
            .perform(
                post(VehicleConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isOk())
            .andReturn();

    VehicleQuote quote =
        objectMapper.readValue(result.getResponse().getContentAsString(), VehicleQuote.class);
    assertEquals(quote.getPickupPostcode(), "SW1A1AA");
    assertEquals(quote.getDeliveryPostcode(), "EC2A3LT");
    assertEquals(quote.getType(), "bicycle");
    assertEquals(quote.getPrice(), new Long(348));
  }
}
