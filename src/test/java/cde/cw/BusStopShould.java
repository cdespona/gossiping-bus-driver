package cde.cw;

import org.junit.jupiter.api.Test;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

public class BusStopShould {

    @Test
    void have_drivers_stopped() {
        BusStop busStop = new BusStop(1);
        BusDriver driver = new BusDriver("Pepe", emptyList());

        busStop.arriving(driver);

        assertThat(busStop.findDrivers()).contains(driver);
    }

    @Test
    void allow_drivers_to_leave() {
        BusStop busStop = new BusStop(1);
        BusDriver driver = new BusDriver("Pepe", emptyList());

        BusStop leavingStop = busStop.leaving(driver);

        assertThat(leavingStop.findDrivers()).isEmpty();
    }
}
