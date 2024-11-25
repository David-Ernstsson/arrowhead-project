package io.github.david_ernstsson.smarthome.doorcamera.domain;

import eu.arrowhead.application.skeleton.publisher.event.EventTypeConstants;
import eu.arrowhead.application.skeleton.publisher.event.PresetEventType;
import eu.arrowhead.application.skeleton.publisher.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ScheduledTask {
    @Autowired
    private PublisherService publisherService;


    @Scheduled(fixedRate = 3000) // Executes every 10 seconds (10000 ms)
    public void runEvery10Seconds() {
        System.out.println("This runs every 10 seconds: " + System.currentTimeMillis());

        publisherService.publish(PresetEventType.HOMEOWNER_LEFT, null, "yes he/she did");

        // Add your custom logic here
    }
}