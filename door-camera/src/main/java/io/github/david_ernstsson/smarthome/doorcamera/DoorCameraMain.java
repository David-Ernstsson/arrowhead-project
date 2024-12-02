package io.github.david_ernstsson.smarthome.doorcamera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import eu.arrowhead.common.CommonConstants;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {CommonConstants.BASE_PACKAGE, "ai.aitia", "io.github.david_ernstsson.smarthome.doorcamera"})
@EnableScheduling
public class DoorCameraMain {

	public static void main(final String[] args) {
		SpringApplication.run(DoorCameraMain.class, args);
	}
}
