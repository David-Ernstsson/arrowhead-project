# Smart energy-efficient home
## Purpose & project description
The purpose of this project is to demonstrate a smart energy-efficient home using the Arrowhead Framework in a local cloud. The system of systems (SoS) aims to both minimize energy usage and costs as well as making life more convenience for the homeowner. The PoC demonstrates this by automating home appliances and services based on predefined events such as when the owner leaves or enters the home. The SoS uses event-driven architecture to control heating, lighting and electric car charging based on the homeowner's presence and electricity prices.

## Documentation
- [Getting started](docs/getting-started.md)
- [System overview](docs/system-overview.md)

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
