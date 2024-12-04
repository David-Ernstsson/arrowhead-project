# ElectricityPriceMonitor

<a name="electricitypricemonitor_sdd" />

## System Design Description Overview

The purpose of `electricitypricemonitor` application system is to monitor the electricity price and publish events when it's high or low.

The `electricitypricemonitor` provides features for a local cloud systems to:
* React to events of when the electricity price is high or low

<a name="electricitypricemonitor_sysd" />

## System Design Overview

<a name="electricitypricemonitor_provided_services" />

## Provided services

The `electricitypricemonitor` provides no services

<a name="electricitypricemonitor_consumed_services" />

## Consumed services

The `electricitypricemonitor` consumes the following services:
* [EventHandler](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/eventhandler) - subscribes on events
* [ElectricityPriceMonitor](/docs/electricitypricemonitor) - to receive specific events
* [ServiceRegistry](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/serviceregistry) - to find Authorization
* [Authorization](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/authorization) - when sslEnabled

<a name="electricitypricemonitor_consumed_events" />

## Consumed events

The `electricitypricemonitor` consumes the following events (no payload required)
* `ELECTRICITY_PRICE_LOW`
* `ELECTRICITY_PRICE_HIGH`
* `START_INIT`
* `START_RUN`

<a name="electricitypricemonitor_usecases" />

## Use cases

The ElectricityPriceMonitor has the following use cases:
* When receiving event `ELECTRICITY_PRICE_LOW` it charges the car battery
* When receiving event `ELECTRICITY_PRICE_HIGH` it stops charging the car battery

<a name="electricitypricemonitor_endpoints" />

## Produced events

No produced events

## Endpoints

No endpoints
