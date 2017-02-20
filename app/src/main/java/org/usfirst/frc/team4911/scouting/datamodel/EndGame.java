package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johansu on 2/19/2017.
 */

public class EndGame {
    // Did robot attempt to climb?
    @SerializedName("Attempted") boolean attempted;

    // Did robot succeed in climbing?
    @SerializedName("Succeeded") boolean succeeded;

    // How long did it take to climb?
    @SerializedName("TimeInSeconds") int timeInSeconds;

    // Which touch pad position did they climb at?
    @SerializedName("TouchPadPosition") TouchPadPosition touchPadPosition;

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public TouchPadPosition getTouchPadPosition() {
        return touchPadPosition;
    }

    public boolean getAttempted() {
        return attempted;
    }

    public boolean getSucceeded() {
        return succeeded;
    }

    public void setAttempted(boolean attempted) {
        this.attempted = attempted;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public void setTouchPadPosition(TouchPadPosition touchPadPosition) {
        this.touchPadPosition = touchPadPosition;
    }
}
