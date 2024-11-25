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


    @Scheduled(fixedRate = 10000) // Executes every 10 seconds (10000 ms)
    public void runEvery10Seconds() {
        System.out.println("This runs every 10 seconds: " + System.currentTimeMillis());

        publisherService.publish(PresetEventType.DOMAIN_EVENT, Map.of(EventTypeConstants.EVENT_TYPE_DOMAIN_EVENT, HttpMethod.GET.name()), "HOMEOWNER_LEFT");

        // Add your custom logic here
    }
}