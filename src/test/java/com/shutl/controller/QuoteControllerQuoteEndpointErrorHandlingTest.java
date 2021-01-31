package com.shutl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shutl.Application;
import com.shutl.constants.PostalTypes;
import com.shutl.constants.QuoteConstants;
import com.shutl.exceptions.InvalidPostCodeException;
import com.shutl.exceptions.MissingAttributesException;
import com.shutl.model.Quote;
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
public class QuoteControllerQuoteEndpointErrorHandlingTest {

  ObjectMapper objectMapper = new ObjectMapper();
  String uniqueQuoteMissingParametersMessageArtifact =
      Arrays.toString(QuoteConstants.REQUIRED_ATTRIBUTES);
  @Autowired private WebApplicationContext webApplicationContext;
  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  public void testQuoteMissingAllParameters() throws Exception {
    Quote quoteData = new Quote();
    MvcResult result =
        this.mockMvc
            .perform(
                post(QuoteConstants.ENDPOINT)
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
            .contains(uniqueQuoteMissingParametersMessageArtifact));
  }

  @Test
  public void testQuoteMissingPickupPostcodeParameter() throws Exception {
    Quote quoteData = new Quote();
    quoteData.setDeliveryPostcode("SW1A1AA");

    MvcResult result =
        this.mockMvc
            .perform(
                post(QuoteConstants.ENDPOINT)
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
            .contains(uniqueQuoteMissingParametersMessageArtifact));
  }

  @Test
  public void testQuoteMissingDeliveryPostcodeParameter() throws Exception {
    Quote quoteData = new Quote();
    quoteData.setPickupPostcode("EC2A3LT");

    MvcResult result =
        this.mockMvc
            .perform(
                post(QuoteConstants.ENDPOINT)
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
            .contains(uniqueQuoteMissingParametersMessageArtifact));
  }

  @Test
  public void testQuoteInvalidPickupPostcodeParameterWithSpecialCharacters() throws Exception {
    Quote quoteData = new Quote();
    quoteData.setDeliveryPostcode("SW1A1AA");
    quoteData.setPickupPostcode(".EC2%A$3LT");

    MvcResult result =
        this.mockMvc
            .perform(
                post(QuoteConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isBadRequest())
            .andExpect(
                error ->
                    assertTrue(error.getResolvedException() instanceof InvalidPostCodeException))
            .andReturn();

    assertTrue(
        result
            .getResolvedException()
            .getMessage()
            .contains(PostalTypes.PICKUP_POSTCODE.getPostalType()));
  }

  @Test
  public void testQuoteInvalidPickupPostcodeParameterWithWhiteSpace() throws Exception {
    Quote quoteData = new Quote();
    quoteData.setDeliveryPostcode("SW1A1AA");
    quoteData.setPickupPostcode("EC2A 3LT");

    MvcResult result =
        this.mockMvc
            .perform(
                post(QuoteConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isBadRequest())
            .andExpect(
                error ->
                    assertTrue(error.getResolvedException() instanceof InvalidPostCodeException))
            .andReturn();

    assertTrue(
        result
            .getResolvedException()
            .getMessage()
            .contains(PostalTypes.PICKUP_POSTCODE.getPostalType()));
  }

  @Test
  public void testQuoteInvalidDeliveryPostcodeParameterWithSpecialCharacters() throws Exception {
    Quote quoteData = new Quote();
    quoteData.setDeliveryPostcode(".SW1%A$1AA");
    quoteData.setPickupPostcode("EC2A3LT");

    MvcResult result =
        this.mockMvc
            .perform(
                post(QuoteConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isBadRequest())
            .andExpect(
                error ->
                    assertTrue(error.getResolvedException() instanceof InvalidPostCodeException))
            .andReturn();

    assertTrue(
        result
            .getResolvedException()
            .getMessage()
            .contains(PostalTypes.DELIVERY_POSTCODE.getPostalType()));
  }

  @Test
  public void testQuoteInvalidDeliveryPostcodeParameterWithWhiteSpace() throws Exception {
    Quote quoteData = new Quote();
    quoteData.setDeliveryPostcode("SW1A 1AA");
    quoteData.setPickupPostcode("EC2A3LT");

    MvcResult result =
        this.mockMvc
            .perform(
                post(QuoteConstants.ENDPOINT)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(quoteData)))
            .andExpect(status().isBadRequest())
            .andExpect(
                error ->
                    assertTrue(error.getResolvedException() instanceof InvalidPostCodeException))
            .andReturn();

    assertTrue(
        result
            .getResolvedException()
            .getMessage()
            .contains(PostalTypes.DELIVERY_POSTCODE.getPostalType()));
  }
}
