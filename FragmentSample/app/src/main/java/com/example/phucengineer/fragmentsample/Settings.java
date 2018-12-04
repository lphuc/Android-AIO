package com.example.phucengineer.fragmentsample;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;

/*
 * Created by lhphuc on 9/19/2018.
 */
public class Settings {

    private static Settings instance;
    private static final String MY_PREFS = "AIO";
    private static SharedPreferences setting;
    private static SharedPreferences.Editor editor;

    private Settings() {
    }

    /**
     * singleton class
     *
     * @return
     */
    public static Settings getInstance(Context context) {
        if (instance == null) {
            instance = new Settings();
            setting = context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
        }
        return instance;
    }

    public void setFragmentVisibility(String keyToStore, boolean isVisbile) {
        editor = setting.edit();
        editor.putBoolean(keyToStore, isVisbile);
        editor.apply();
    }

    public boolean isFragmentVisible(String keyToGet) {
        return setting.getBoolean(keyToGet, false);
    }


}
