package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johansu on 2/19/2017.
 */

public class AutonomousPeriod {

    // Does robot cross baseline by t=0 for 5 points?
    @SerializedName("AutoMobilityPoints") private boolean autoMobilityPoints = false;

    // Records the attempts to load a gear.
    @SerializedName("GearAttempts") private List<GearAttempt> gearAttempts;

    // Records the attempts to shot fuel.
    @SerializedName("ShotAttempts") private List<ShotAttempt> shotAttempts;

    // Records any attempts to activate a hopper.
    @SerializedName("HopperAttempts") private List<HopperAttempt> hopperAttempts;

    public AutonomousPeriod() {
        this.setGearAttempts(new ArrayList<GearAttempt>());
        this.setShotAttempts(new ArrayList<ShotAttempt>());
        this.setHopperAttempts(new ArrayList<HopperAttempt>());
    }

    public boolean getAutoMobilityPoints() {
        return autoMobilityPoints;
    }

    public void setAutoMobilityPoints(boolean autoMobilityPoints) {
        this.autoMobilityPoints = autoMobilityPoints;
    }

    public List<GearAttempt> getGearAttempts() {
        return gearAttempts;
    }

    private void setGearAttempts(List<GearAttempt> gGearAttempts) {
        this.gearAttempts = gGearAttempts;
    }

    public List<ShotAttempt> getShotAttempts() {
        return shotAttempts;
    }

    public void setShotAttempts(List<ShotAttempt> shotAttempts) {
        this.shotAttempts = shotAttempts;
    }

    private void AddShotAttempt(ShotAttempt shotAttempt) {
        this.shotAttempts.add(shotAttempt);
    }

    public List<HopperAttempt> getHopperAttempts() {
        return hopperAttempts;
    }

    private void setHopperAttempts(List<HopperAttempt> hopperAttempts) {
        this.hopperAttempts = hopperAttempts;
    }
}
