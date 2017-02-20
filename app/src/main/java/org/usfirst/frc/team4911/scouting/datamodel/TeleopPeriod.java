package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johansu on 2/19/2017.
 */

public class TeleopPeriod {
    // Did robot play defense during teleop period?
    @SerializedName("PlayedDefense") boolean playedDefense;

    // Count of attempts to load gears at airship.
    @SerializedName("GearAttemptCount") int gearAttemptCount;

    // Count of attempts to shot balls.
    @SerializedName("ShotAttemptCount") int ShotAttemptCount;

    @SerializedName("GearAttempts") List<GearAttemptTeleop> gearAttempts;

    @SerializedName("ShotAttempts") List<ShotAttemptTeleop> shotAttempts;

    public TeleopPeriod() {
        this.setGearAttempts(new ArrayList<GearAttemptTeleop>());
        this.setShotAttempts(new ArrayList<ShotAttemptTeleop>());
    }

    public int getGearAttemptCount() {
        return gearAttemptCount;
    }

    public int getShotAttemptCount() {
        return ShotAttemptCount;
    }

    public List<GearAttemptTeleop> getGearAttempts() {
        return gearAttempts;
    }

    public List<ShotAttemptTeleop> getShotAttempts() {
        return shotAttempts;
    }

    public void setGearAttemptCount(int gearAttemptCount) {
        this.gearAttemptCount = gearAttemptCount;
    }

    public void setGearAttempts(List<GearAttemptTeleop> gearAttempts) {
        this.gearAttempts = gearAttempts;
    }

    public void setPlayedDefense(boolean playedDefense) {
        this.playedDefense = playedDefense;
    }

    public void setShotAttemptCount(int shotAttemptCount) {
        ShotAttemptCount = shotAttemptCount;
    }

    public void setShotAttempts(List<ShotAttemptTeleop> shotAttempts) {
        this.shotAttempts = shotAttempts;
    }
}

