# CarBatteryCharger

<a name="carbatterycharger_sdd" />

## System Design Description Overview

The purpose of `carbatterycharger` application system is to charge the battery of an electric car.

The `carbatterycharger` provides features for a local cloud systems to :
* Charge the battery when the electricity price is low

<a name="carbatterycharger_sysd" />

## System Design Overview

<a name="carbatterycharger_provided_services" />

## Provided services

The `carbatterycharger` provides no services

<a name="carbatterycharger_consumed_services" />

## Consumed services

The `carbatterycharger` consumes the following services:
* [EventHandler](https://github.com/arrowhead-f/core-java-spring/blob/aitia-docs/eventhandler/docs/ReadMe.md) - subscribes on events
* [ElectricityPriceMonitor](/docs/electricitypricemonitor) - to receive specific events
* ServiceRegistry and Orchestrator and Authorization (when sslEnabled)

The `carbatterycharger` consumes the following events (no payload required)
* ELECTRICITY_PRICE_LOW
* ELECTRICITY_PRICE_HIGH
* START_INIT
* START_RUN

<a name="carbatterycharger_usecases" />

## Use cases

The CarBatteryCharger has the following use cases:
* When receiving event ELECTRICITY_PRICE_LOW it charges the car battery
* When receiving event ELECTRICITY_PRICE_HIGH it stops charging the car battery

<a name="carbatterycharger_endpoints" />

## Endpoints

No endpoints
