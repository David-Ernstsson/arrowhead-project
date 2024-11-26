package io.github.david_ernstsson.smarthome.skeleton_subscriber;

import eu.arrowhead.application.skeleton.subscriber.ConfigEventProperites;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import ai.aitia.arrowhead.application.library.ArrowheadService;
import ai.aitia.arrowhead.application.library.util.ApplicationCommonConstants;
import eu.arrowhead.application.skeleton.subscriber.constants.SubscriberConstants;
import eu.arrowhead.common.CommonConstants;

@SpringBootApplication
@EnableConfigurationProperties(ConfigEventProperites.class)
@ComponentScan(basePackages = {CommonConstants.BASE_PACKAGE, "ai.aitia", "io.github.david_ernstsson.smarthome.skeleton_subscriber"}) //TODO: add custom packages if any
public class SubscriberMain {

    //=================================================================================================
	// members
	
	@Value( SubscriberConstants.$PRESET_EVENT_TYPES_WD )
	private String presetEvents;
	
	@Value(ApplicationCommonConstants.$APPLICATION_SYSTEM_NAME)
	private String applicationSystemName;
	
	@Value(ApplicationCommonConstants.$APPLICATION_SERVER_ADDRESS_WD)
	private String applicationSystemAddress;
	
	@Value(ApplicationCommonConstants.$APPLICATION_SERVER_PORT_WD)
	private int applicationSystemPort;
	
	@Value(CommonConstants.$SERVER_SSL_ENABLED_WD)
	private boolean sslEnabled;
	
	@Autowired
	private ArrowheadService arrowheadService;
	
	@Autowired
	private ConfigEventProperites configEventProperites;
	
	private final Logger logger = LogManager.getLogger(SubscriberApplicationInitListener.class);
	
	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public static void main(final String[] args) {
		SpringApplication.run(SubscriberMain.class, args);
		
	}
}
