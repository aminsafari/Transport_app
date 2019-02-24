package com.example.amin08.transport;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import app.*;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    public static final int REQUEST_CODE = 112;



    LatLng shahriar = new LatLng(35.705506, 50.985904);


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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



    private boolean checkPermission() {

        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M) {
            int result = ContextCompat.checkSelfPermission(Application.getContext() , Manifest.permission.ACCESS_FINE_LOCATION );
            if (result == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
            else {

                ActivityCompat.requestPermissions(this , new String[] {Manifest.permission.ACCESS_FINE_LOCATION} , REQUEST_CODE);
            }
        }
        else {
            return true;
        }
        return false;

    }






    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();


            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            mMap.addMarker(new MarkerOptions().position(shahriar).title("موبایل کاوش"));
            // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(shahriar, 15));


            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    if (marker.getTitle().equals("موبایل کاوش")) {

                        try {

                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + shahriar.latitude + "," + shahriar.longitude + " ( موبایل کاوش )"));
                            startActivity(intent);
                        } catch (Exception e) {

                            app.l(e.toString());

                        }
                    }

                    return false;
                }
            });


        }

    }



}
