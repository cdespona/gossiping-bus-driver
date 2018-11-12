package cde.cw;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toSet;

public class DayOfWork {
    private final List<BusDriver> busDrivers;
    protected static final int MINUTES_TO_WORK_DONE = 480;

    public DayOfWork(List<BusDriver> busDrivers) {
        this.busDrivers = busDrivers;
    }


    public int minutesCounting() {
        AtomicInteger minutesPassedBy = new AtomicInteger();
        while (notEveryBodyAware() && notEndOfDayYet(minutesPassedBy.get())) {
            busDrivers.forEach(drive(minutesPassedBy).andThen(BusDriver::gossip));
            minutesPassedBy.getAndIncrement();
        }

        return minutesPassedBy.get();
    }

    private boolean notEveryBodyAware() {
        Set<String> allGossips = busDrivers.stream()
                .map(BusDriver::gossips)
                .flatMap(Collection::stream)
                .collect(toSet());
        return !busDrivers.stream()
                .map(BusDriver::gossips)
                .allMatch(driverGossips -> driverGossips.containsAll(allGossips));
    }

    private boolean notEndOfDayYet(int minutesPassedBy) {
        return minutesPassedBy < MINUTES_TO_WORK_DONE;
    }

    @NotNull
    private Consumer<BusDriver> drive(AtomicInteger minutesPassedBy) {
        return busDriver -> busDriver.drive(minutesPassedBy.get());
    }
}
