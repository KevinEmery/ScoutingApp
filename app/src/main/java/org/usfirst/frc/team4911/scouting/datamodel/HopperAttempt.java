package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johansu on 2/26/2017.
 */

public class HopperAttempt {
    // Did the robot activate, i.e. hit and dump the balls, from this hopper?
    @SerializedName("Activated") private Boolean activated;

    // The location of the hopper.
    @SerializedName("HopperLocation") private String hopperLocation;


    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getHopperLocation() {
        return hopperLocation;
    }

    public void setHopperLocation(String hopperLocation) {
        this.hopperLocation = hopperLocation;
    }
}



