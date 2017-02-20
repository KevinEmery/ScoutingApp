package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

/**
 * Created by johansu on 2/19/2017.
 */

public class GearAttemptTeleop extends GearAttempt {
    @SerializedName("WasDefended") boolean wasDefended;
    @SerializedName("TimeStamp") String timeStamp;

    public GearAttemptTeleop() {
        this.timeStamp = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance()
                .getTime());
    }

    public boolean getWasDefended() {
        return wasDefended;
    }

    public void setWasDefended(boolean wasDefended) {
        this.wasDefended = wasDefended;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }}
