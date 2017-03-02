package org.usfirst.frc.team4911.scouting;

/**
 * Created by Anne_ on 3/2/2017.
 *
 * Custom view pager copied from
 */

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Custom view pager borrowed from here so we could disable tab swiping until the scouting data
 * has been initialised:
 * https://www.quora.com/How-do-i-restrict-swiping-between-2-tabs-in-android
 */
public class CustomViewPager extends ViewPager {

    private boolean enabled;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        // We want swiping to be disabled until start match has been pressed and the matchdata
        // is initialised.
        this.enabled = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.enabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.enabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
