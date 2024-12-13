# DoorCamera

<a name="doormonitor_sdd" />

## System Design Description Overview

The purpose of `doorcamera` application system is to monitor when the homeowner leaves or comes home and publish events accordingly

The `doorcamera` provides features for a local cloud systems to:
* React to events when the homeowner leaves or comes home

<a name="doorcamera_sysd" />

## System Design Overview

<a name="doorcamera_provided_services" />

## Provided services

* `door-camera-dummy` - Needed for intra cloud authorization by systems that wants to receive events from service

<a name="doorcamera_consumed_services" />

## Consumed services

The `doorcamera` consumes the following services:
* [EventHandler](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/eventhandler) - publishes events
* [ServiceRegistry](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/serviceregistry) - to find `Authorization`
* [Authorization](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/authorization) - when sslEnabled

<a name="doorcamera_usecases" />

## Use cases

The `doorcamera` has the following use cases:
* When homeowner leaves publishes event `HOMEOWNER_LEFT`
* When homeowner comes home publishes event `HOMEOWNER_CAME_HOME`

<a name="doorcamera_produced_events" />

## Produced events

* `HOMEOWNER_LEFT`
* `HOMEOWNER_CAME_HOME`

<a name="doorcamera_consumed_events" />

## Consumed events

No consumed events

<a name="doorcamera_endpoints" />

## Endpoints

The doorcamera controller offers endpoint for getting current state

The base URL for the requests: `https://<host>:<port>`

### Client endpoint description<br />
<a name="onboardingcontroller_endpoints" />

| Function | URL subpath | Method | Input | Output |
| -------- | ----------- | ------ | ----- | ------ |
| [get-state](#endoints_state) | /state   | GET   | [Request](#endpoints_state_request) | [Response](#endpoints_state_response) |


<a name="endoints_state" />

### get-state
path: `/state`

<a name="endpoints_state_request" />

#### Request
Empty

<a name="endpoints_state_response" />

#### Response
`doorCameraStateResponseDto`

```json
{
    "homeownerIsHome": "boolean"
}
```

## Security
See [SoSD - Security](https://github.com/David-Ernstsson/arrowhead-project/tree/main/docs#security)

