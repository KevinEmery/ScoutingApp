package org.usfirst.frc.team4911.scouting.datamodel;

/**
 * Created by Anne_ on 1/28/2017.
 *
 * Extends matchevent to include data particular to baseline crossing.
 */

public class CrossedBaselineEvent extends MatchEvent {
    private boolean crossedBaseLine;

    public boolean getCrossedBaseLine() {
        return this.crossedBaseLine;
    }

    public void setCrossedBaseLine(boolean crossedBaseLine) {
        this.crossedBaseLine = crossedBaseLine;
    }
}
