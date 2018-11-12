package cde.cw;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.*;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayOfWorkShould {

    private static final BusStop BUS_STOP_1 = new BusStop(1);
    private static final BusStop BUS_STOP_2 = new BusStop(2);

    @Test
    void let_us_know_when_all_the_gossips_are_shared_within_all_the_drivers() {
        Set<String> gossips = Stream.of("gossip1").collect(toSet());
        List<BusStop> firstRoute = asList(BUS_STOP_1, BUS_STOP_2);
        List<BusStop> secondRoute = asList(BUS_STOP_2, BUS_STOP_2);
        BusDriver driver1 = new BusDriver("Pepe", firstRoute, gossips);
        BusDriver driver2 = new BusDriver("Jordi", secondRoute);

        DayOfWork dayOfWork = new DayOfWork(asList(driver1, driver2));

        assertEquals(2, dayOfWork.minutesCounting());
    }

    @Test
    void example_1() {
        Set<String> gossips = Stream.of("gossip1").collect(toSet());
        Set<String> gossips2 = Stream.of("gossip2").collect(toSet());
        Set<String> gossips3 = Stream.of("gossip3").collect(toSet());
        BusStop busStop1 = new BusStop(1);
        BusStop busStop2 = new BusStop(2);
        BusStop busStop3 = new BusStop(3);
        BusStop busStop4 = new BusStop(4);
        BusStop busStop5 = new BusStop(5);
        List<BusStop> firstRoute = asList(busStop3, busStop1, busStop2, busStop3);
        List<BusStop> secondRoute = asList(busStop3, busStop2, busStop3, busStop1);
        List<BusStop> thirdRoute = asList(busStop4, busStop2, busStop3, busStop4, busStop5);
        BusDriver driver1 = new BusDriver("Pepe", firstRoute, gossips);
        BusDriver driver2 = new BusDriver("Jordi", secondRoute, gossips2);
        BusDriver driver3 = new BusDriver("Samantha", thirdRoute, gossips3);

        DayOfWork dayOfWork = new DayOfWork(asList(driver1, driver2, driver3));

        assertEquals(5, dayOfWork.minutesCounting());
    }

    @Test
    void example_2() {
        Set<String> gossips = Stream.of("gossip1").collect(toSet());
        Set<String> gossips2 = Stream.of("gossip2").collect(toSet());
        BusStop busStop1 = new BusStop(1);
        BusStop busStop2 = new BusStop(2);
        BusStop busStop5 = new BusStop(5);
        BusStop busStop8 = new BusStop(8);
        List<BusStop> firstRoute = asList(busStop2, busStop1, busStop2);
        List<BusStop> secondRoute = asList(busStop5, busStop2, busStop8);
        BusDriver driver1 = new BusDriver("Pepe", firstRoute, gossips);
        BusDriver driver2 = new BusDriver("Jordi", secondRoute, gossips2);

        DayOfWork dayOfWork = new DayOfWork(asList(driver1, driver2));

        assertEquals(480, dayOfWork.minutesCounting());
    }
}
