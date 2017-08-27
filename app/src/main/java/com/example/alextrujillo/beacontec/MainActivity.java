package com.example.alextrujillo.beacontec;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback,
        AdapterView.OnItemSelectedListener,
        GoogleMap.OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final LatLng LABESTRUCTURAS = new LatLng(25.649232, -100.288519);
    private static final LatLng LABQUIMICAGENERAL = new LatLng(25.6519, -100.2905);
    private static final LatLng LABROBOTICA = new LatLng(25.6505, -100.2909);
    private static final LatLng LABMICROELECTRONICA = new LatLng(25.6493, -100.2887);
    private static final LatLng LABMECATRONICA = new LatLng(25.6497, -100.2881);
    private static final LatLng LASISTEMASDECONTROL = new LatLng(25.6497, -100.2882);
    private static final LatLng LABPROYECTOSMECATRONICA = new LatLng(25.6497, -100.2883);


    private Spinner spinnerPlaces;
    private PlaceSpinnerAdapter sAdapter;
    private String lugar;
    private Marker mLabRobotica;
    private boolean mLocationPermissionGranted;

    private List<String> placesName = new ArrayList<String>();
    private List<String> placeDistance = new ArrayList<String>();
    private List<Drawable> flags = new ArrayList<Drawable>();   // {R.mipmap.ic_arrow_drop_down_circle_black_24dp, 0, 0, 0, 0};

    private boolean mPermissionDenied = false;
    private LatLngBounds TECMTY = new LatLngBounds(
            new LatLng(25.647980, -100.289998), new LatLng(25.653551, -100.289803));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        flags.add(getResources().getDrawable(R.drawable.ic_arrow_drop_down_circle_black_24dp));
        placesName.add("Seleccionar Lugar:");
        placeDistance.add("");
        spinnerPlaces = (Spinner) findViewById(R.id.spinner_places);
        spinnerPlaces.setOnItemSelectedListener(this);
        getSpinnerPlacesData();
        sAdapter = new PlaceSpinnerAdapter(getApplicationContext(), flags, placesName, placeDistance);
        spinnerPlaces.setAdapter(sAdapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        addMarkersToMap();
        //EVENTO DE ALERTA DE PERMISO


        // Add a marker in Sydney and move the camera
        LatLng tecMty = new LatLng(25.647980, -100.289998);
        //mMap.addMarker(new MarkerOptions().position(tecMty).title("Marker in Tec"));

        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(LABESTRUCTURAS)
                .include(LABQUIMICAGENERAL)
                .include(LABMICROELECTRONICA)
                .include(LABMECATRONICA)
                .include(LASISTEMASDECONTROL)
                .include(LABPROYECTOSMECATRONICA)
                .include(LABROBOTICA)
                .build();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TECMTY.getCenter(), 17));
        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();

//=======

        mMap = googleMap;
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setPadding(0,300,0,0);
        enableMyLocation();

    }


    public void getSpinnerPlacesData() {
        String lugar = null;
        Drawable res = null;
        for (int cont = 1; cont <= 2; cont++) {
            switch (cont) {
                case 1:
                    lugar = "Laboratorio de quimica general";
                    res = getResources().getDrawable(R.drawable.ic_local_library_black_24dp);
                    break;
                case 2:
                    lugar = "Laboratorio de Robotica";
                    res = getResources().getDrawable(R.drawable.ic_local_library_black_24dp);
                    break;

            }

            placesName.add(lugar);
            placeDistance.add("50 Mts");
            flags.add(res);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void addMarkersToMap() {
        // Uses a colored icon.
        mLabRobotica = mMap.addMarker(new MarkerOptions()
                .position(LABESTRUCTURAS)
                .title("Laboratorio Estructuras")
                .snippet("Distancia: 50 m")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mLabRobotica = mMap.addMarker(new MarkerOptions()
                .position(LABQUIMICAGENERAL)
                .title("Laboratorio Química")
                .snippet("Distancia: 50 m")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mLabRobotica = mMap.addMarker(new MarkerOptions()
                .position(LABMICROELECTRONICA)
                .title("Laboratorio Electrónica")
                .snippet("Distancia: 50 m")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mLabRobotica = mMap.addMarker(new MarkerOptions()
                .position(LABMECATRONICA)
                .title("Laboratorio Mecatrónica")
                .snippet("Distancia: 50 m")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mLabRobotica = mMap.addMarker(new MarkerOptions()
                .position(LASISTEMASDECONTROL)
                .title("Laboratorio Sistemas de Control")
                .snippet("Distancia: 50 m")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mLabRobotica = mMap.addMarker(new MarkerOptions()
                .position(LABPROYECTOSMECATRONICA)
                .title("Laboratorio Proyectos Mecatronica")
                .snippet("Distancia: 50 m")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mLabRobotica = mMap.addMarker(new MarkerOptions()
                .position(LABROBOTICA)
                .title("Laboratorio Robótica")
                .snippet("Distancia: 50 m")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

    }





    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

}


