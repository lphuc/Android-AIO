package com.example.phucengineer.fragmentsample;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * this fragment is added as a static part in the activity and will be displayed for the entire life cycle of the activity
 * to add a fragment statically to the activity, use a <fragment> tag inside the layout of the activity
 */
public class BFragment extends BaseFragment {
    private RadioGroup radioGroup;
    private static final int YES = 1;
    private static final int NO = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for this fragment, define as final because it won't change
        // final -> anonymous inner class can access
        final View fragRootView = inflater.inflate(R.layout.fragment_b, container, false);
        radioGroup = fragRootView.findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioBtn = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioBtn);
                switch (index) {
                    case YES:
                        Toast.makeText(getContext(), "YES", Toast.LENGTH_SHORT).show();
                        break;
                    case NO:
                        Toast.makeText(getContext(), "NO", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        // returns the View for the fragment's UI.
        return fragRootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Toast.makeText(context, "B-Fragment -> onAttach()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity(), "B-Fragment: onViewCreated() \n "
                + "view id -> " + view.getId(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "B-Fragment -> onStart()", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "B-Fragment -> onResume()", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getActivity(), "B-Fragment -> onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(), "B-Fragment -> onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(), "B-Fragment -> onDestroyView()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(), "B-Fragment -> onDestroy()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(getActivity(), "B-Fragment -> onDetach()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void clearInstance() {
    }
}
