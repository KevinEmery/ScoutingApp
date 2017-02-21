package org.usfirst.frc.team4911.scouting;

/**
 * Created by Anne_ on 2/20/2017.
 *
 * Used by RecordLocationFragment to know which alliance a team belongs to.
 */

public enum AllianceType {
    RED(0), BLUE(1);

    private final int value;

    AllianceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
