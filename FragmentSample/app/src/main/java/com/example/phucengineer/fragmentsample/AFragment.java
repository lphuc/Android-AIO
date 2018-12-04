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
import android.widget.Button;
import android.widget.Toast;

import static com.example.phucengineer.fragmentsample.Consts.BUTTON_LABEL;


/**
 * This fragment will be added to activity dynamically, so that the activity can add, replace or remove it.
 * to add a fragment dynamically, specify a ViewGroup inside the layout of the activity such as FrameLayout
 */
public class AFragment extends BaseFragment implements View.OnClickListener, FragmentType.TypeA {
    private Button btnSubmit;
    private View view;
    private final String TAG = "AFragment-";
    private MainCallBackListener listener;
    private static AFragment instance;

    @SuppressLint("ValidFragment")
    private AFragment() {

    }

    /**
     * create a new instance of fragment in activity
     *
     * @param buttonLabel -> the button label which is included in a Bundle when create new fragment from activity
     */
    public static AFragment newInstance(String buttonLabel) {
        if (instance == null) {
            instance = new AFragment();
            Bundle arguments = new Bundle();
            arguments.putString(BUTTON_LABEL, buttonLabel);
            instance.setArguments(arguments);
        }
        return instance;
    }


    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Toast.makeText(getContext(), TAG + "onAttachFragment()", Toast.LENGTH_SHORT).show();
    }


    /**
     * capture the host activity interface implementation here
     * the onAttact() is call as soon as the fragment is associated with activity
     * The code makes sure that the host Activity has implemented the callback interface. unless, throws an exception.
     *
     * @param context -> the context object of host activity
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Toast.makeText(context, TAG + "onAttach()", Toast.LENGTH_SHORT).show();

        if (context instanceof MainActivity) {
            listener = (MainCallBackListener) context;
        } else {
            Toast.makeText(getContext(), TAG + "onAttach() -> couldn't cast instance!", Toast.LENGTH_SHORT).show();
            throw new ClassCastException("Context -> " + context.toString() + "\n"
                    + "Message -> " + getResources().getString(R.string.exception_message));
        }

    }


    /**
     * @param inflater
     * @param container
     * @param savedInstanceState -> get the button label passed to the Bundle in newInstance()
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getContext(), TAG + "onViewCreated()", Toast.LENGTH_SHORT).show();

        view = inflater.inflate(R.layout.fragment_a, container, false);
        // Inflate the layout for this fragment
        btnSubmit = view.findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);

        if (getArguments().containsKey(Consts.BUTTON_LABEL)) {
            btnSubmit.setText(getArguments().getString(BUTTON_LABEL));
        }
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), TAG + "onViewCreated()", Toast.LENGTH_SHORT).show();
        listener.onFragmentViewCreated(TAG);
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getContext(), TAG + "onActivityCreated()", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Toast.makeText(getContext(), TAG + "onViewStateRestored()", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getContext(), TAG + "onStart()", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), TAG + "onResume()", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(getContext(), TAG + "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getContext(), TAG + "onPause()", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getContext(), TAG + "onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getContext(), TAG + "onDestroyView()", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getContext(), TAG + "onDestroy()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(getContext(), TAG + "onDetach()", Toast.LENGTH_SHORT).show();
    }


    /**
     * OnClick callback
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                Toast.makeText(getActivity(), "SUBMITTED", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public static AFragment getInstance() {
        return instance;
    }

    public void clearInstance() {
        instance = null;
    }
}
