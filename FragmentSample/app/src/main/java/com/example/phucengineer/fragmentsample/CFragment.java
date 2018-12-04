package com.example.phucengineer.fragmentsample;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * This fragment will be added dynamically to activity
 */
public class CFragment extends BaseFragment implements FragmentType.TypeC {
    private static CFragment instance;

    @SuppressLint("ValidFragment")
    private CFragment() {
    }

    public static CFragment newInstance() {

        if (instance == null) {
            instance = new CFragment();
        }
        return getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_c, container, false);
    }

    public static CFragment getInstance() {
        return instance;
    }

    public void clearInstance() {
        instance = null;
    }
}
