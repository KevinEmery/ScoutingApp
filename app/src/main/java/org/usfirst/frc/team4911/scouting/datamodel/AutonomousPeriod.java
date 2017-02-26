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

    // Did robot get fuel from hopper?
    @SerializedName("LoadedFromHopper") private boolean loadedFromHopper = false;

    @SerializedName("GearAttempts") private List<GearAttempt> gearAttempts;

    @SerializedName("ShotAttempts") private List<ShotAttempt> shotAttempts;

    public AutonomousPeriod() {
        this.setGearAttempts(new ArrayList<GearAttempt>());
        this.setShotAttempts(new ArrayList<ShotAttempt>());
    }

    public boolean getAutoMobilityPoints() {
        return autoMobilityPoints;
    }

    public boolean getsLoadedFromHopper() {
        return loadedFromHopper;
    }

     public void setAutoMobilityPoints(boolean autoMobilityPoints) {
        this.autoMobilityPoints = autoMobilityPoints;
    }

    public void setLoadedFromHopper(boolean loadedFromHopper) {
        this.loadedFromHopper = loadedFromHopper;
    }

    public List<GearAttempt> getGearAttempts() {
        return gearAttempts;
    }

    public void setGearAttempts(List<GearAttempt> gGearAttempts) {
        this.gearAttempts = gGearAttempts;
    }

    public List<ShotAttempt> getShotAttempts() {
        return shotAttempts;
    }

    public void setShotAttempts(List<ShotAttempt> shotAttempts) {
        this.shotAttempts = shotAttempts;
    }
}
