package org.usfirst.frc.team4911.scouting.matchscouting.datamodel;

import java.util.Calendar;

/**
 * Created by Anne_ on 1/18/2017.
 *
 * Base class for events that we log.
 */

public class MatchEvent {
    protected EventType eventType;
    private int xCoordinate;
    private int yCoordinate;
    private String timeStamp;
    private boolean wasDefended;
    private Role role;

    public MatchEvent() {
        xCoordinate = 1;
        yCoordinate = 1;
        timeStamp = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance()
                .getTime());
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public int getxCoordinate() {
        return this.xCoordinate;
    }

    public int getyCoordinate() {
        return this.yCoordinate;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public boolean getWasDefended() {
        return this.wasDefended;
    }

    public void setWasDefended(boolean wasDefended) {
        this.wasDefended = wasDefended;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
