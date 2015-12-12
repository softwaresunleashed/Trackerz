package com.unleashed.android.trackerz.Maps;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unleashed.android.trackerz.R;
import com.unleashed.android.trackerz.Config.AppConfig;
import com.unleashed.android.trackerz.LocationTracker.GpsLocationTracker;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FloatingActionButton trackButton;
    private SupportMapFragment mapFragment;
    private boolean isMapReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        setupActionBar();


        trackButton = (FloatingActionButton) findViewById(R.id.trackMeButton);
        trackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMapReady == true){
                    updateMyCurrentLocation();
                }
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        android.app.ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        isMapReady = true;


        updateMyCurrentLocation();


    }

    private void updateMyCurrentLocation() {

        try{

            GpsLocationTracker mGpsLocationTracker = new GpsLocationTracker(MapsActivity.this);

            /**
             * Set GPS Location fetched address
             */
            if (mGpsLocationTracker.canGetLocation())
            {
                double latitude = mGpsLocationTracker.getLatitude();
                double longitude = mGpsLocationTracker.getLongitude();

                LatLng myLocationLatLng = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(myLocationLatLng).title("My Location"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocationLatLng, AppConfig.ZoomLevel.ZOOM_MEDIUM));
            }
            else
            {
                mGpsLocationTracker.showSettingsAlert();
            }

        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Error Retrieving Location. Try Again.", Toast.LENGTH_SHORT).show();
        }

    }


}
