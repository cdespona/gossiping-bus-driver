package cde.cw;

import org.apache.commons.lang3.builder.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

import static java.util.stream.Stream.empty;

public class BusDriver {
    private final String name;
    private final List<BusStop> route;
    private final Set<String> gossips;
    private BusStop currentStop;

    public BusDriver(String name, List<BusStop> route) {
        this.name = name;
        this.route = route;
        gossips = new HashSet<>();
        currentStop = null;
    }

    public BusDriver(String name, List<BusStop> route, Set<String> gossips) {
        this.name = name;
        this.route = route;
        this.gossips = gossips;
        currentStop = null;
    }

    public BusDriver drive(int minute) {
        return leaveTheCurrentStopIfPossibleAt(minute, this::thenDriveToTheNextStop);
    }

    public void gossip() {
        lookAroundFor(this::otherDrivers)
                .forEach(listenGossips());
    }

    public Set<String> gossips() {
        return gossips;
    }

    public BusStop stopIAmIn() {
        return currentStop;
    }

    private BusDriver leaveTheCurrentStopIfPossibleAt(Integer minute, Function<Integer, BusDriver> function) {
        if (currentStop != null)
            currentStop.leaving(this);

        return function.apply(minute);
    }

    @NotNull
    private BusDriver thenDriveToTheNextStop(int minute) {
        BusStop arriving = route
                .get(minute % route.size())
                .arriving(this);

        return this.toStop(arriving);
    }

    private BusDriver toStop(BusStop arriving) {
        currentStop = arriving;
        return this;
    }

    private Stream<BusDriver> lookAroundFor(Predicate<BusDriver> otherDrivers) {
        return currentStop != null
                ? currentStop.findDrivers().stream().filter(otherDrivers)
                : empty();
    }

    private Boolean otherDrivers(BusDriver busDriver) {
        return !busDriver.equals(this);
    }

    @NotNull
    private Consumer<BusDriver> listenGossips() {
        return driver -> driver.listenToGossips(this.gossips);
    }

    private void listenToGossips(Set<String> gossips) {
        this.gossips.addAll(gossips);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof BusDriver)) return false;

        BusDriver driver = (BusDriver) o;

        return new EqualsBuilder()
                .append(name, driver.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }
}
