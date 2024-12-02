# System of System documentation

## Graph
```mermaid
graph TB
    subgraph "Arrowhead Local Cloud"
        direction TB
        %% Core Systems
        authorization[Authorization]
        service-registry[Service Registry]
        orchestration[Orchestration]
        event-handler[Event Handler]

        %% Other Systems
        electricity-price-monitor[electricity-price-monitor]
        door-camera[door-camera]
        car-battery-charger[car-battery-charger]
        lights[lights]
        radiator[radiator]
    end

    %% Event Routing to Event Handler
    electricity-price-monitor -->|ELECTRICITY_PRICE_LOW| event-handler
    electricity-price-monitor -->|ELECTRICITY_PRICE_HIGH| event-handler
    door-camera -->|HOMEOWNER_LEFT| event-handler
    door-camera -->|HOMEOWNER_CAME_HOME| event-handler

    %% Event Routing from Event Handler
    event-handler -->|ELECTRICITY_PRICE_LOW| car-battery-charger
    event-handler -->|ELECTRICITY_PRICE_HIGH| car-battery-charger
    event-handler -->|HOMEOWNER_LEFT| lights
    event-handler -->|HOMEOWNER_CAME_HOME| lights
    event-handler -->|HOMEOWNER_LEFT| radiator
    event-handler -->|HOMEOWNER_CAME_HOME| radiator

    %% Style for Nodes
    style event-handler fill:#f4d35e,stroke:#000,stroke-width:2px
    style service-registry fill:#4a90e2,stroke:#000,stroke-width:2px
    style authorization fill:#ff6f61,stroke:#000,stroke-width:2px
    style orchestration fill:#76c893,stroke:#000,stroke-width:2px

    style electricity-price-monitor fill:#d3d3d3,stroke:#000,stroke-width:2px
    style door-camera fill:#d3d3d3,stroke:#000,stroke-width:2px
    style car-battery-charger fill:#d3d3d3,stroke:#000,stroke-width:2px
    style lights fill:#d3d3d3,stroke:#000,stroke-width:2px
    style radiator fill:#d3d3d3,stroke:#000,stroke-width:2px
```

## Table
| System                  | Type      | Service                         | Event Name                | Action                                    |
|-------------------------|-----------|----------------------------------|---------------------------|-------------------------------------------|
| **car-battery-charger** | Consumer  | `car-battery-charger-dummy`   | `ELECTRICITY_PRICE_LOW`   | Charge battery                            |
|                         |           |                                  | `ELECTRICITY_PRICE_HIGH`  | Stop charging action                                 |
| **door-camera**         | Provider  | `door-camera-dummy`             | `HOMEOWNER_LEFT`          | Sent when homeowner leaves                                |
|                         |           |                                  | `HOMEOWNER_CAME_HOME`     | Sent when homeowner comes home                                |
| **lights**              | Consumer  | `LightsController`              | `HOMEOWNER_LEFT`          | Turn off lights                           |
|                         |           |                                  | `HOMEOWNER_CAME_HOME`     | Turn on lights                            |
| **electricity-price-monitor** | Provider | `electricity-price-monitor-dummy` | `ELECTRICITY_PRICE_LOW`   | Sent when price is low                                |
|                         |           |                                  | `ELECTRICITY_PRICE_HIGH`  | Send when price is high                                |
| **radiator**            | Consumer  | `RadiatorController`            | `HOMEOWNER_LEFT`          | Turn off radiator                         |
|                         |           |                                  | `HOMEOWNER_CAME_HOME`     | Turn on radiator                          |
