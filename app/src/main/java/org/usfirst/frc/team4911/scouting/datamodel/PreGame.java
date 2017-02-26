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
    @SerializedName("HasPilot") private boolean hasPilot = false;

    // Does robot use it's own rope?
    @SerializedName("UsesOwnRope") private boolean usesOwnRope = false;

    // What touch pad position is rope place at?
    @SerializedName("RopeTouchPadPosition") private TouchPadPosition ropeTouchPadPosition =
            TouchPadPosition.None;

    public boolean getHasGear() {
        return hasGear;
    }

    public void setHasGear(boolean hasGear) {
        this.hasGear = hasGear;
    }

    public boolean getHasFuel() {
        return hasFuel;
    }

    public void setHasFuel(boolean hasFuel) {
        this.hasFuel = hasFuel;
    }


    public boolean getUsesOwnRope() {
        return usesOwnRope;
    }
    public void setUsesOwnRope(boolean usesOwnRope) {
        this.usesOwnRope = usesOwnRope;
    }

    public void setHasPilot(boolean hasPilot) {
        this.hasPilot= hasPilot;
    }

    public boolean getHasPilot() {
        return hasPilot;
    }

    public TouchPadPosition getRopeTouchPadPosition() {
        return ropeTouchPadPosition;
    }
    public void setRopeTouchPadPosition(TouchPadPosition ropeTouchPadPosition) {
        this.ropeTouchPadPosition = ropeTouchPadPosition;
    }
}
