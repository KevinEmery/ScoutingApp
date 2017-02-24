package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johansu on 2/19/2017.
 */

public class ShotAttempt {
    @SerializedName("ShotSpeed") ShotSpeed shotSpeed = ShotSpeed.Slow;
    @SerializedName("ShotAccuracy") ShotAccuracy shotAccuracy = ShotAccuracy.Failed;
    @SerializedName("FuelAmount") FuelAmount fuelAmount = FuelAmount.Small;
    @SerializedName("ShotLocation") String shotLocation = "";
    @SerializedName("ShotMode") ShotMode shotMode = ShotMode.Low;

    public FuelAmount getFuelAmount() {
        return fuelAmount;
    }

    public ShotAccuracy getShotAccuracy() {
        return shotAccuracy;
    }

    public String getShotLocation() {
        return shotLocation;
    }

    public ShotSpeed getShotSpeed() {
        return shotSpeed;
    }

    public ShotMode getShotMode() {
        return shotMode;
    }

    public void setFuelAmount(FuelAmount fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public void setShotAccuracy(ShotAccuracy shotAccuracy) {
        this.shotAccuracy = shotAccuracy;
    }

    public void setShotLocation(String shotLocation) {
        this.shotLocation = shotLocation;
    }

    public void setShotMode(ShotMode shotMode) {
        this.shotMode = shotMode;
    }

    public void setShotSpeed(ShotSpeed shotSpeed) {
        this.shotSpeed = shotSpeed;
    }
}
