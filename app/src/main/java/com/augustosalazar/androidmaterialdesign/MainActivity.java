package com.augustosalazar.androidmaterialdesign;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private boolean menuRefreshEnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main_appbar);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer,(DrawerLayout)findViewById(R.id.drawer_layout),toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if ( id == R.id.navigate) {
            startActivity(new Intent(this,SubActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
