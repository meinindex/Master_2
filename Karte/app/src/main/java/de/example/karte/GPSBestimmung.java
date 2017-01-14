/*package de.example.karte;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;


@SuppressWarnings("MissingPermission")
public class GPSBestimmung extends Service implements LocationListener {

    private final Context c;
    // GPS aktiv?
    private boolean GPSaktiv = false;
    //Lat && Lng
    Location position;
    // Positionsbestimmung
    protected LocationManager locationManager;




    public GPSBestimmung(Context context)
    {
        this.c = context;
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        position = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    // Breitengrad als Double zurückgeben
    public double getBrteitengrad()
    {
        if(position != null)
        {
            return position.getLatitude();
        }
        else
            return 0;
    }

    // Längengrad als Double zurückgeben
    public double getLaengengrad()
    {
        if(position != null)
        {
            return position.getLongitude();
        }
        else
            return 0;
    }

    // GPS an?
    public boolean istGPSaktiv()
    {
        return this.GPSaktiv;
    }

    public void setPosition()
    {
        try {
            locationManager = (LocationManager) c.getSystemService(LOCATION_SERVICE);

            GPSaktiv = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if (GPSaktiv) {
                // speichere Daten in Var position
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 10, this);
                if (locationManager != null) {
                    position = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Beendet GPS Bestimmung
    public void stopGPSposition()
    {
        if(locationManager != null)
        {
            locationManager.removeUpdates(GPSBestimmung.this);
        }
    }


}
*/