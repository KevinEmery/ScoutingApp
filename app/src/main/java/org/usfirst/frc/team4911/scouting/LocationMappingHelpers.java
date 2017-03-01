package org.usfirst.frc.team4911.scouting;

import org.usfirst.frc.team4911.scouting.datamodel.GearPegPosition;
import org.usfirst.frc.team4911.scouting.datamodel.TouchPadPosition;

/**
 * Created by Anne_ on 2/28/2017.
 *
 * Dumping ground for Scott's location map code.
 * Note that all this will need to be changed whenever we mess with the picture.
 */

class LocationMappingHelpers {

    /**
     * Maps raw XY coordinates to a touchpad position.
     * @param x The X coordinate of the point to map
     * @param y The Y coordinate of the point to map
     * @param isBlueAlliance True if the bot belongs to the blue alliance (false if red).
     * @return The touchpad position the given point corresponds to.
     */
    static TouchPadPosition GetTouchPadPosition(int x, int y, boolean isBlueAlliance) {

        int t1xmin, t1ymin, t1xmax, t1ymax;
        int t2xmin, t2ymin, t2xmax, t2ymax;
        int t3xmin, t3ymin, t3xmax, t3ymax;

        if (isBlueAlliance) {
            //Blue variables.

            t1xmin = 20; t1ymin = 250; t1xmax = 75; t1ymax = 310;
            t2xmin = 190; t2ymin = 340; t2xmax = 230; t2ymax = 410;
            t3xmin = 20; t3ymin = 430; t3xmax = 75; t3ymax = 495;
        } else {
            //Must be red.
            t1xmin = 380; t1ymin = 245; t1xmax = 425; t1ymax = 310;
            t2xmin = 210; t2ymin = 330; t2xmax = 265; t2ymax = 410;
            t3xmin = 385; t3ymin = 425; t3xmax = 430; t3ymax = 490;
        }
        if ((x > t1xmin) && (x < t1xmax) && (y > t1ymin) && (y < t1ymax)) {
            return TouchPadPosition.T1;
        } else if ((x > t2xmin) && (x < t2xmax) && (y > t2ymin) && (y < t2ymax)) {
            return TouchPadPosition.T2;
        } else if ((x > t3xmin) && (x < t3xmax) && (y > t3ymin) && (y < t3ymax)) {
            return TouchPadPosition.T3;
        } else {
            return TouchPadPosition.None;
        }
    }

    /**
     * Calculates the gear peg position that a given point on the screen corresponds to.
     * @param x The x coordinate of the given point
     * @param y The y coordinate of the given point
     * @param isBlueAlliance True if the bot is from the blue alliance, false if red.
     * @return The gear peg position corresponding to the touched point.
     */
    static GearPegPosition GetGearPegPosition(int x, int y, boolean isBlueAlliance) {

        int g1xmin, g1ymin, g1xmax, g1ymax;
        int g2xmin, g2ymin, g2xmax, g2ymax;
        int g3xmin, g3ymin, g3xmax, g3ymax;

        if (isBlueAlliance) {
            //Blue variables.
            g1xmin = 125; g1ymin = 245; g1xmax = 175; g1ymax = 295;
            g2xmin = 185; g2ymin = 340; g2xmax = 240; g2ymax = 410;
            g3xmin = 140; g3ymin = 425; g3xmax = 195; g3ymax = 490;
        } else {
            //Must be red.
            g1xmin = 265; g1ymin = 240; g1xmax = 320; g1ymax = 310;
            g2xmin = 210; g2ymin = 330; g2xmax = 265; g2ymax = 410;
            g3xmin = 260; g3ymin = 430; g3xmax = 315; g3ymax = 490;
        }
        if ((x > g1xmin) && (x < g1xmax) && (y > g1ymin) && (y < g1ymax)) {
            return GearPegPosition.G1;
        } else if ((x > g2xmin) && (x < g2xmax) && (y > g2ymin) && (y < g2ymax)) {
            return GearPegPosition.G2;
        } else if ((x > g3xmin) && (x < g3xmax) && (y > g3ymin) && (y < g3ymax)) {
            return GearPegPosition.G3;
        } else {
            return GearPegPosition.None;
        }
    }

    /**
     * Gets the position in feet from the boiler of a given point.
     * @param x X coordinate of point.
     * @param y Y coordinate of point.
     * @param isBlueAlliance True if the bot is from the blue alliance, false if red.
     * @return A string containing the feet of the format 'X: *x in feet* Y: *y in feet*'
     */
    static String GetShootingPosition(int x, int y, boolean isBlueAlliance) {

        int xmin, xmax, ymin, ymax;
        Double dblxFromBoiler, dblyFromBoiler;
        String strxFeet, stryFeet;

        if (isBlueAlliance) {
            xmin = 48;
            xmax = 356;
            ymin = 88;
            ymax = 650;

            //Check if selection is out of bounds.  Doesn't take into account key and airship areas yet :(
            if (((x < xmin) || (x > xmax)) || ((y < ymin) || (y > ymax))) {
                return "OUT OF BOUNDS";
            } else {
                //Convert x coordinate to feet from boiler.
                //15.44' / (356-48) = .05013
                //Convert y coordinate to feet from boiler.
                //27' / (650-88) = .04804

                dblxFromBoiler = (356 - x) * .05013;
                dblyFromBoiler = (650 - y) * .04804;

                strxFeet = String.valueOf(dblxFromBoiler);
                stryFeet = String.valueOf(dblyFromBoiler);

                return  "X: " + strxFeet + " Y: " + stryFeet;
            }
        } else {
            //Must be red alliance.
            xmin = 90;
            xmax = 400;
            ymin = 90;
            ymax = 650;
            //Check if selection is out of bounds.  Doesn't take into account key and airship areas yet.
            if (((x < xmin) || (x > xmax)) || ((y < ymin) || (y > ymax))) {
                return "OUT OF BOUNDS";
            } else {
                //Convert x coordinate to feet from boiler.
                //15.44' / (400-90) = .04981
                //Convert y coordinate to feet from boiler.
                //27' / (650-90) = .04821

                dblxFromBoiler = 15.44 -((400 - x) * .04981);
                dblyFromBoiler = (650 - y) * .04821;

                strxFeet = String.valueOf(dblxFromBoiler);
                stryFeet = String.valueOf(dblyFromBoiler);

                return  "X: " + strxFeet + " Y: " + stryFeet;
            }
        }
    }
}
