package org.usfirst.frc.team4911.scouting.matchscouting.datamodel;

import java.util.Calendar;

/**
 * Created by Anne_ on 1/18/2017.
 *
 * Extends matchevent to include data particular to climbing events.
 */

public class ClimbingEvent extends MatchEvent {
    private boolean attempted;
    private boolean succeeded;

    // A secondary timestamp indicating *when* a climbing attempt succeeded.
    // Set when the scout hits the 'succeeded' button.
    private String succeededTime;

    public ClimbingEvent() {
        super.eventType = EventType.CLIMBING;
    }

    public boolean getAttempted() {
        return this.attempted;
    }

    public void setAttempted(boolean attempted) {
        this.attempted = attempted;
    }

    public boolean getSucceeded() {
        return this.succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public void logSucceededTime() {
        this.succeededTime = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance()
                        .getTime());
    }
}
