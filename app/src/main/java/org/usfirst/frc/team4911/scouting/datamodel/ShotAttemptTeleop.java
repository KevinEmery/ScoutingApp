package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

/**
 * Created by johansu on 2/19/2017.
 */

public class ShotAttemptTeleop extends ShotAttempt {

    @SerializedName("WasDefended") boolean wasDefended = false;
    @SerializedName("TimeStamp") String timeStamp = "";

    public ShotAttemptTeleop() {
        this.setTimeStamp(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance()
                .getTime()));
    }

    public ShotAttemptTeleop(ShotAttempt shotAttempt) {
        this.setTimeStamp(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance()
                .getTime()));
        this.setShotsMade(shotAttempt.getShotsMade());
        this.setShotsMissed(shotAttempt.getShotsMissed());
        this.setShotLocation(shotAttempt.getShotLocation());
        this.setShotMode(shotAttempt.getShotMode());
        this.setShotDurationInSeconds(shotAttempt.getShotDurationInSeconds());
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
    }
}
