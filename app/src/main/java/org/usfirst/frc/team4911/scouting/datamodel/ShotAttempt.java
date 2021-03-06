package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johansu on 2/19/2017.
 */

public class ShotAttempt {
    @SerializedName("ShotsMade") private int shotsMade = 0;
    @SerializedName("ShotsMissed") private int shotsMissed = 0;
    @SerializedName("ShotLocation") private String shotLocation = "";
    @SerializedName("ShotMode") private ShotMode shotMode = ShotMode.Low;
    @SerializedName("ShotDuration") private int shotDurationInSeconds = 0;

    public int getShotsMissed() {
        return shotsMissed;
    }

    public void setShotsMissed(int shotsMissed) {
        this.shotsMissed = shotsMissed;
    }

    public int getShotsMade() {
        return shotsMade;
    }
    public void setShotsMade(int shotsMade) {
        this.shotsMade = shotsMade;
    }

    public ShotMode getShotMode() {
        return shotMode;
    }
    public void setShotMode(ShotMode shotMode) {
        this.shotMode = shotMode;
    }

    public String getShotLocation() {
        return shotLocation;
    }
    public void setShotLocation(String shotLocation) {
        this.shotLocation = shotLocation;
    }

    public int getShotDurationInSeconds() {
        return shotDurationInSeconds;
    }
    public void setShotDurationInSeconds(int shotDurationInSeconds) {
        this.shotDurationInSeconds = shotDurationInSeconds;
    }
}
