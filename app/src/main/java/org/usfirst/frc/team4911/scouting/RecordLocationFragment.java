package org.usfirst.frc.team4911.scouting;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Dialog fragment subclass which displays a given field and records a normalised location
 * where it was pressed.
 * Use the {@link RecordLocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordLocationFragment extends DialogFragment {

    // The keys we use to store argument parameters
    private static final String ARG_PARAM1 = "argParam1";

    // The variable we store the ID in once we've gone through the whole argument passing process.
    private int mapImageResourceId;

    // Object for interacting with the image of the map shown on screen.
    private ImageView map;

    // The touch point coordinates - I'm guessing these will very shortly be replaced
    // with methods that calculate the zone.
    private Pair<Float, Float> normalizedTouchPoint;

    // Array which stores all the LocationMapType/AllianceType resource ID values.
    private final int[][] resourceIdArray;

    public RecordLocationFragment() {
        // Putting this in the constructor because IDK where else it should go.
        resourceIdArray = createResourceIdArray();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param allianceType The allianceType to display a map for as a {@link AllianceType}.
     * @param locationMapType The type of map to display as a {@link LocationMapType}.
     *
     * @return A new instance of fragment RecordLocationFragment.
     */
    public static RecordLocationFragment newInstance(AllianceType allianceType,
                                                     LocationMapType locationMapType) {
        RecordLocationFragment fragment = new RecordLocationFragment();
        int resourceId =
                fragment.resourceIdArray[allianceType.getValue()][locationMapType.getValue()];

        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, resourceId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mapImageResourceId = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_location, container, false);

        map = (ImageView) view.findViewById(R.id.fragment_map);
        map.setImageBitmap(getEditableFieldCanvas());
        map.setOnTouchListener(handleTouch);

        Button close = (Button) view.findViewById(R.id.btn_fragment_close);
        close.setOnClickListener(closeWindow);

        return view;
    }

    /**
     * OnTouchListener for the map which draws a white event position marker on the screen
     * when the screen is touched.
     */
    private View.OnTouchListener handleTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_UP) {
                drawMapWithMarker(event.getX(), event.getY());
                return true;
            }

            return true;
        }
    };

    /**
     * OnClickListener for the button that records the location and closes the dialog.
     */
    private View.OnClickListener closeWindow = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    /**
     * Fills the resource ID array with the correct resources for each map type.
     */
    private int[][] createResourceIdArray() {
        int[][] idArray = new int[AllianceType.values().length][LocationMapType.values().length];

        // First the blue alliance
        idArray[AllianceType.BLUE.getValue()][LocationMapType.AIRSHIP.getValue()] =
                R.drawable.airship_blue;
        idArray[AllianceType.BLUE.getValue()][LocationMapType.SHOOTING_AREA.getValue()] =
                R.drawable.shootingzone_blue;
        idArray[AllianceType.BLUE.getValue()][LocationMapType.WHOLEFIELD.getValue()] =
                R.drawable.steamworks_field;

        // Then the red
        idArray[AllianceType.RED.getValue()][LocationMapType.AIRSHIP.getValue()] =
                R.drawable.airship_red;
        idArray[AllianceType.RED.getValue()][LocationMapType.SHOOTING_AREA.getValue()] =
                R.drawable.shootingzone_blue;
        idArray[AllianceType.RED.getValue()][LocationMapType.WHOLEFIELD.getValue()] =
                R.drawable.steamworks_field;

        return idArray;
    }

    /**
     * Sets the value of the pair that stored the coordinates of the touch point onto an axis
     * between 0 and 1 where 0 is the
     * top left corner of the field and 1 is the bottom right.
     * @param touchX x coordinate of the touched point.
     * @param touchY y coordinate of the touch point.
     * point.
     */
    private void setNormalizedTouchPoint(float touchX, float touchY) {

        float mapBottomRightX = map.getWidth();
        float mapBottomRightY = map.getHeight();
        normalizedTouchPoint = new Pair<>(touchX/mapBottomRightX, touchY/mapBottomRightY);
    }

    /**
     * Gets the normalised touch point.
     * @return A pair contianing the normalised X and Y coordinates of the touched point.
     * Null if they're not defined.
     */
    public Pair<Float, Float> getNormalizedTouchPoint() {

        return normalizedTouchPoint;
    }

    /**
     * Draws the map with the marker.
     * @param scrX x coordinate of said marker.
     * @param scrY y coordinate of said marker.
     */
    private void drawMapWithMarker(float scrX,float scrY){

        Bitmap image = getEditableFieldCanvas();
        Canvas canvas = new Canvas(image);
        Drawable d = getActivity().getDrawable(R.drawable.marker_shape);
        d.setBounds(getDrawableBounds(scrX, scrY, d));
        d.draw(canvas);
        map.setImageBitmap(image);
    }

    /**
     * Gets the field canvas as an editable bitmap.
     * @return FIeld canvas as an editable bitmap.
     */
    private Bitmap getEditableFieldCanvas(){

        Bitmap originalBitmap;
        originalBitmap = BitmapFactory.decodeResource(getResources(), mapImageResourceId);
        return originalBitmap.copy(Bitmap.Config.RGB_565, true);
    }

    /**
     * This is the method we need so that the touch point marker draws itself centered at
     * the touch point like you want it to instead of randomly off the the side.
     * @param centerX The X coordinate of the point you want to draw the shape around.
     * @param centerY The Y coordinate of the point you want to draw the shape around.
     * @param d A drawable representing the shape you want to draw.
     * @return A rectangle containing the bounds of the shape such that it will draw itself with the
     * given x and y coordinates at the center of said shape.
     */
    private Rect getDrawableBounds(float centerX, float centerY, Drawable d){

        int halfWidth = d.getIntrinsicWidth()/2;
        int halfHeight = d.getIntrinsicHeight()/2;

        int centerXasInt = (int) centerX;
        int centerYAsInt = (int) centerY;

        int left = centerXasInt - halfWidth;
        int top = centerYAsInt - halfHeight;
        int right = centerXasInt + halfWidth;
        int bottom = centerYAsInt + halfHeight;

        return new Rect(left, top, right, bottom);
    }
}