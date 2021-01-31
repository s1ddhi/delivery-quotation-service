# Quote Endpoint

Retrieve a delivery quote from one postcode to another.

**URL**: `/quote`

**Method**: `POST`

**Authentication**: None

**Headers**: None

**Query Body Attributes**:

|Attribute         |Description          |Required|
|:----------------:|:-------------------:|:------:|
|`deliveryPostcode`|The delivery postcode|Yes     |
|`pickupPostcode`  |The pickup postcode |Yes      |

Note: Submit the attributes, as a JSON object (see example below), as part of the request's body.

**Example Request**:

```json
{
  "deliveryPostcode": "EC2A3LT",
  "pickupPostcode": "SW1A1AA"
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
  "price": 316
}
```

### Error - Missing Attribute(s)

**Condition**: Request body did not have a required attribute.

**Code**: `400 BAD REQUEST`

**Example Invalid Response**:

Here, the `pickupPostcode` attribute that is required is missing.

```json
{
    "deliveryPostcode": "EC2A3LT"
}
```

**Example Response**:

```json
{
    "timestamp": 1612107296788,
    "status": 400,
    "error": "Bad Request",
    "exception": "com.shutl.exceptions.MissingAttributesException",
    "message": "You are missing some attributes. Please include: [deliveryPostcode, pickupPostcode]",
    "path": "/quote"
}
```

### Error - Invalid Postcode

**Condition**: A specified postcode is deemed illegal. This means there are special characters or whitespace within the
postcode string.

**Code**: `400 BAD REQUEST`

**Example Invalid Response**:

Here the `pickupPostcode` attribute has a whitespace which is not allowed.

```json
{
    "deliveryPostcode": "EC2A3LT",
    "pickupPostcode": "SW1A 1AA"
}
```

**Example Response**:

```json
{
  "timestamp": 1612107426289,
  "status": 400,
  "error": "Bad Request",
  "exception": "com.shutl.exceptions.InvalidPostCodeException",
  "message": "Please enter a valid pickup postcode with no spaces or special characters. You have entered: SW1A 1AA. A valid example is EC2A3LT.",
  "path": "/quote"
}
```

Note: As can be seen in the example response, the illegal postcode is specifically reported for user information.

## Extra Information

- Any other attributes that are specified as part of the request that do not match the required attributes declared
  above are dropped and ignored by the endpoint.