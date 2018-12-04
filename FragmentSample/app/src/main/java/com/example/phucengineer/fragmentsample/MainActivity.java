package com.example.phucengineer.fragmentsample;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainCallBackListener {
    private Button btnFragmentA, btnFragmentB, btnFragmentC;
    private boolean isFragmentDisplayed = false;
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Activity: onCreate()", Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_main);
        btnFragmentA = findViewById(R.id.btn_fragment_a);
        btnFragmentB = findViewById(R.id.btn_fragment_b);
        btnFragmentC = findViewById(R.id.btn_fragment_c);
        btnFragmentA.setOnClickListener(this);
        btnFragmentB.setOnClickListener(this);
        btnFragmentC.setOnClickListener(this);

        startDialogFragment();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Activity: onStart()", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Activity: onPause()", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Activity: onStop()", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Activity: onDestroy()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Activity: onResume()", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Activity: onRestart()", Toast.LENGTH_SHORT).show();
    }


    /**
     * onClick callback
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fragment_a:
                if (AFragment.getInstance() == null) {
                    Utils.addNewFragment(this, AFragment.newInstance("OK"), R.id.fragment_container_1, AFragment.class.getSimpleName());
                    Settings.getInstance(this).setFragmentVisibility("A_FRAGMENT", true);
                }

                break;
            case R.id.btn_fragment_b:
                if (AFragment.getInstance() != null) {
                    Utils.removeFragment(this, AFragment.getInstance());
                    AFragment.getInstance().clearInstance();
                } else {
                    Toast.makeText(this, "Added fragment C", Toast.LENGTH_SHORT).show();
                    Utils.addNewFragment(this, CFragment.newInstance(), R.id.fragment_container_1, CFragment.class.getSimpleName());
                }
                break;
            case R.id.btn_fragment_c:
                if (CFragment.getInstance() == null) {
                    Utils.replaceFragment(this, R.id.fragment_container_1, CFragment.newInstance());
                } else {
                    Utils.removeFragment(this, CFragment.getInstance());
                    Utils.clearFragmentInstance(CFragment.getInstance());
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Activity: onBackPressed()", Toast.LENGTH_SHORT).show();

    }

    /**
     * @param fragmentName name of the fragment which has the view just created
     */
    @Override
    public void onFragmentViewCreated(String fragmentName) {
        Toast.makeText(this, "Activity: fragment view for -" + fragmentName + " has been created!", Toast.LENGTH_SHORT).show();

    }

    private void startDialogFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        android.app.Fragment previous = getFragmentManager().findFragmentByTag("dialog");
        if (previous != null) {
            transaction.remove(previous);
        }
        transaction.addToBackStack(null);
        DialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(transaction, "dialog");

    }

}
