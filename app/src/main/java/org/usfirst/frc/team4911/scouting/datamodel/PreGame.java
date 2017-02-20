package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johansu on 2/19/2017.
 */

public class PreGame {

    // Does robot start with a gear?
    @SerializedName("HasGear") boolean hasGear;

    // Does robot start with fuel?
    @SerializedName("HasFuel") boolean hasFuel;

    // How much fuel does robot start with?
    @SerializedName("FuelCount") int fuelCount;

    // Does robot use it's own rope?
    @SerializedName("UsesOwnRope") boolean usesOwnRope;

    // What touch pad position is rope place at?
    @SerializedName("RopeTouchPadPosition") TouchPadPosition ropeTouchPadPosition;

    public boolean getHasFuel() {
        return hasFuel;
    }

    public boolean getHasGear() {
        return hasGear;
    }

    public boolean getUsesOwnRope() {
        return usesOwnRope;
    }

    public int getFuelCount() {
        return fuelCount;
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
