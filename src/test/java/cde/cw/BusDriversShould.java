package cde.cw;

import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.*;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusDriversShould {

    private BusDriver driver;
    private static final BusStop BUS_STOP_1 = new BusStop(1);
    private static final BusStop BUS_STOP_2 = new BusStop(2);
    private static final List<BusStop> ROUTES = asList(BUS_STOP_1, BUS_STOP_2);

    @BeforeEach
    void setUp() {
        driver = new BusDriver("Pepe", ROUTES);
    }

    @Test
    void drive_to_the_next_stop_in_the_route() {
        assertEquals(BUS_STOP_1, driver.drive(0).stopIAmIn());
    }

    @Test
    void drive_in_a_circular_route() {
        assertEquals(BUS_STOP_1, driver.drive(2).stopIAmIn());
    }

    @Test
    void gossip_everything_he_knows_to_other_drivers() {
        Set<String> gossips = Stream.of("gossip1", "gossip2").collect(toSet());
        BusDriver driver1 = new BusDriver("Pepe", ROUTES, gossips);
        BusDriver driver2 = new BusDriver("Jordi", ROUTES);
        BusDriver driver3 = new BusDriver("Samantha", ROUTES);

        driver1.drive(0);
        driver2.drive(0);
        driver3.drive(0);

        driver1.gossip();

        assertEquals(driver1.gossips(), driver2.gossips());
        assertEquals(driver1.gossips(), driver3.gossips());

    }
}
