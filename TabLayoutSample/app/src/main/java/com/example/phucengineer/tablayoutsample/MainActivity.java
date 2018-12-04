package com.example.phucengineer.tablayoutsample;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.phucengineer.tablayoutsample.drawer_layout.DrawerFragment;
import com.example.phucengineer.tablayoutsample.tab_layout.TabActivity;

public class MainActivity extends AppCompatActivity implements DrawerFragment.FragmentDrawerListener, View.OnClickListener {
    private Toolbar toolbar;
    private DrawerFragment drawerFragment;
    private Button btnStartTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartTab = findViewById(R.id.btn_start);
        btnStartTab.setOnClickListener(this);
        toolbar = findViewById(R.id.toolbar);

        /*
          enable toolbar by calling setSupportActionBar() and passing the toolbar object
         */

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*init drawer fragment to the activity*/
        drawerFragment = (DrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        drawerFragment.setDrawerListener(this);
    }


    /**
     * Override this function to inflate the menu, this will add menu items to the action bar
     *
     * @param menu the menu to be inflated
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * This function will handle click event on menu item from the action bar
     *
     * @param item the single item from the menu_main.xml
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                Toast.makeText(this, "Open Search view!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Open Settings view!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        // TODO: 9/25/2018 Action when an item from drawer fragment is selected
        Toast.makeText(this, "clicked on " + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                startActivity(new Intent(this, TabActivity.class));
        }
    }
}
