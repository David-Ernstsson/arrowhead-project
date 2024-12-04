# Lights

<a name="lights_sdd" />

## System Design Description Overview

The purpose of `lights` application system is to turn the lights on and off in a home.

The `lights` provides features for a local cloud systems to :
* Turn the lights on and off in a home when the homeowner leaves or comes home accordingly

<a name="lights_sysd" />

## System Design Overview

<a name="lights_provided_services" />

## Provided services

The `lights` provides no services

<a name="lights_consumed_services" />

## Consumed services

The `lights` consumes the following services:
* [EventHandler](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/eventhandler) - subscribes on events
* [DoorCamera](/docs/doorcamera) - to receive specific events
* [ServiceRegistry](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/serviceregistry) - to find `Authorization`
* [Authorization](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/authorization) - when sslEnabled

<a name="lights_usecases" />

## Use cases

The Lights has the following use cases:
* When receiving event `HOMEOWNER_LEFT` it turns off the lights
* When receiving event `HOMEOWNER_CAME_HOME` it turns on the lights

<a name="lights_consumed_events" />

## Consumed events

The `lights` consumes the following events (no payload required)
* `HOMEOWNER_LEFT`
* `HOMEOWNER_CAME_HOME`
* `START_INIT`
* `START_RUN`

<a name="lights_produced_events" />

## Produced events

No produced events

<a name="lights_endpoints" />

## Endpoints

No endpoints
