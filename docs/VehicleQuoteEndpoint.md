# VehicleQuote Endpoint

Retrieve a delivery quote from one postcode to another.

**URL**: `/quote/vehicle`

**Method**: `POST`

**Authentication**: None

**Headers**: None

**Query Body Attributes**:

|Attribute         |Description          |Required|
|:----------------:|:-------------------:|:------:|
|`deliveryPostcode`|The delivery postcode|Yes     |
|`pickupPostcode`  |The pickup postcode  |Yes     |
|`type`            |The vehicle type     |Yes     |

Submit the attributes, as a JSON object (see example below), as part of the request's body.

**Example Request**:

```json
{
  "deliveryPostcode": "EC2A3LT",
  "pickupPostcode": "SW1A1AA",
  "type": "bicycle"
}
```

## Responses

### Success

**Condition**: Postcodes provided are valid, non-whitespace separated and consist of solely alphanumeric characters.

**Code**: `200 OK`

**Example Response**:

This is the response following the example request shown above.

```json
{
  "pickupPostcode": "SW1A1AA",
  "deliveryPostcode": "EC2A3LT",
  "price": 348,
  "type": "bicycle"
}
```

### Error - Missing Attribute(s)

**Condition**: Request body did not have a required attribute.

**Code**: `400 BAD REQUEST`

**Example Invalid Response**:

```json
{
  "deliveryPostcode": "EC2A3LT",
  "pickupPostcode": "SW1A1AA"
}
```

**Example Response**:

```json
{
  "timestamp": 1612107909286,
  "status": 400,
  "error": "Bad Request",
  "exception": "com.shutl.exceptions.MissingAttributesException",
  "message": "You are missing some attributes. Please include: [type, deliveryPostcode, pickupPostcode]",
  "path": "/quote/vehicle"
}
```

### Error - Invalid Postcode

**Condition**: A specified postcode is deemed illegal. This means there are special characters or whitespace within the
postcode string.

**Code**: `400 BAD REQUEST`

**Example Invalid Response**:

```json
{
  "deliveryPostcode": "EC2A3LT",
  "pickupPostcode": "SW1A1AA",
  "type": "bicycle"
}
```

**Example Response**:

```json
{
  "timestamp": 1612108489128,
  "status": 400,
  "error": "Bad Request",
  "exception": "com.shutl.exceptions.InvalidVehicleTypeException",
  "message": "Please enter a valid vehicle. Current permitted vehicle types are: [bicycle, motorbike, parcel_car, small_van, large_van]",
  "path": "/quote/vehicle"
}
```

As can be seen in the example response, the illegal postcode is specifically reported for user information.

### Error - Invalid Type

**Condition**: The specified vehicle type does not exist. Those allowed can be found declared by `VEHICLE_TYPES` within
the [VehicleConstants](../src/main/java/com/shutl/constants/VehicleConstants.java) file.

**Code**: `400 BAD REQUEST`

**Example Invalid Response**:

```json
{
  "deliveryPostcode": "EC2A3LT",
  "pickupPostcode": "SW1A 1AA",
  "type": "boat"
}
```

**Example Response**:

```json
{
  "timestamp": 1612108211409,
  "status": 400,
  "error": "Bad Request",
  "exception": "com.shutl.exceptions.InvalidVehicleTypeException",
  "message": "Please enter a valid vehicle. Current permitted vehicle types are: [bicycle, motorbike, parcel_car, small_van, large_van]",
  "path": "/quote/vehicle"
}
```

As can be seen in the example response, the valid vehicle types, as declared by the `VEHICLE_TYPES` variable, is
reported in the message.

## Extra Information

- Any other attributes that are specified as part of the request that do not match the required attributes declared
  above are dropped and ignored by the endpoint.