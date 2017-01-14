package com.techacademy.demomaps;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity {



    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Marker marker;  // Wo sind wir
    double lat = 0.0;
    double lng = 0.0;
    Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    public void onSearch(View view)
    {
        EditText location_tf = (EditText)findViewById(R.id.TFaddress);
        String location = location_tf.getText().toString();

        System.out.print("---------------------------------------------------");
        System.out.print("location: " + location);
        System.out.print("--------------------------------------------");

        List<Address> addressList = null;
        if(location != null || !location.equals(""))
        {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location , 1);


            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude() , address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(location));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        }
    }

    public void onZoom(View view)
    {
        if(view.getId() == R.id.Bzoomin)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if(view.getId() == R.id.Bzoomout)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }

    public void changeType(View view)
    {
        if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    private void setUpMapIfNeeded() {
        // Nullabfrage, ob die Karte bereits gestartet wurde

        if (mMap == null) {
            // Versuche Karte von SupportMapFragment zu erhalten
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Frage, ob Karte erhalten wurde
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {                   // Startpunkt
        meinStandort();

      /*  mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));*/
        mMap.setMyLocationEnabled(true);
    }

    public void agregarMarker  (double lat, double lng){

        System.out.print("11111111111111111111111111111111111111111111111111111");

        //List<Address> geoResult = findGeocoder(lat, lng);

        System.out.print("++++++++++++++++++++++++++++++++++++++++++++"+lat+ "," + lng);
/*
                //"**********************"+geoResult);
        if (geoResult != null)
        {
            List<String> geoStringResult = new ArrayList<String>();

            Address thisAddress = geoResult.get(0);

            String stringThisAddress = "";


            stringThisAddress +=
                    "Adresse:" + "\n"
                            + thisAddress.getAddressLine(0) + "\n"                      // Straße & Hausnummer
                            + thisAddress.getPostalCode()                               // Postleitzahl
                            + " " + thisAddress.getLocality() + "\n"                    // Stadt
                            + thisAddress.getAdminArea() + "\n"                         // Bundesstaat
                            + thisAddress.getCountryName();

            System.out.print(stringThisAddress);
        }*/
        System.out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        LatLng koordinaten = new LatLng(lat, lng);
        CameraUpdate miStand = CameraUpdateFactory.newLatLngZoom(koordinaten, 16);
        if (marker != null) marker.remove();
        marker = mMap.addMarker(new MarkerOptions()
                .position(koordinaten)
                /*.title(stringThisAddress)*/
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.animateCamera(miStand);
    }

    private void aktualisiereStandort(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            System.out.print("3333333333333333333333aktualisiere Standort 333333333333333333333333");
            agregarMarker(lat, lng);
        }
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            aktualisiereStandort(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private void meinStandort() {
      /*  if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }*/
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        aktualisiereStandort(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, locListener);

    }

    private List<Address> findGeocoder(Double lat, Double lon) {
        System.out.print("22222222222222222222222222222222222222222 ");

        final  int maxResults = 5;
        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(lat, lon, maxResults);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        return addresses;
    }

}
