package io.github.david_ernstsson.smarthome.carbatterycharger.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.arrowhead.application.skeleton.subscriber.constants.SubscriberConstants;
import eu.arrowhead.application.skeleton.subscriber.constants.SubscriberDefaults;
import eu.arrowhead.common.CommonConstants;
import eu.arrowhead.common.dto.shared.EventDTO;

@RestController
@RequestMapping(SubscriberDefaults.DEFAULT_EVENT_NOTIFICATION_BASE_URI)
public class CarBatteryChargerController {
	
	//=================================================================================================
	// members

	private final Logger logger = LogManager.getLogger(CarBatteryChargerController.class);
	private static boolean batteryIsCharging =false;

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	@GetMapping(path = CommonConstants.ECHO_URI)
	public String echoService() {
		return "Got it!";
	}
	
	//-------------------------------------------------------------------------------------------------
	@PostMapping(path = SubscriberConstants.ELECTRICITY_PRICE_LOW)
	public void receiveEventElectricityPriceLow(@RequestBody final EventDTO event) {
		if (event.getEventType() != null) {
			changeChargingState(false);
		}
	}

	@PostMapping(path = SubscriberConstants.ELECTRICITY_PRICE_HIGH)
	public void receiveEventElectricityPriceHigh(@RequestBody final EventDTO event) {
		if (event.getEventType() != null) {
			changeChargingState(true);
		}
	}

	private void changeChargingState(boolean charge) {
		batteryIsCharging = charge;
		if(charge) {
			logger.info("Car battery charging");
		}
		else {
			logger.info("Car battery stopped charging");
		}
	}

	//-------------------------------------------------------------------------------------------------
	@PostMapping(path = SubscriberConstants.START_INIT_NOTIFICATION_URI) 
	public void receivePublisherStartedInitEvent(@RequestBody final EventDTO event) {
		logger.debug("receivePublsisherStartedInitEvent started... ");
		
		if (event.getEventType() == null) {
			logger.debug("EventType is null.");
		}
	}
	
	//-------------------------------------------------------------------------------------------------
	@PostMapping(path = SubscriberConstants.START_RUN_NOTIFICATION_URI) 
	public void receivePublisherStartedRunEvent(@RequestBody final EventDTO event ) {
		logger.debug("receivePublsisherStartedRunEvent started... ");
		
		if (event.getEventType() == null) {			
			logger.debug("EventType is null.");
		}
		
		//TODO implement your event handling logic here 
	}
	
	//-------------------------------------------------------------------------------------------------
	//TODO: implement here additional subscriber related REST end points
}