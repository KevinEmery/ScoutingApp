package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johansu on 2/19/2017.
 */

public class PreGame {

    // Does robot start with a gear?
    @SerializedName("HasGear") private boolean hasGear = false;

    // Does robot start with fuel?
    @SerializedName("HasFuel") private boolean hasFuel = false;

    // How much fuel does robot start with?
    @SerializedName("FuelCount") private int fuelCount = 0;

    // Does robot use it's own rope?
    @SerializedName("UsesOwnRope") private boolean usesOwnRope = false;

    // What touch pad position is rope place at?
    @SerializedName("RopeTouchPadPosition") private TouchPadPosition ropeTouchPadPosition =
            TouchPadPosition.None;

    public boolean getHasGear() {
        return hasGear;
    }

    public boolean getHasFuel() {
        return hasFuel;
    }

    public int getFuelCount() {
        return fuelCount;
    }

    public boolean getUsesOwnRope() {
        return usesOwnRope;
    }

    public TouchPadPosition getRopeTouchPadPosition() {
        return ropeTouchPadPosition;
    }

    public void setHasGear(boolean hasGear) {
        this.hasGear = hasGear;
    }

    public void setHasFuel(boolean hasFuel) {
        this.hasFuel = hasFuel;
    }

    public void setFuelCount(int fuelCount) {
        this.fuelCount = fuelCount;
    }

    public void setUsesOwnRope(boolean usesOwnRope) {
        this.usesOwnRope = usesOwnRope;
    }

    public void setRopeTouchPadPosition(TouchPadPosition ropeTouchPadPosition) {
        this.ropeTouchPadPosition = ropeTouchPadPosition;
    }
}
