package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johansu on 2/19/2017.
 */

public class TeleopPeriod {
    // Did robot play defense during teleop period?
    @SerializedName("PlayedDefense") private boolean playedDefense = false;

    // Count of attempts to load gears at airship.
    @SerializedName("GearAttemptCount") private int gearAttemptCount = 0;

    // Count of attempts to shot balls.
    @SerializedName("ShotAttemptCount") private int ShotAttemptCount = 0;

    @SerializedName("GearAttempts") private List<GearAttemptTeleop> gearAttempts;

    @SerializedName("ShotAttempts") private List<ShotAttemptTeleop> shotAttempts;

    @SerializedName("HopperAttempts") private List<HopperAttempt> hopperAttempts;

    public TeleopPeriod() {
        this.setGearAttempts(new ArrayList<GearAttemptTeleop>());
        this.setShotAttempts(new ArrayList<ShotAttemptTeleop>());
        this.setHopperAttempts(new ArrayList<HopperAttempt>());
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

    public int getGearAttemptCount() {
        return gearAttemptCount;
    }

    public void setGearAttempts(List<GearAttemptTeleop> gearAttempts) {
        this.gearAttempts = gearAttempts;
        this.setGearAttemptCount(gearAttempts.size());
    }

    public void setPlayedDefense(boolean playedDefense) {
        this.playedDefense = playedDefense;
    }

    public boolean getPlayedDefence() {
        return this.playedDefense;
    }

    public void setShotAttemptCount(int shotAttemptCount) {
        ShotAttemptCount = shotAttemptCount;
    }

    public int getShotAttemptCount() {
        return ShotAttemptCount;
    }

    private void setShotAttempts(List<ShotAttemptTeleop> shotAttempts) {
        this.shotAttempts = shotAttempts;
        this.setShotAttemptCount(shotAttempts.size());
    }

    public List<HopperAttempt> getHopperAttempts() {
        return hopperAttempts;
    }

    private void setHopperAttempts(List<HopperAttempt> hopperAttempts) {
        this.hopperAttempts = hopperAttempts;
    }
}

