package io.github.david_ernstsson.smarthome.radiator;

import ai.aitia.arrowhead.application.library.ArrowheadService;
import ai.aitia.arrowhead.application.library.util.ApplicationCommonConstants;
import eu.arrowhead.common.CommonConstants;
import eu.arrowhead.common.SSLProperties;
import eu.arrowhead.common.Utilities;
import eu.arrowhead.common.dto.shared.*;
import eu.arrowhead.common.exception.InvalidParameterException;
import io.github.david_ernstsson.smarthome.radiator.controller.RadiatorController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RadiatorTask extends Thread {
	private final Logger logger = LogManager.getLogger(RadiatorTask.class);

	@Autowired
	private ArrowheadService arrowheadService;

	@Autowired
	private RadiatorController radiatorController;

	@Autowired
	protected SSLProperties sslProperties;

	@Value(ApplicationCommonConstants.$TOKEN_SECURITY_FILTER_ENABLED_WD)
	private boolean tokenSecurityFilterEnabled;

	@Value(CommonConstants.$SERVER_SSL_ENABLED_WD)
	private boolean sslEnabled;

	@Override
	public void run() {
		// This is the Thread's run method
		logger.info("--- RadiatorTask here");

		var stateRequestingService = orchestrateGetStateService();
		callGetStateRequestingService(stateRequestingService);
	}

	private OrchestrationResultDTO orchestrateGetStateService() {
		logger.info("Orchestration request for " + DoorCameraConstants.GET_STATE_SERVICE_DEFINITION + " service:");
		final ServiceQueryFormDTO serviceQueryForm = new ServiceQueryFormDTO.Builder(DoorCameraConstants.GET_STATE_SERVICE_DEFINITION)
				.interfaces(getInterface())
				.build();

		final OrchestrationFormRequestDTO.Builder orchestrationFormBuilder = arrowheadService.getOrchestrationFormBuilder();
		final OrchestrationFormRequestDTO orchestrationFormRequest = orchestrationFormBuilder.requestedService(serviceQueryForm)
				.flag(OrchestrationFlags.Flag.MATCHMAKING, false)
				.flag(OrchestrationFlags.Flag.OVERRIDE_STORE, true)
				.flag(OrchestrationFlags.Flag.PING_PROVIDERS, true)
				.build();

		printOut(orchestrationFormRequest);

		final OrchestrationResponseDTO orchestrationResponse = arrowheadService.proceedOrchestration(orchestrationFormRequest);

		logger.info("Orchestration response:");
		printOut(orchestrationResponse);

		if (orchestrationResponse == null) {
			logger.info("No orchestration response received");
		} else if (orchestrationResponse.getResponse().isEmpty()) {
			logger.info("No provider found during the orchestration");
		} else {
			final OrchestrationResultDTO orchestrationResult = orchestrationResponse.getResponse().get(0);
			validateOrchestrationResult(orchestrationResult, DoorCameraConstants.GET_STATE_SERVICE_DEFINITION);

			return orchestrationResult;
		}

		return null;
	}

	private void callGetStateRequestingService( final OrchestrationResultDTO orchestrationResult) {
		validateOrchestrationResult(orchestrationResult, DoorCameraConstants.GET_STATE_SERVICE_DEFINITION);

		logger.info("get homeownerstate:");
		final String token = orchestrationResult.getAuthorizationTokens() == null ? null : orchestrationResult.getAuthorizationTokens().get(getInterface());
		@SuppressWarnings("unchecked")
		final DoorCameraStateResponseDto stateDto = arrowheadService.consumeServiceHTTP(DoorCameraStateResponseDto.class, HttpMethod.valueOf(orchestrationResult.getMetadata().get(DoorCameraConstants.HTTP_METHOD)),
				orchestrationResult.getProvider().getAddress(), orchestrationResult.getProvider().getPort(), orchestrationResult.getServiceUri(),
				getInterface(), token, null, new String[0]);

		printOut(stateDto);
		radiatorController.changeRadiatorState(stateDto.getHomeownerIsHome());
	}

	private void validateOrchestrationResult(final OrchestrationResultDTO orchestrationResult, final String serviceDefinition) {
		if (!orchestrationResult.getService().getServiceDefinition().equalsIgnoreCase(serviceDefinition)) {
			throw new InvalidParameterException("Requested and orchestrated service definition do not match");
		}

		boolean hasValidInterface = false;
		for (final ServiceInterfaceResponseDTO serviceInterface : orchestrationResult.getInterfaces()) {
			if (serviceInterface.getInterfaceName().equalsIgnoreCase(getInterface())) {
				hasValidInterface = true;
				break;
			}
		}
		if (!hasValidInterface) {
			throw new InvalidParameterException("Requested and orchestrated interface do not match");
		}
	}

	private String getInterface() {
		return sslProperties.isSslEnabled() ? DoorCameraConstants.INTERFACE_SECURE : DoorCameraConstants.INTERFACE_INSECURE;
	}

	private void printOut(final Object object) {
		System.out.println(Utilities.toPrettyJson(Utilities.toJson(object)));
	}
}