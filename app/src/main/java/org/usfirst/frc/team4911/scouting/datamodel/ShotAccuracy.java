package org.usfirst.frc.team4911.scouting.datamodel;

/**
 * Created by Anne_ on 1/18/2017.
 *
 * Enumerates how good the accuracy of a shot attempt was.
 */

public enum ShotAccuracy {
    Failed,  // missed all shots
    Poor, // 0%-39% %
    Average, // 40%-79%
    Great // 80%+
}
