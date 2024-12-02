# System of System documentation

| System                  | Type      | Service                         | Event Name                | Action                                    |
|-------------------------|-----------|----------------------------------|---------------------------|-------------------------------------------|
| **car-battery-charger** | Consumer  | `car-battery-charger-service`   | `ELECTRICITY_PRICE_LOW`   | Charge battery                            |
|                         |           |                                  | `ELECTRICITY_PRICE_HIGH`  | Stop charging action                                 |
| **door-camera**         | Provider  | `door-camera-dummy`             | `HOMEOWNER_LEFT`          | Send event                                |
|                         |           |                                  | `HOMEOWNER_CAME_HOME`     | Send event                                |
| **lights**              | Consumer  | `LightsController`              | `HOMEOWNER_LEFT`          | Turn off lights                           |
|                         |           |                                  | `HOMEOWNER_CAME_HOME`     | Turn on lights                            |
| **electricity-price-monitor** | Provider | `electricity-price-monitor-dummy` | `ELECTRICITY_PRICE_LOW`   | Send event                                |
|                         |           |                                  | `ELECTRICITY_PRICE_HIGH`  | Send event                                |
| **radiator**            | Consumer  | `RadiatorController`            | `HOMEOWNER_LEFT`          | Turn off radiator                         |
|                         |           |                                  | `HOMEOWNER_CAME_HOME`     | Turn on radiator                          |

