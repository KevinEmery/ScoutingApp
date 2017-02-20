package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johansu on 2/19/2017.
 */

public class AutonomousPeriod {

    // Does robot cross baseline by t=0 for 5 points?
    @SerializedName("AutoMobilityPoints") boolean autoMobilityPoints;

    // Did robot get fuel from hopper?
    @SerializedName("LoadedFromHopper") boolean loadedFromHopper;

    @SerializedName("GearAttempts") List<GearAttempt> gearAttempts;

    @SerializedName("ShotAttempts") List<ShotAttempt> shotAttempts;

    public AutonomousPeriod() {
        this.setGearAttempts(new ArrayList<GearAttempt>());
        this.setShotAttempts(new ArrayList<ShotAttempt>());
    }

    public boolean isAutoMobilityPoints() {

        return autoMobilityPoints;
    }

    public boolean isLoadedFromHopper() {

        return loadedFromHopper;
    }

    public List<GearAttempt> getgGearAttempts() {
        return gearAttempts;
    }

    public List<ShotAttempt> getShotAttempts() {
        return shotAttempts;
    }

    public void setAutoMobilityPoints(boolean autoMobilityPoints) {
        this.autoMobilityPoints = autoMobilityPoints;
    }

    public void setLoadedFromHopper(boolean loadedFromHopper) {
        this.loadedFromHopper = loadedFromHopper;
    }

    public void setGearAttempts(List<GearAttempt> gGearAttempts) {
        this.gearAttempts = gGearAttempts;
    }

    public void setShotAttempts(List<ShotAttempt> shotAttempts) {
        this.shotAttempts = shotAttempts;
    }
}
