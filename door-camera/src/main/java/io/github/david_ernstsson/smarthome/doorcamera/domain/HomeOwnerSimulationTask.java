package io.github.david_ernstsson.smarthome.doorcamera.domain;

import eu.arrowhead.application.skeleton.publisher.event.PresetEventType;
import eu.arrowhead.application.skeleton.publisher.service.PublisherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Component
@Service

public class HomeOwnerSimulationTask {
    @Autowired
    private PublisherService publisherService;

    private final Logger logger = LogManager.getLogger(HomeOwnerSimulationTask.class);

    private static final int SIMULATION_SPEED = 60; // 1 real minute = 1 simulated week
    private static final int SECONDS_IN_A_WEEK = 7 * 24 * 60 * 60;
    public boolean isAtHome=true;
    private final long simulationStartTime = System.currentTimeMillis();
    private static final String CAME_HOME ="Came home";

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

        String activity = getActivity(day, time);
        if(isAtHome && !Objects.equals(activity, CAME_HOME)){
            isAtHome=false;
            publisherService.publish(PresetEventType.HOMEOWNER_LEFT, null, activity);
            logger.info("{} | {} | {}", day, time, activity);
        }
        else if(!isAtHome && Objects.equals(activity, CAME_HOME)){
            isAtHome=true;
            publisherService.publish(PresetEventType.HOMEOWNER_CAME_HOME, null, activity);
            logger.info("{} | {} | {}", day, time, activity);
        }
    }

    private static String getActivity(DayOfWeek day, LocalTime time) {
        if (day == DayOfWeek.SATURDAY) {
            if (isWithin(time, LocalTime.of(10, 0), LocalTime.of(11, 30))) {
                return "Going shopping";
            } else if (isWithin(time, LocalTime.of(18, 0), LocalTime.of(23, 0))) {
                return "Going out to eat and have a beer";
            } else {
                return CAME_HOME;
            }
        } else if (day == DayOfWeek.SUNDAY) {
            if (isWithin(time, LocalTime.of(8, 0), LocalTime.of(10, 0))) {
                return "Going to church";
            } else {
                return CAME_HOME;
            }
        } else {
            if (isWithin(time, LocalTime.of(7, 30), LocalTime.of(17, 0))) {
                return "Going to work";
            } else {
                return CAME_HOME;
            }
        }
    }

    private static boolean isWithin(LocalTime current, LocalTime start, LocalTime end) {
        return !current.isBefore(start) && current.isBefore(end);
    }
}