package io.github.david_ernstsson.smarthome.doorcamera.controller;

import java.util.Map;

import io.github.david_ernstsson.smarthome.doorcamera.DoorCameraConstants;
import io.github.david_ernstsson.smarthome.doorcamera.DoorCameraStateResponseDto;
import io.github.david_ernstsson.smarthome.doorcamera.domain.HomeOwnerSimulationTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.arrowhead.application.skeleton.publisher.event.EventTypeConstants;
import eu.arrowhead.application.skeleton.publisher.event.PresetEventType;
import eu.arrowhead.application.skeleton.publisher.service.PublisherService;
import eu.arrowhead.common.CommonConstants;

@RestController
public class DoorCameraController {
	
	//=================================================================================================
	// members

	private final Logger logger = LogManager.getLogger(DoorCameraController.class);
	
	@Autowired
	private PublisherService publisherService;

	@Autowired
	private HomeOwnerSimulationTask homeOwnerSimulationTask;

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	@GetMapping(path = CommonConstants.ECHO_URI)
	public String echoService() {
		logger.debug("echoService started...");
		
		publisherService.publish(PresetEventType.REQUEST_RECEIVED, Map.of(EventTypeConstants.EVENT_TYPE_REQUEST_RECEIVED_METADATA_REQUEST_TYPE, HttpMethod.GET.name()), CommonConstants.ECHO_URI);
		
		return "Got it!";
	}

	@GetMapping(path = DoorCameraConstants.GET_STATE_URI)
	public DoorCameraStateResponseDto getStateService() {
		logger.debug("get-state started...");

		return new DoorCameraStateResponseDto(homeOwnerSimulationTask.isAtHome);
	}


	//-------------------------------------------------------------------------------------------------
	//TODO: implement here your provider related REST end points
}