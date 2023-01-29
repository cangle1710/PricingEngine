﻿# **Parking Pricing Engine**

------------
## The Application
This is an application that provides prices for parking spaces with APIs that allows the user to enter a date range and receives parking fees within that time range. 
Utilized Java and Spring Boot for core framework ,JUnit for testing purposes, Spring JPA for data storage, and Java Time library for implementation logic

## Configuration
Locate `default_rate.json`  under resources folder to modify the default prices

(Optional) change the port under `application.properties`

## Build 
Open this Maven project with your choice of IDE and build it. Or run this command on bash
```
mvn clean install
```

## Run
Locate `PricingEngineApplication` and run the main runner. Or run the java jar file with
```
java -jar <jar_file_name>
```

## APIs
`rates` endpoint will return the current rates stored in application when requested with `GET` path.
`rates` with `PUT` path will allow user to update the rate information with modified rate JSON in body request. This will overwrite the stored rates

Sample Json 
```json
{
    "rates": [
        {
            "days": "mon,tues,thurs",
            "times": "0900-2100",
            "tz": "America/Chicago",
            "price": 1500
        }
    ]
}
```

`price` endpoint with `GET` will allow user to request for the parking price within the requested time. Input needs to specify input date/times in ISO-8601 format with timezones
Parameters are `start` and `end` 

An example query is 
```
localhost:5000/price?start=2015-07-01T07:00:00-05:00&end=2015-07-01T12:00:00-05:00
```
above query will give response of
```
{
    "price": "1750"
}
```
- User input can span more than one day, but the API will not return a valid price  - it returns `"unavailable"`
- User input can span multiple rates, but the API will not return a valid price - it returns `"unavailable"`
- Rates will not span multiple days
- Note: a plus sign (+) in URL parameters should be replaced by `2%B`
  ``` 
  start=2015-07-04T15:00:00+00:00&end=2015-07-04T20:00:00+00:00
  ```
  should be 
  ``` 
  start=2015-07-04T15:00:00%2B00:00&end=2015-07-04T20:00:00%2B00:00
  ```

## Testing
You can utilize PostMan to send requests to the application

![Screenshot](img1.PNG)

![Screenshot](img2.PNG)
