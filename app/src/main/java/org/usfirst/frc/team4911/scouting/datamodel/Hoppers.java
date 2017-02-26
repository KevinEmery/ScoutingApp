package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johansu on 2/26/2017.
 */

public class Hoppers {
    @SerializedName("Hopper1") Boolean hopper1;
    @SerializedName("Hopper2") Boolean hopper2;
    @SerializedName("Hopper3") Boolean hopper3;
    @SerializedName("Hopper4") Boolean hopper4;
    @SerializedName("Hopper5") Boolean hopper5;

    public Boolean getHopper1() {
        return hopper1;
    }

    public void setHopper1(Boolean hopper1) {
        this.hopper1 = hopper1;
    }

    public Boolean getHopper2() {
        return hopper2;
    }

    public void setHopper2(Boolean hopper2) {
        this.hopper2 = hopper2;
    }

    public Boolean getHopper3() {
        return hopper3;
    }

    public void setHopper3(Boolean hopper3) {
        this.hopper3 = hopper3;
    }

    public Boolean getHopper4() {
        return hopper4;
    }

    public void setHopper4(Boolean hopper4) {
        this.hopper4 = hopper4;
    }

    public Boolean getHopper5() {
        return hopper5;
    }

    public void setHopper5(Boolean hopper5) {
        this.hopper5 = hopper5;
    }
}

