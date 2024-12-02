package io.github.david_ernstsson.smarthome.lights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import eu.arrowhead.common.CommonConstants;

@SpringBootApplication
@ComponentScan(basePackages = {CommonConstants.BASE_PACKAGE, "ai.aitia", "io.github.david_ernstsson.smarthome.lights"}) //TODO: add custom packages if any
public class LightsMain {

	public static void main(final String[] args) {
		SpringApplication.run(LightsMain.class, args);
	}
}
