package io.github.david_ernstsson.smarthome.skeleton_subscriber.controller;

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
public class SubscriberController {
	
	//=================================================================================================
	// members

	private final Logger logger = LogManager.getLogger(SubscriberController.class);
	private static boolean lightsTurnedOn =false;
	
	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	@GetMapping(path = CommonConstants.ECHO_URI)
	public String echoService() {
		return "Got it!";
	}

	//-------------------------------------------------------------------------------------------------
	@PostMapping(path = SubscriberConstants.HOMEOWNER_LEFT_NOTIFICATION_URI)
	public void receiveEventHomeownerLeft(@RequestBody final EventDTO event) {
		logger.debug("receiveEventRequestHomeOwner started...");

		if (event.getEventType() != null) {
			changeLightsState(false);
		}
	}

	@PostMapping(path = SubscriberConstants.HOMEOWNER_CAME_HOME_NOTIFICATION_URI)
	public void receiveEventHomeownerCameHome(@RequestBody final EventDTO event) {
		logger.debug("receiveEventRequestHomeOwner started...");

		if (event.getEventType() != null) {
			changeLightsState(true);
		}
	}

	private void changeLightsState(boolean isTurnedOn) {
		lightsTurnedOn = isTurnedOn;
		if(isTurnedOn) {
			logger.info("Lights turned on");
		}
		else {
			logger.info("Lights turned off");
		}
	}

	
	//-------------------------------------------------------------------------------------------------
	@PostMapping(path = SubscriberConstants.START_INIT_NOTIFICATION_URI) 
	public void receivePublisherStartedInitEvent(@RequestBody final EventDTO event) {
		logger.debug("receivePublsisherStartedInitEvent started... ");
		
		if (event.getEventType() == null) {			
			logger.debug("EventType is null.");
		}
		
		//TODO implement your event handling logic here 
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