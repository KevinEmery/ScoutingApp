package org.usfirst.frc.team4911.scouting;

/**
 * Created by Anne_ on 2/20/2017.
 *
 * This is a class that must be implemented by everything that uses a RecordLocationEvent fragment.
 * It's called when the location is determined and provides a way to pass event location data back
 * to the parent fragment.
 */

public interface OnRecordLocationEventListener {
    void OnRecordLocationEvent(Object locationObject);
}
