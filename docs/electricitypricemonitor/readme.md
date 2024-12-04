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

* `electricity-price-monitor-dummy` - Needed for intra cloud authorization by systems that wants to receive events from service

<a name="electricitypricemonitor_consumed_services" />

## Consumed services

The `electricitypricemonitor` consumes the following services:
* [EventHandler](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/eventhandler) - publishes events
* [ServiceRegistry](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/serviceregistry) - to find Authorization
* [Authorization](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/authorization) - when sslEnabled

<a name="electricitypricemonitor_consumed_events" />

## Use cases

The `electricitypricemonitor` has the following use cases:
* When electricity price is low publishes event `ELECTRICITY_PRICE_LOW`
* When electricity price is high publishes event `ELECTRICITY_PRICE_HIGH`

<a name="electricitypricemonitor_endpoints" />

## Produced events

* `ELECTRICITY_PRICE_LOW`
* `ELECTRICITY_PRICE_HIGH`

## Consumed events

No consumed events

<a name="electricitypricemonitor_usecases" />

## Endpoints

No endpoints
