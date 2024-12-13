# Radiator

<a name="radiator_sdd" />

## System Design Description Overview

The purpose of `radiator` application system is to turn the radiator on and off in a home.

The `radiator` provides features for a local cloud systems to :
* Turn the radiator on and off in a home when the homeowner leaves or comes home accordingly

<a name="radiator_sysd" />

## System Design Overview

<a name="radiator_provided_services" />

## Provided services

The `radiator` provides no services

<a name="radiator_consumed_services" />

## Consumed services

The `radiator` consumes the following services:
* [EventHandler](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/eventhandler) - subscribes on events
* [DoorCamera](/docs/doorcamera) - to receive specific events
* [ServiceRegistry](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/serviceregistry) - to find `Authorization`
* [Authorization](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/authorization) - when sslEnabled

<a name="radiator_usecases" />

## Use cases

The Radiator has the following use cases:
* When receiving event `HOMEOWNER_LEFT` it turns off the radiator
* When receiving event `HOMEOWNER_CAME_HOME` it turns on the radiator

<a name="radiator_consumed_events" />

## Consumed events

The `radiator` consumes the following events (no payload required)
* `HOMEOWNER_LEFT`
* `HOMEOWNER_CAME_HOME`
* `START_INIT`
* `START_RUN`

<a name="radiator_produced_events" />

## Produced events

No produced events

<a name="radiator_endpoints" />

## Endpoints

No endpoints

## Security
See [SoSD - Security](https://github.com/David-Ernstsson/arrowhead-project/tree/main/docs#security)

