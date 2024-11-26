# Smart energy-efficient home

## To start
1. Start local cloud with core systems as well as eventhandler at ip `169.254.5.254`
2. Start publishing systems: door-camera
3. Register consuming systems: lights
    1. `https://169.254.5.254:8443/swagger-ui.html#/Management/addSystemUsingPOST`
    ```json
    {
      "address": "127.0.0.1",
      "port": 8889,
      "systemName": "lights"
   }
   ```
    2. `https://169.254.5.254:8445/swagger-ui.html#/Management`
   ```json
   {    
      "consumerId": ["lights-system-id"],
      "interfaceIds": [1],
      "providerIds": ["door-camera-id"],
      "serviceDefinitionIds": ["door-camera-dummy-id"]
    }
   ```

## Purpose & project description
The purpose of this project is to demonstrate a smart energy-efficient home using the Arrowhead Framework in a local cloud. The system of systems (SoS) aims to both minimize energy usage and costs as well as making life more convenience for the homeowner. The PoC demonstrates this by automating home appliances and services based on predefined events such as when the owner leaves or enters the home. The SoS uses event-driven architecture to control heating, lighting and electric car charging based on the homeowner's presence and electricity prices.

## System and Services overview

| **System**                  | **Service Type** | **Service Description**                                                                                              |
|-----------------------------|------------------|----------------------------------------------------------------------------------------------------------------------|
| **Door camera**             | Provider         | Publishes an event when the owner enters the home.                                                                   |
|                             | Provider         | Publishes an event when the owner leaves the home.                                                                   |
| **Radiator**                | Consumer         | Consumes the "owner enters" event to turn on heating.                                                                |
|                             | Consumer         | Consumes the "owner leaves" event to turn down/off heating.                                                          |
| **Lights**                  | Consumer         | Consumes the "owner enters" event to turn on lights.                                                                |
|                             | Consumer         | Consumes the "owner leaves" event to turn off lights.                                                                 |
| **Electricity price monitor**| Provider         | Publishes an event when electricity price is low. |
| **Car battery charger**     | Consumer         | Consumes the "electricity price is low" event to charge the electric car until full.                                 |

## Implementation notes
- **Event-based architecture:** The system heavily relies on an event-based architecture and the Arrowhead Framework eventhandler to synchronize operations between devices and services.
- **Energy optimization:** By reacting to low electricity prices, the system reduces energy costs while ensuring essential operations like heating and car charging are performed efficiently.
- **Homeowner convenience:** By leveraging automation rather than manual work the homeowner knows he/she saves costs without having to think or do anything about it.
- **Publish of events:** Occurs randomly or somewhat randomly with a timer.

## Future enhancements
- More energy consuming systems such as dishwasher, laundry machine etc.
- Electricity price monitor to pull spot price data from remote source
- More heating adjustments such as turning down heating and turning off lights when owner goes to bed.
- More convenience such as turning on radiators when owner leaves work rather than when entering home.
- Showing homeowner how much energy that has been saved automatically.
