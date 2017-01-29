package com.example.angwy.scoutingapp.matchscouting.datamodel;

import java.util.Calendar;

/**
 * Created by Anne_ on 1/18/2017.
 *
 * Base class for events that we log.
 */

public class MatchEvent {
    public EventType eventType;
    public int xCoordinate;
    public int yCoordinate;
    public String timeStamp;
    public boolean wasDefended;
    public Role role;

    public MatchEvent() {
        xCoordinate = 1;
        yCoordinate = 1;
        timeStamp = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance()
                .getTime());
    }
}
