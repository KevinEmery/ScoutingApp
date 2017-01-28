package com.example.angwy.scoutingapp.matchscouting.datamodel;

import java.util.Date;

/**
 * Created by Anne_ on 1/18/2017.
 */

public class ClimbingEvent extends MatchEvent {
    public boolean attempted;
    public boolean succeeded;

    // A secondary timestamp indicating *when* a climbing attempt succeeded.
    // Set when the scout hits the 'succeeded' button.
    public Date succeededTime;
}
