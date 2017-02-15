package org.usfirst.frc.team4911.scouting.datamodel;

/**
 * Created by Anne_ on 1/18/2017.
 *
 * Class containing the data for a shot event.
 */

public class ShotEvent extends MatchEvent {
    private ShotSpeed shotSpeed;
    private ShotAccuracy shotAccuracy;
    private ShotQuantity shotQuantity;
    private ShotTarget shotTarget;

    public ShotSpeed getShotSpeed() {
        return this.shotSpeed;
    }

    public void setShotSpeed(ShotSpeed shotSpeed) {
        this.shotSpeed = shotSpeed;
    }

    public ShotAccuracy getShotAccuracy() {
        return this.shotAccuracy;
    }

    public void setShotAccuracy(ShotAccuracy shotAccuracy) {
        this.shotAccuracy = shotAccuracy;
    }

    public ShotQuantity getShotQuantity() {
        return this.shotQuantity;
    }

    public void setShotQuantity(ShotQuantity shotQuantity) {
        this.shotQuantity = shotQuantity;
    }

    public ShotTarget getShotTarget() {
        return this.shotTarget;
    }

    public void setShotTarget(ShotTarget shotTarget) {
        this.shotTarget = shotTarget;
    }
}
