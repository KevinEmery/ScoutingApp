package com.example.angwy.scoutingapp.matchscouting.datamodel;

/**
 * Created by Anne_ on 1/18/2017.
 *
 * Extends matchevent to include data particular to climbing events.
 */

public class ClimbingEvent extends MatchEvent {
    public boolean attempted;
    public boolean succeeded;

    // A secondary timestamp indicating *when* a climbing attempt succeeded.
    // Set when the scout hits the 'succeeded' button.
    public String succeededTime;

    public ClimbingEvent() {
        super();
        super.eventType = EventType.CLIMBING;
    }
}
