package org.usfirst.frc.team4911.scouting;

/**
 * Created by Anne_ on 2/20/2017.
 *
 * Used by RecordLocationFragment to track of all possible location match types.
 */

public enum LocationMapType {
    AIRSHIP(0), WHOLEFIELD(1), SHOOTING_AREA(2);

    private final int value;

    LocationMapType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
