package io.github.david_ernstsson.smarthome.electricitypricemonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import eu.arrowhead.common.CommonConstants;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {CommonConstants.BASE_PACKAGE, "ai.aitia", "io.github.david_ernstsson.smarthome.electricitypricemonitor"})
@EnableScheduling
public class ElectricityPriceMonitorCameraMain {

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public static void main(final String[] args) {
		SpringApplication.run(ElectricityPriceMonitorCameraMain.class, args);
	}
}
