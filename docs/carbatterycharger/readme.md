# CarBatteryCharger

<a name="carbatterycharger_sdd" />

## System Design Description Overview

The purpose of TimeManager supporting core system is to provide time and location based services.

The TimeManager provides features for a local cloud systems to :
* Fetch accurate and trusted time and location information,


<a name="carbatterycharger_sysd" />

## System Design Overview

<a name="carbatterycharger_provided_services" />

## Provided services

The TimeManager provides the following services:
* [Echo](#carbatterycharger_endpoints_get_echo)
* [Time](#carbatterycharger_endpoints_time)

<a name="carbatterycharger_consumed_services" />

## Consumed services

The CarBatteryCharger consumes the following services:

None currently, but will consume Orchestration later on.

<a name="carbatterycharger_usecases" />

## Use cases

The CarBatteryCharger has the following use cases:
* [Fetch trusted time](documentation/carbatterycharger/use_cases/TM_use_case_1.md)

<a name="carbatterycharger_endpoints" />

## Endpoints

Swagger API documentation is available on: `https://<host>:<port>` <br />
The base URL for the requests: `http://<host>:<port>/carbatterycharger`

<a name="carbatterycharger_endpoints_client" />

### Client endpoint description<br />

| Function | URL subpath | Method | Input | Output |
| -------- | ----------- | ------ | ----- | ------ |
| [Echo](#carbatterycharger_endpoints_get_echo) | /echo | GET    | -    | OK     |
| [Time](#carbatterycharger_endpoints_get_time) | /time | GET    | -    | TimeResponse  |

<a name="carbatterycharger_endpoints_get_echo" />

### Echo
```
GET /carbatterycharger/echo
```

Returns a "Got it!" message with the purpose of testing the system availability.

<a name="carbatterycharger_endpoints_get_time" />

### Get trusted time and location
```
GET /carbatterycharger/time
```

Returns time stamps (UNIX in seconds and millseconds), time zone ("Europe/Budapest"), Daylist savings active (true/false) and if the time is trusted (true/false).

<a name="carbatterycharger_gettime_response" />

__TimeResponse__ output:

```json

{
  "epoch": 1627844812,
  "epochMs": 1627844812102,
  "tz": "string",
  "dst": true,
  "trusted": true
}

```
