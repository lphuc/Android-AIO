package com.example.phucengineer.fragmentsample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/*
 * Created by lhphuc on 9/19/2018.
 */
public class Utils {

    /**
     * method to create and display a new fragment inside the activityA
     * addToBackStack -> managed by activity, allow user to return to previous AFragment state by pressing back button
     * commit -> for the transition to take effect
     *
     * @param fragment
     * @param containerId
     * @param tag
     */
    public static void addNewFragment(AppCompatActivity activity, Fragment fragment, int containerId, String tag) {
        //get the FragmentManager to start a transition
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        //Add new AFragment to activity
        transaction.add(containerId, fragment).addToBackStack(tag);

        // commit for the action to be started
        transaction.commit();
    }


    /**
     * method to remove a fragment from a activity
     * make sure the fragment is displayed (!null) before calling this method
     *
     * @param fragment
     */
    public static void removeFragment(AppCompatActivity activity, Fragment fragment) {
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment).commit();
    }


    /**
     * method to replace current fragment with a new fragment to the view container
     *
     * @param activity        -> the host activity
     * @param containerViewId -> the id of the view which hold the fragment inside the layout of activity
     * @param fragment        -> the new fragment to be placed on the view container
     */
    public static void replaceFragment(AppCompatActivity activity, int containerViewId, Fragment fragment) {
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(containerViewId, fragment, null).commit();
    }


    public static void clearFragmentInstance(BaseFragment baseFragment) {

        if (baseFragment instanceof FragmentType.TypeB) {
            CFragment.getInstance().clearInstance();
        } else if (baseFragment instanceof FragmentType.TypeA) {
            AFragment.getInstance().clearInstance();
        }

    }

}
