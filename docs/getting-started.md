## Running the SoS
1. Start local cloud with `core systems` as well as `eventhandler` at ip `169.254.5.254`. These systems can all be found in [core systems](core systems)
2. Start publishing systems: `door-camera` and `electricity-price-monitor`
4. Register and run consuming system `lights` (replace id values accordingly from database/swagger)
    1. `https://169.254.5.254:8443/swagger-ui.html#/Management/addSystemUsingPOST`
    ```json
    {
      "address": "127.0.0.1",
      "port": 8881,
      "systemName": "lights",
      "authenticationInfo": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtCuVpXBqH4xkOZn8Fq+EP4iO09iHiErjRTBHvZCb1GLegm7V5yw0lAurKrVGJq8GwKvGo2bnhk7gZhZoJgiX8twyZVpvxKZgpJLwRK3OZQuTcAXx4uowBJewPFDWn26PC/axdEwRs+R7yidEpNqIKQytChjswcFOA19OY+r3MwtPvupYHpbEfJsQ102sVaCnaBFWjhrP1Kmt1FiBGImfKNw666DW3fK869quf37hdAdx3+SmCn520U0czNGnqZIePbNaG8YFxlHiofYY7PFQ90szNurBypqT/znVeFSij5fB+ObIuFZChOLx6Cx9PT1ESLANAaE4TF6MY10FETNo7QIDAQAB"
   }
   ```
    2. `[https://169.254.5.254:8445/swagger-ui.html#/Management](https://169.254.5.254:8445/swagger-ui.html#/Management/registerAuthorizationIntraCloudUsingPOST)`
   ```json
   {    
      "consumerId": "lights-system-id",
      "interfaceIds": [1],
      "providerIds": ["door-camera-id"],
      "serviceDefinitionIds": ["door-camera-dummy-id"]
    }
   ```
5. Register and run consuming system `radiator` (replace id values accordingly from database/swagger)
       1. `https://169.254.5.254:8443/swagger-ui.html#/Management/addSystemUsingPOST`
    ```json
    {
      "address": "127.0.0.1",
      "port": 8882,
      "systemName": "radiator",
      "authenticationInfo": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtCuVpXBqH4xkOZn8Fq+EP4iO09iHiErjRTBHvZCb1GLegm7V5yw0lAurKrVGJq8GwKvGo2bnhk7gZhZoJgiX8twyZVpvxKZgpJLwRK3OZQuTcAXx4uowBJewPFDWn26PC/axdEwRs+R7yidEpNqIKQytChjswcFOA19OY+r3MwtPvupYHpbEfJsQ102sVaCnaBFWjhrP1Kmt1FiBGImfKNw666DW3fK869quf37hdAdx3+SmCn520U0czNGnqZIePbNaG8YFxlHiofYY7PFQ90szNurBypqT/znVeFSij5fB+ObIuFZChOLx6Cx9PT1ESLANAaE4TF6MY10FETNo7QIDAQAB"
   }
   ```
    2. `https://169.254.5.254:8445/swagger-ui.html#/Management/registerAuthorizationIntraCloudUsingPOST`
   ```json
   {    
      "consumerId": "radiator-system-id",
      "interfaceIds": [1],
      "providerIds": ["door-camera-id"],
      "serviceDefinitionIds": ["door-camera-dummy-id"]
    }
   ```
6. Register and run consuming system `car-battery-charger` (replace id values accordingly from database/swagger)
       1. `https://169.254.5.254:8443/swagger-ui.html#/Management/addSystemUsingPOST`
    ```json
    {
      "address": "127.0.0.1",
      "port": 8883,
      "systemName": "carbatterycharger",
      "authenticationInfo": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtCuVpXBqH4xkOZn8Fq+EP4iO09iHiErjRTBHvZCb1GLegm7V5yw0lAurKrVGJq8GwKvGo2bnhk7gZhZoJgiX8twyZVpvxKZgpJLwRK3OZQuTcAXx4uowBJewPFDWn26PC/axdEwRs+R7yidEpNqIKQytChjswcFOA19OY+r3MwtPvupYHpbEfJsQ102sVaCnaBFWjhrP1Kmt1FiBGImfKNw666DW3fK869quf37hdAdx3+SmCn520U0czNGnqZIePbNaG8YFxlHiofYY7PFQ90szNurBypqT/znVeFSij5fB+ObIuFZChOLx6Cx9PT1ESLANAaE4TF6MY10FETNo7QIDAQAB"
   }
   ```
    2. `https://169.254.5.254:8445/swagger-ui.html#/Management/registerAuthorizationIntraCloudUsingPOST`
   ```json
   {    
      "consumerId": "car-battery-charger-system-id",
      "interfaceIds": [1],
      "providerIds": ["electricity-price-monitor-id"],
      "serviceDefinitionIds": ["electricity-price-monitor-dummy-id"]
    }
   ```
7. Verify that all systems either produce or consume events (in log)
