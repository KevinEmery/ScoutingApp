package org.usfirst.frc.team4911.scouting;

import android.support.v4.util.Pair;

import org.usfirst.frc.team4911.scouting.datamodel.GearPegPosition;
import org.usfirst.frc.team4911.scouting.datamodel.HopperPosition;
import org.usfirst.frc.team4911.scouting.datamodel.TouchPadPosition;

/**
 * Created by Anne_ on 2/28/2017.
 *
 * Dumping ground for Scott's location map code.
 * Note that all this will need to be changed whenever we mess with the picture.
 */

class LocationMappingHelpers {
    static final String OUT_OF_BOUNDS = "OUT OF BOUNDS";

    // All the touchpads by alliance
    private static final TouchZone blueTouchpad1 =
            new TouchZone(0.04358876, 0.34606203, 0.1902547, 0.42674464);
    private static final TouchZone blueTouchpad2 =
            new TouchZone(0.3940295, 0.46471292, 0.5445892, 0.5596337);
    private static final TouchZone blueTouchpad3 =
            new TouchZone(0.031907413, 0.604721, 0.17467958, 0.6877766);

    private static final TouchZone redTouchpad1 =
            new TouchZone(0.858688, 0.3579271, 0.9508409, 0.42437163);
    private static final TouchZone redTouchpad2 =
            new TouchZone(0.50175756, 0.49081612, 0.5730721, 0.54539555);
    private static final TouchZone redTouchpad3 =
            new TouchZone(0.84960246, 0.6142131, 0.9261803, 0.6782845);


    // All the gear peg positions by alliance
    private static final TouchZone blueGearPeg1 =
            new TouchZone(0.30057865, 0.3555541, 0.38364607, 0.4101335);
    private static final TouchZone blueGearPeg2 =
            new TouchZone(0.41998807, 0.49081612, 0.51343894, 0.54539555);
    private static final TouchZone blueGearPeg3 =
            new TouchZone(0.3161538, 0.6165861, 0.40441293, 0.6664194);

    private static final TouchZone redGearPeg1 =
            new TouchZone(0.6120815, 0.3531811, 0.69385105, 0.41725257);
    private static final TouchZone redGearPeg2 =
            new TouchZone(0.48748034, 0.49081612, 0.56924987, 0.5620066);
    private static final TouchZone redGearPeg3 =
            new TouchZone(0.5796333, 0.60946697, 0.69514894, 0.6735385);

    // All the hopper positions
    private static final TouchZone hopper1 =
            new TouchZone(0.3029268, 0.033713926, 0.40699184, 0.13676797);
    private static final TouchZone hopper2 =
            new TouchZone(0.6034146, 0.033713926, 0.71788615, 0.13676797);
    private static final TouchZone hopper3 =
            new TouchZone(0.162439, 0.87188673, 0.29902437, 0.96807075);
    private static final TouchZone hopper4 =
            new TouchZone(0.4590244, 0.8856275, 0.56569105, 0.95432997);
    private static final TouchZone hopper5 =
            new TouchZone(0.7217886, 0.8993678, 0.844065, 0.9749409);

    // The shooting zone bounds
    private static final TouchZone blueShootingZone =
            new TouchZone(0.11587928, 0.08642042, 0.81196254, 0.9179594);
    private static final TouchZone redShootingZone =
            new TouchZone(0.1811692, 0.12537135, 0.92098856, 0.9203324);

    /**
     * Maps raw XY coordinates to a touchpad position.
     * @param touchPoint Pair containing the X and Y coordinates of the touched point
     *                   normalised against the size of the image they're in.
     * @param isBlueAlliance True if the bot belongs to the blue alliance (false if red).
     * @return The touchpad position the given point corresponds to.
     */
    static TouchPadPosition GetTouchPadPosition(Pair<Float, Float> touchPoint,
                                                boolean isBlueAlliance) {
        if ((isBlueAlliance && blueTouchpad1.containsPoint(touchPoint))
                || (!isBlueAlliance && redTouchpad1.containsPoint(touchPoint))) {
            return TouchPadPosition.T1;
        }

        if ((isBlueAlliance && blueTouchpad2.containsPoint(touchPoint))
                || (!isBlueAlliance && redTouchpad2.containsPoint(touchPoint))) {
            return TouchPadPosition.T2;
        }

        if ((isBlueAlliance && blueTouchpad3.containsPoint(touchPoint))
                || (!isBlueAlliance && redTouchpad3.containsPoint(touchPoint))) {
            return TouchPadPosition.T3;
        }

        return TouchPadPosition.None;
    }

    /**
     * Calculates the gear peg position that a given point on the screen corresponds to.
     * @param touchPoint Pair containing the X and Y coordinates of the touched point
     *                   normalised against the size of the image they're in.
     * @param isBlueAlliance True if the bot is from the blue alliance, false if red.
     * @return The gear peg position corresponding to the touched point.
     */
    static GearPegPosition GetGearPegPosition(Pair<Float, Float> touchPoint,
                                              boolean isBlueAlliance) {
        if ((isBlueAlliance && blueGearPeg1.containsPoint(touchPoint))
                || (!isBlueAlliance && redGearPeg1.containsPoint(touchPoint))) {
            return GearPegPosition.G1;
        }

        if ((isBlueAlliance && blueGearPeg2.containsPoint(touchPoint))
                || (!isBlueAlliance && redGearPeg2.containsPoint(touchPoint))) {
            return GearPegPosition.G2;
        }

        if ((isBlueAlliance && blueGearPeg3.containsPoint(touchPoint))
                || (!isBlueAlliance && redGearPeg3.containsPoint(touchPoint))) {
            return GearPegPosition.G3;
        }

        return GearPegPosition.None;
    }

    /**
     * Calculates the hopper that a normalised touchpoint corresponds to.
     * @param touchPoint Pair containing the X and Y coordinates of the touched point
     *                   normalised against the size of the image they're in.
     * @return The hopper the point corresponds to.
     */
    static HopperPosition GetHopperPosition(Pair<Float, Float> touchPoint) {
        if (hopper1.containsPoint(touchPoint)) {
            return HopperPosition.One;
        }

        if (hopper2.containsPoint(touchPoint)) {
            return  HopperPosition.Two;
        }

        if (hopper3.containsPoint(touchPoint)) {
            return  HopperPosition.Three;
        }

        if (hopper4.containsPoint(touchPoint)) {
            return  HopperPosition.Four;
        }

        if (hopper5.containsPoint(touchPoint)) {
            return  HopperPosition.Five;
        }

        return HopperPosition.None;
    }

    /**
     * Gets the position in feet from the boiler of a given point.
     * @param touchPoint Pair containing the X and Y coordinates of the touched point
     *                   normalised against the size of the image they're in.
     * @param isBlueAlliance True if the bot is from the blue alliance, false if red.
     * @return A string containing the feet of the format 'X: *x in feet* Y: *y in feet*'
     */
    static String GetShootingPosition(Pair<Float, Float> touchPoint, boolean isBlueAlliance) {

        // First we check if the point is inside its shooting zone
        if ((isBlueAlliance && !blueShootingZone.containsPoint(touchPoint))
                || (!isBlueAlliance && !redShootingZone.containsPoint(touchPoint))) {
            return OUT_OF_BOUNDS;
        }

        Pair<Float, Float> positionAgainstFieldDimensions = isBlueAlliance ?
                blueShootingZone.getPointNormalisedToZone(touchPoint) :
                redShootingZone.getPointNormalisedToZone(touchPoint);

        // TODO: Get real numbers
        float launchpadWidthFeet = (float) 0;
        float launchpadWidthHeight = (float) 0;

        float shootingPositionXfeet = launchpadWidthFeet * positionAgainstFieldDimensions.first;
        float shootingPositionYfeet = launchpadWidthHeight * positionAgainstFieldDimensions.second;

        return "X: " + shootingPositionXfeet + " Y: " + shootingPositionYfeet;
    }

    /**
     * Class that defines a square area in normalised touchpad coordinates
     */
    private static class TouchZone {
        private double xTop;
        private double yTop;
        private double xBottom;
        private double yBottom;

        /**
         * Constructor for a touch zone. Takes the top left and bottom right points as its creating
         * arguments - COORDINATES MUST BE NORMALISED AGAINST THE SIZE OF THE IMAGE THE POINT IS IN.
         * @param xTop x of top left
         * @param yTop y of top left
         * @param xBottom x of bottom right
         * @param yBottom y of bottom right
         */
         TouchZone (double xTop, double yTop, double xBottom, double yBottom) {
            this.xTop = xTop;
            this.yTop = yTop;
            this.xBottom = xBottom;
            this.yBottom = yBottom;
        }

        /**
         * Returns true if a given point is in the touch zone, false otherwise.
         * @param touchPoint Pair containing the X and Y coordinates of the touched point
         *                   normalised against the size of the image they're in.
         * @return True if the point is in the touch zone, false otherwise.
         */
        boolean containsPoint(Pair<Float, Float> touchPoint) {
            return (touchPoint.first > xTop)
                    && (touchPoint.first < xBottom)
                    && (touchPoint.second > yTop)
                    && (touchPoint.second < yBottom);
        }

        /**
         * Takes a point that's been normalized against the image and returns it normalized against
         * the bounds of the TouchZone. Note that the touchzone coordinates need to be normalized
         * against the same image as those of the given point.
         *
         * The main place we're going to use this is when changing a shooting position point so it's
         * normalized against the shooting zone instead of the image edges. We'd want to do this so
         * it's really easy to calculate the shooting position in feet from the boiler if we know
         * the field dimensions.
         * @param touchPoint Pair containing the X and Y coordinates of the touched point
         *                   normalised against the size of the image they're in.
         * @return Same point normalised against the touchzone bounds instead of the image bounds.
         */
        Pair<Float, Float> getPointNormalisedToZone(Pair<Float, Float> touchPoint) {
            // TODO: The math that works this out :P
            return new Pair<>((float)0,(float)0);
        }
    }
}
