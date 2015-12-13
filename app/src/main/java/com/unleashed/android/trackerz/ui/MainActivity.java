package com.unleashed.android.trackerz.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.unleashed.android.trackerz.API.gitapi;
import com.unleashed.android.trackerz.LoginModule.LoginActivity;
import com.unleashed.android.trackerz.Maps.MapsActivity;
import com.unleashed.android.trackerz.R;
import com.unleashed.android.trackerz.Settings.SettingsActivity;
import com.unleashed.android.trackerz.model.gitmodel;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private long back_pressed = 0;   // Counter to keep track of time elapsed between previous back button pressed.

    Button click;
    TextView tv;
    EditText edit_user;
    ProgressBar pbar;
    String API = "https://api.github.com";                         //BASE URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /////// Retrofit ////////

        click = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.tv);
        edit_user = (EditText) findViewById(R.id.edit);
        pbar = (ProgressBar) findViewById(R.id.pb);
        pbar.setVisibility(View.INVISIBLE);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edit_user.getText().toString();
                pbar.setVisibility(View.VISIBLE);

                //Retrofit section start from here...
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(API).build();                                        //create an adapter for retrofit with base url

                gitapi git = restAdapter.create(gitapi.class);                            //creating a service for adapter with our GET class

                //Now ,we need to call for response
                //Retrofit using gson for JSON-POJO conversion

                git.getFeed(user, new Callback<gitmodel>() {
                    @Override
                    public void success(gitmodel gitmodel, Response response) {
                        //we get json object from github server to our POJO or model class

                        tv.setText("Github Name :" + gitmodel.getName() + "\nWebsite :" + gitmodel.getBlog() + "\nCompany Name :" + gitmodel.getCompany());

                        pbar.setVisibility(View.INVISIBLE);                               //disable progressbar
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        tv.setText(error.getMessage());
                        pbar.setVisibility(View.INVISIBLE);                               //disable progressbar
                    }
                });
            }
        });


        /////////////////////////

        // Main Activity FAB button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Main Activity Navigation Bar (Left side)
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Displaying Login Activity
        View hView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView tv_log = (TextView)hView.findViewById(R.id.tv_loginBtn);
        tv_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the login screen
                openLoginActivity();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            // Confirm from user before exiting app.
            // (Press Back Button twice to exit)
            if (back_pressed + 1000 > System.currentTimeMillis()){
                super.onBackPressed();
            }
            else{
                Toast.makeText(getBaseContext(),
                        "Press once again to exit!", Toast.LENGTH_SHORT)
                        .show();
            }
            back_pressed = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            openSettingsActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id){

/* Main Menu */
            case R.id.nav_trackme:
                openMapsActivity();
                break;

            case R.id.nav_history_summary:
                // TODO : Sudhanshu : Add code to check if logged in and get history of all added devices / cars
                break;

            case R.id.nav_notification:
                break;

            case R.id.nav_map_view:
                break;

/* Settings Menu */
            case R.id.nav_settings:
                openSettingsActivity();
                break;
/* Misc Menu */
            case R.id.nav_customer_support:
                break;

            case R.id.nav_driver_details:
                break;

            case R.id.nav_app_ver:
                break;

/* Communicate Menu */
            case R.id.nav_share:
                break;

            case R.id.nav_send:
                break;

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openMapsActivity() {
        Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
        // myIntent.putExtra("key", value); //Optional parameters
        startActivity(myIntent);
    }

    private void openLoginActivity() {
        Intent myLoginIntent = new Intent(MainActivity.this, LoginActivity.class);
        // myIntent.putExtra("key", value); //Optional parameters
        startActivity(myLoginIntent);
    }

    private void openSettingsActivity() {
        Intent SettingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(SettingsIntent);
    }
}
