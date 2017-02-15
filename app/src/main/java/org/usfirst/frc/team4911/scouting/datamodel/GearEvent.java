package org.usfirst.frc.team4911.scouting.datamodel;

/**
 * Created by Anne_ on 1/18/2017.
 *
 * Extends matchevent to contain data particular to gear events.
 */

public class GearEvent extends MatchEvent {
    private GearResult gearResult;

    public GearResult getGearResult() {
        return this.gearResult;
    }

    public void setGearResult(GearResult gearResult) {
        this.gearResult = gearResult;
    }
}
