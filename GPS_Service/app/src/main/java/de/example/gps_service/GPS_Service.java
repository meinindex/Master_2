package de.example.gps_service;

import android.app.Service;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.os.IBinder;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.Nullable;


@SuppressWarnings("MissingPermission")
public class GPS_Service extends Service
{

    private LocationListener listener;
    private LocationManager locationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // wenn Standort geändert habe, dann aktualisiere die Daten
                Intent i = new Intent("location_update");
                // schreibe Daten auf Bildschirm ->
              //  i.putExtra("coordinates", location.getLongitude().toString()+" "+location.getLatitude().toString());
                i.putExtra("coordinates", "23.3257987 14.0");
                sendBroadcast(i);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                //wenn gps disablled -> weise Nutzer darauf hin, dass wir das benötigen
                    Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        //noinspection MissingPermission
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,0,listener);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(locationManager != null)
        {
            locationManager.removeUpdates(listener);
        }
    }

}
