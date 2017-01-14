package de.example.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    private static final int MY_PERMISSION_REQUEST_LOCATION=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);

        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
                {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.ACCESS_COARSE_LOCATION))
                    {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION );
                    }
                    else{
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION );
                    }
                }
                else
                {
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    try
                    {
                        textView.setText(hereLocation(location.getLatitude(), location.getLongitude()));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_LOCATION:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED )
                {
                    if(ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        try
                        {
                            textView.setText(hereLocation(location.getLatitude(), location.getLongitude()));
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(this, "No Permission granted", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public String hereLocation(double lat, double lon)
    {
        String curCity = "";
        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocation(lat, lon, 1);
            if (addressList.size() > 0)
            {
                curCity = addressList.get(0).getLocality();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return curCity;
    }

}
