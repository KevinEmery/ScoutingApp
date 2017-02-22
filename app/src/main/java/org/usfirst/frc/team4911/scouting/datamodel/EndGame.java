package org.usfirst.frc.team4911.scouting.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johansu on 2/19/2017.
 */

public class EndGame {
    // Did robot attempt to climb?
    @SerializedName("Attempted") private boolean attempted = false;

    // Did robot succeed in climbing?
    @SerializedName("Succeeded") private boolean succeeded = false;

    // How long did it take to climb?
    @SerializedName("TimeInSeconds") private int timeInSeconds = 0;

    // Which touch pad position did they climb at?
    @SerializedName("TouchPadPosition") private TouchPadPosition touchPadPosition =
            TouchPadPosition.None;

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
