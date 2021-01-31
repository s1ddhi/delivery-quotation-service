# Take home exercise

**Do NOT fork this repo! Please read the instructions carefully and follow the instructions for submitting your work
below.**

For our tech test, we'd like you to take a stripped-down version of our quoting engine, and then add some features. This
is a RESTful service endpoint that takes a few details and works out the price for a delivery.

Throughout the test we're looking for great coding style, driving your code through tests (and refactoring) and at all
times doing the bare minimum possible to get the job done. If you don't like the code or tests that are there already,
feel free to refactor as you add features.

Please ensure that the features you complete are done to a standard that you're happy with, taking into account the time
guideline below. Please complete the features in order.

Read this document thoroughly before starting your work. You are welcome to contact us if you have any question.

Please ensure that you include a readme file with any commands/thoughts/assumptions or anything else you would like us
to know about your solution.

Good luck! :)

### Time guideline:

We recommend spending no more than 3 hours completing this exercise.

### Submitting your work:

To submit, please clone this repo and then push it onto your own GitHub account. **Do not fork this repo!** Then make
the changes as you see fit. When you completed the test, please email the link to YOUR repo/pull request to
DL-eBay-Shipping-London-Intern-Hiring@ebay.com. There is no deadline for submission but keep in mind that we will review
the PRs in the order they come in.

## Completed Feature

### Basic Service

Build a basic service that responds to a POST to /quotes, with the following request structure:

```
{
  "pickup_postcode":   "SW1A1AA",
  "delivery_postcode": "EC2A3LT"
}
```

And responds with the following price:

```
{
  "pickup_postcode":   "SW1A1AA",
  "delivery_postcode": "EC2A3LT",
  "price":             316
}
```

The price we charge depends on the distance between two postcodes. We are not implementing postcode geocoding here, so
instead we are using basic formula for working out the price for a quote between two postcodes. The process is to take
the base-36 integer of each postcode, subtract the delivery postcode from the pickup postcode and then divide by some
large number. If the result is negative, turn it into a positive.

Hint: in java, this would be:

`Long.valueOf("SW1A1AA", 36) - Long.valueOf("EC2A3LT", 36)`

If you have a better idea for a deterministic way of making a number from two postcodes, please feel free to use that
instead. Update your service to calculate pricing based upon these rules.

## Features to complete

### 1) Simple variable prices by vehicle

Our price changes based upon the vehicle. Implement a "vehicle" attribute on the request, that takes one of the
following values, applying the appropriate markup:

* bicycle: 10%
* motorbike: 15%
* parcel_car: 20%
* small_van: 30%
* large_van: 40%

For example, if the base price was 100, the `small_van` price with markup will be 130. The vehicle should also be
returned in the response, and the price should be rounded to the nearest integer.

Request:

```
{
  "pickup_postcode":   "SW1A1AA",
  "delivery_postcode": "EC2A3LT",
  "vehicle": "bicycle"
}
```

Response:

```
{
  "pickup_postcode":   "SW1A1AA",
  "delivery_postcode": "EC2A3LT"
  "vehicle": "bicycle"
  "price": 348
}
```

### 2) Build an interface for your app!

Build a webpage that makes the above call.

It should contain a form with the following fields:
`pickup_postcode`, `delivery_postcode` and `vehicle`.

Under the form, based on the response, list the price in the following format:
`A delivery from <pickup_postcode> to <delivery_postcode> using a <vehicle> will cost you £<price>.`
Substitute the variables in the <> with the appropriate values.

While the page is waiting for the response, an appropriate message should be displayed.

**Bonus**:

- Make sure that the page displays well both on smaller and larger screens, ie that is `responsive`.
- The action linked to the submit button could retrieve the data from the service without refreshing the page.

# Dependencies

`gradle`: make sure is correctly installed on your machine. `brew` can help you with the installation if you are using a
Mac Machine. For Windows users you can follow the installation steps on the gradle website: https://gradle.org/install/

## Useful commands

Run tests from command line:

```
gradle test
```

Run server locally:

```
gradle bootRun
```

Make quote request:

```
echo '{"pickupPostcode": "SW1A1AA", "deliveryPostcode": "EC2A3LT" }' | \
curl -d @- http://localhost:8080/quote --header "Content-Type:application/json"
```

## Troubleshooting

Some version configurations cause Gradle to not be able to find the main class. To fix this, add the following to the
end of the build.gradle file

```
springBoot {
    mainClass = "com.shutl.Application"
}
```

The build has been tested for Gradle 5.4.1 and Java 8
