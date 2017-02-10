package org.usfirst.frc.team4911.scouting.matchscouting;

/**
 * Created by Anne_ on 1/22/2017.
 *
 * I found this on StackOverflow
 * https://stackoverflow.com/questions/21228721/how-to-replace-a-fragment-on-button-click-of-that-fragment
 */

import android.support.v4.app.Fragment;

public interface ButtonFragmentChangeListener
{
    /**
     * Helps with switching between match phase button fragments.
     * @param fragment The fragment to replace the current fragment with.
     */
    void replaceButtonFragment(Fragment fragment);
}