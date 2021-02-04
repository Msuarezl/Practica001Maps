package com.example.pruba001maps;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Polyline polyline = null;
    Polygon polygon = null;
    List<LatLng> latLngList = new ArrayList<>();
    List<Marker> markerList = new ArrayList<>();
RadioButton rbLinea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        rbLinea = findViewById(R.id.idlienar);
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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions().position(latLng);
                Marker marker = mMap.addMarker(markerOptions);
                latLngList.add(latLng);
                markerList.add(marker);
                if(rbLinea.isChecked()){
                    if(polyline!=null) polyline.remove();
                    if(polygon!=null) polygon.remove();

                    PolylineOptions polylineOptions = new PolylineOptions().addAll(latLngList).clickable(true);
                    polyline = mMap.addPolyline(polylineOptions);
                }



            }
        });
    }
    public void limpiar(View view){
        if(polyline!=null) polyline.remove();
        if(polygon!=null) polygon.remove();
        for (Marker marker:markerList) marker.remove();
        markerList.clear();
        latLngList.clear();
    }
    public void dibujarpoligono(View view){
        try {
            if(polyline!=null) polyline.remove();
            if(polygon!=null) polygon.remove();

            PolygonOptions polygonOptions = new PolygonOptions().addAll(latLngList).clickable(true);
            polygon = mMap.addPolygon(polygonOptions);
        } catch (Exception e){

        }


    }




    public void animarcamara(View view){
        LatLng Quevedo = new LatLng(-1.0125996, -79.4539186);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Quevedo,15));

    }
    public void movercamara(View view){
        LatLng Quevedo = new LatLng(-1.0125996, -79.4539186);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Quevedo,15));

    }
    public void mapaNormal(View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }
    public void mapaHybrido(View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }
    public void mapaTerrain(View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }
    public void mapaSatelital(View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }

}