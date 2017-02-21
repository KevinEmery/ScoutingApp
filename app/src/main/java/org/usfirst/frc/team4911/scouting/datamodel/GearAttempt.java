package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johansu on 2/19/2017.
 */

public class GearAttempt {
    @SerializedName("GearResult") private String gearResult = "TODO";
    @SerializedName("GearPegPosition") private GearPegPosition gearPegPosition = GearPegPosition.None;

    public GearPegPosition getGearPegPosition() {
        return gearPegPosition;
    }

    public String getGearResult() {
        return gearResult;
    }

    public void setGearPegPosition(GearPegPosition gearPegPosition) {
        this.gearPegPosition = gearPegPosition;
    }

    public void setGearResult(String gearResult) {
        this.gearResult = gearResult;
    }
}
