# Delivery Quotation API + Interface

This service allows users and third parties to calculate the delivery cost from one postcode to another. Made available
are 2 API endpoints:

1. [**Quote Endpoint**](docs/QuoteEndpoint.md)
2. [**VehicleQuote Endpoint**](docs/VehicleQuoteEndpoint.md)

Documentation for each endpoint can be found in the [docs](docs) directory or via the links above.

### Error Handling

For both endpoints, invalid postcodes and missing attributes are reported. While specifically for the [
VehicleQuote](docs/VehicleQuoteEndpoint.md) endpoint, an additional check is completed to ensure only current valid
vehicle types are submitted.

This means any API requests should fail gracefully and report specific error messages as to why the request was not
successful.

More detail can be found within the specific endpoint documentation.

### REST API

This application provides, as mentioned above, 2 specific endpoints:

|Endpoint Address  |Description                                                 |
|:----------------:|:----------------------------------------------------------:|
|`/quote`          |Basic quotation service for deliveries between 2 postcodes  |
|`/quote/vehicle`  |Basic quotation service with specified vehicle types        |

### Web Interface

This application provides a user interface for the `/quote/vehicle` endpoint which can be accessed at `/getvehiclequote`
on the browser.

## Usage Information

To start the REST API and web interface described above locally, execute the following command:

```
gradle bootRun
```

## Other Useful Information

To run the tests, which can be found within the [test](src/test) directory, execute the following command:

```
gradle test
```

## Attribution

With thanks to [Bootstrap](https://getbootstrap.com/docs/4.1/layout/overview/) for the inspiration for the responsive
breakpoints used within the web UI.