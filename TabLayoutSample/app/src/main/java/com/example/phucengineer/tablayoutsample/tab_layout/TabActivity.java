package com.example.phucengineer.tablayoutsample.tab_layout;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.phucengineer.tablayoutsample.R;


public class TabActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.ic_dummy_avatar,
            R.drawable.ic_dummy_avatar,
            R.drawable.ic_dummy_avatar
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        toolbar = findViewById(R.id.toolbar);
         /*
          enable toolbar by calling setSupportActionBar() and passing the toolbar object
         */

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // setup view pager to allow slide between fragments
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // setup tab layout and attach it with the view pager
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    /**
     * add 3 fragments to the view pager
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment(), "FIRST");
        adapter.addFragment(new SecondFragment(), "SECOND");
        adapter.addFragment(new ThirdFragment(), "THIRD");
        viewPager.setAdapter(adapter);
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
}
