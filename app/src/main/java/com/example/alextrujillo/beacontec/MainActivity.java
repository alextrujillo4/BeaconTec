package com.example.alextrujillo.beacontec;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, AdapterView.OnItemSelectedListener{

    private GoogleMap mMap;
    private Spinner spinnerPlaces;
    private  PlaceSpinnerAdapter sAdapter;
    private String lugar;

    private List<String> placesName = new ArrayList<String>();
    private List<String> placeDistance = new ArrayList<String>();
    private List<Drawable> flags = new ArrayList<Drawable>();   // {R.mipmap.ic_arrow_drop_down_circle_black_24dp, 0, 0, 0, 0};


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


        spinnerPlaces  = (Spinner) findViewById(R.id.spinner_places);
        spinnerPlaces.setOnItemSelectedListener(this);
        getSpinnerPlacesData();
        sAdapter = new PlaceSpinnerAdapter(getApplicationContext(), flags, placesName, placeDistance);
        spinnerPlaces.setAdapter(sAdapter);


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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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
}

