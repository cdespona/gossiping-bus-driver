package cde.cw;

import org.apache.commons.lang3.builder.*;

import java.util.*;

public class BusStop {
    private final int number;
    private final Set<BusDriver> drivers;

    public BusStop(int number) {
        this.number = number;
        drivers = new HashSet<>();
    }

    public Set<BusDriver> findDrivers() {
        return drivers;
    }

    public BusStop arriving(BusDriver driver) {
        drivers.add(driver);
        return this;
    }

    public BusStop leaving(BusDriver busDriver) {
        drivers.remove(busDriver);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof BusStop)) return false;

        BusStop busStop = (BusStop) o;

        return new EqualsBuilder()
                .append(number, busStop.number)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(number)
                .toHashCode();
    }
}
