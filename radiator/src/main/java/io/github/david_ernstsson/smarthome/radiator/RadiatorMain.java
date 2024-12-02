package io.github.david_ernstsson.smarthome.radiator;

import eu.arrowhead.application.skeleton.subscriber.ConfigEventProperites;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import eu.arrowhead.common.CommonConstants;

@SpringBootApplication
@EnableConfigurationProperties(ConfigEventProperites.class)
@ComponentScan(basePackages = {CommonConstants.BASE_PACKAGE, "ai.aitia", "io.github.david_ernstsson.smarthome.radiator"}) //TODO: add custom packages if any
public class RadiatorMain {
	public static void main(final String[] args) {
		SpringApplication.run(RadiatorMain.class, args);
		
	}
}
