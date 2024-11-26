package io.github.david_ernstsson.smarthome.doorcamera;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import eu.arrowhead.common.dto.shared.ServiceRegistryRequestDTO;
import eu.arrowhead.common.dto.shared.ServiceSecurityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import ai.aitia.arrowhead.application.library.ArrowheadService;
import ai.aitia.arrowhead.application.library.config.ApplicationInitListener;
import ai.aitia.arrowhead.application.library.util.ApplicationCommonConstants;
import eu.arrowhead.application.skeleton.publisher.constants.PublisherConstants;
import eu.arrowhead.application.skeleton.publisher.event.PresetEventType;
import eu.arrowhead.application.skeleton.publisher.security.PublisherSecurityConfig;
import eu.arrowhead.common.CommonConstants;
import eu.arrowhead.common.Utilities;
import eu.arrowhead.common.core.CoreSystem;
import eu.arrowhead.common.dto.shared.EventPublishRequestDTO;
import eu.arrowhead.common.dto.shared.SystemRequestDTO;
import eu.arrowhead.common.exception.ArrowheadException;

@Component
public class DoorCameraApplicationInitListener extends ApplicationInitListener {
	
	//=================================================================================================
	// members
	
	@Autowired
	private ArrowheadService arrowheadService;
	
	@Autowired
	private PublisherSecurityConfig publisherSecurityConfig;
	
	@Value(ApplicationCommonConstants.$TOKEN_SECURITY_FILTER_ENABLED_WD)
	private boolean tokenSecurityFilterEnabled;
	
	@Value(CommonConstants.$SERVER_SSL_ENABLED_WD)
	private boolean sslEnabled;
	
	@Value(ApplicationCommonConstants.$APPLICATION_SYSTEM_NAME)
	private String mySystemName;
	
	@Value(ApplicationCommonConstants.$APPLICATION_SERVER_ADDRESS_WD)
	private String mySystemAddress;
	
	@Value(ApplicationCommonConstants.$APPLICATION_SERVER_PORT_WD)
	private int mySystemPort;
	
	private final Logger logger = LogManager.getLogger(DoorCameraApplicationInitListener.class);
	
	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	@Override
	protected void customInit(final ContextRefreshedEvent event) {
		checkConfiguration();
		
		//Checking the availability of necessary core systems
		checkCoreSystemReachability(CoreSystem.SERVICEREGISTRY);
		
		if (sslEnabled && tokenSecurityFilterEnabled) {
			checkCoreSystemReachability(CoreSystem.AUTHORIZATION);			

			//Initialize Arrowhead Context
			arrowheadService.updateCoreServiceURIs(CoreSystem.AUTHORIZATION);			
		
			setTokenSecurityFilter();
		
		}else {
			logger.info("TokenSecurityFilter in not active");
		}

		//TODO: Register system by first registering service and then unregister into ServiceRegistry. One seems to first having to needs to create a system to be able to publish event, but how else to do this using arrowheadService?
		//TODO: Dummy service is needed for system to be created. Its also needed for the consumer to get (some kind of service-registry permission dependency for events magic check?) to get events
		final ServiceRegistryRequestDTO serviceRegistryRequest = createDummyServiceRegistryRequest();
		arrowheadService.forceRegisterServiceToServiceRegistry(serviceRegistryRequest);
		//arrowheadService.unregisterServiceFromServiceRegistry(dummy, dummy);

		if (arrowheadService.echoCoreSystem(CoreSystem.EVENTHANDLER)) {
			arrowheadService.updateCoreServiceURIs(CoreSystem.EVENTHANDLER);	
			
			publishInitStartedEvent();
		}
		
		//TODO: implement here any custom behavior on application start up
	}

	private ServiceRegistryRequestDTO createDummyServiceRegistryRequest() {
		final ServiceRegistryRequestDTO serviceRegistryRequest = new ServiceRegistryRequestDTO();
		serviceRegistryRequest.setServiceDefinition("dummy");
		final SystemRequestDTO systemRequest = new SystemRequestDTO();
		systemRequest.setSystemName(mySystemName);
		systemRequest.setAddress(mySystemAddress);
		systemRequest.setPort(mySystemPort);

		if (sslEnabled && tokenSecurityFilterEnabled) {
			systemRequest.setAuthenticationInfo(Base64.getEncoder().encodeToString(arrowheadService.getMyPublicKey().getEncoded()));
			serviceRegistryRequest.setSecure(ServiceSecurityType.TOKEN.name());
			serviceRegistryRequest.setInterfaces(List.of(DoorCameraConstants.INTERFACE_SECURE));
		} else if (sslEnabled) {
			systemRequest.setAuthenticationInfo(Base64.getEncoder().encodeToString(arrowheadService.getMyPublicKey().getEncoded()));
			serviceRegistryRequest.setSecure(ServiceSecurityType.CERTIFICATE.name());
			serviceRegistryRequest.setInterfaces(List.of(DoorCameraConstants.INTERFACE_SECURE));
		} else {
			serviceRegistryRequest.setSecure(ServiceSecurityType.NOT_SECURE.name());
			serviceRegistryRequest.setInterfaces(List.of(DoorCameraConstants.INTERFACE_INSECURE));
		}
		serviceRegistryRequest.setProviderSystem(systemRequest);
		serviceRegistryRequest.setServiceUri("/dummy");
		return serviceRegistryRequest;
	}

	//-------------------------------------------------------------------------------------------------
	@Override
	public void customDestroy() {
		//TODO: implement here any custom behavior on application shout down
	}
	
	//=================================================================================================
	// assistant methods
	
	//-------------------------------------------------------------------------------------------------
	private void checkConfiguration() {
		if (!sslEnabled && tokenSecurityFilterEnabled) {			 
			logger.warn("Contradictory configuration:");
			logger.warn("token.security.filter.enabled=true while server.ssl.enabled=false");
		}
	}

	//-------------------------------------------------------------------------------------------------
	private void setTokenSecurityFilter() {

		final PublicKey authorizationPublicKey = arrowheadService.queryAuthorizationPublicKey();
		if (authorizationPublicKey == null) {
			throw new ArrowheadException("Authorization public key is null");
		}
		
		KeyStore keystore;
		try {
			keystore = KeyStore.getInstance(sslProperties.getKeyStoreType());
			keystore.load(sslProperties.getKeyStore().getInputStream(), sslProperties.getKeyStorePassword().toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException ex) {
			throw new ArrowheadException(ex.getMessage());
		}			
		final PrivateKey publisherPrivateKey = Utilities.getPrivateKey(keystore, sslProperties.getKeyPassword());
		
		publisherSecurityConfig.getTokenSecurityFilter().setAuthorizationPublicKey(authorizationPublicKey);
		publisherSecurityConfig.getTokenSecurityFilter().setMyPrivateKey(publisherPrivateKey);
	}

	//-------------------------------------------------------------------------------------------------	
	//Sample implementation of event publishing at application init time
	private void publishInitStartedEvent() {
		logger.debug( "publishInitStartedEvent started..." );
		
		final String eventType = PresetEventType.START_INIT.getEventTypeName();
		
		final SystemRequestDTO source = new SystemRequestDTO();
		source.setSystemName(mySystemName);
		source.setAddress(mySystemAddress);
		source.setPort(mySystemPort);
		if (sslEnabled) {	
			source.setAuthenticationInfo( Base64.getEncoder().encodeToString(arrowheadService.getMyPublicKey().getEncoded()));
		}

		final Map<String,String> metadata = null;		
		final String payload = PublisherConstants.START_INIT_EVENT_PAYLOAD;		
		final String timeStamp = Utilities.convertZonedDateTimeToUTCString(ZonedDateTime.now());		
		final EventPublishRequestDTO publishRequestDTO = new EventPublishRequestDTO(eventType, source, metadata, payload, timeStamp);
		
		arrowheadService.publishToEventHandler(publishRequestDTO);				
	}
}
