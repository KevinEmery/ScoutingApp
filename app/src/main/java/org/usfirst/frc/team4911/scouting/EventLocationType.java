package org.usfirst.frc.team4911.scouting;

/**
 * Created by Anne_ on 2/20/2017.
 *
 * Used by RecordLocationFragment to track where events happen.
 */

public enum EventLocationType {
    SHOOT(0), PLACEGEAR(1), CLIMB(2);

    private final int value;

    EventLocationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
