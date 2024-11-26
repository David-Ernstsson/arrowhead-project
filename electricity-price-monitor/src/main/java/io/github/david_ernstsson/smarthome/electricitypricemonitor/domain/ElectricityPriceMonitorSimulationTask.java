package io.github.david_ernstsson.smarthome.electricitypricemonitor.domain;

import eu.arrowhead.application.skeleton.publisher.event.PresetEventType;
import eu.arrowhead.application.skeleton.publisher.service.PublisherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Component
public class ElectricityPriceMonitorSimulationTask {
    @Autowired
    private PublisherService publisherService;

    private final Logger logger = LogManager.getLogger(ElectricityPriceMonitorSimulationTask.class);

    private static final int SIMULATION_SPEED = 60; // 1 real minute = 1 simulated week
    private static final int SECONDS_IN_A_WEEK = 7 * 24 * 60 * 60;
    private boolean wasLowPrice = false;
    private final long simulationStartTime = System.currentTimeMillis();

    @Scheduled(fixedRate = 500)
    public void run() {
        long elapsedMillis = System.currentTimeMillis() - simulationStartTime;
        double elapsedSimulatedSeconds = (double) elapsedMillis / 1000 * SECONDS_IN_A_WEEK / SIMULATION_SPEED;

        // Simulated day and time
        int totalSimulatedSeconds = (int) elapsedSimulatedSeconds;
        int simulatedDayOfWeek = (totalSimulatedSeconds / (24 * 60 * 60)) % 7;
        int secondsSinceMidnight = totalSimulatedSeconds % (24 * 60 * 60);

        DayOfWeek day = DayOfWeek.of(simulatedDayOfWeek == 0 ? 7 : simulatedDayOfWeek); // Adjust for Sunday = 7
        LocalTime time = LocalTime.ofSecondOfDay(secondsSinceMidnight);

        boolean isLowPrice = getIsLowPrice(day, time);
        if (isLowPrice != wasLowPrice) {
            wasLowPrice = isLowPrice;

            if (isLowPrice) {
                logger.info("{} | {} | Low electricity price", day, time);
                publisherService.publish(PresetEventType.ELECTRICITY_PRICE_LOW, null, "changed");
            } else {
                logger.info("{} | {} | High electricity price", day, time);
                publisherService.publish(PresetEventType.ELECTRICITY_PRICE_HIGH, null, "changed");
            }
        }
    }

    private static boolean getIsLowPrice(DayOfWeek day, LocalTime time) {
        return isWithin(time, LocalTime.of(2, 0), LocalTime.of(4, 0));
    }

    private static boolean isWithin(LocalTime current, LocalTime start, LocalTime end) {
        return !current.isBefore(start) && current.isBefore(end);
    }
}