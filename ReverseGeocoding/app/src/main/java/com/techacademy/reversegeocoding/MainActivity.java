package com.techacademy.reversegeocoding;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnShow;
    TextView textView;
    LocationManager locationManager;
    String provider;
    final int MY_PERMISSION_REQUEST_CODE = 7171;
    double lat, lng;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    getLocation();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShow = (Button) findViewById(R.id.btnGetAdress);
        textView = (TextView) findViewById(R.id.txtAddress);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, MY_PERMISSION_REQUEST_CODE);

        } else {
            getLocation();


        }

        btnShow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                new GetAddress();
            }
        });

    }

    private void getLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        final Location location = locationManager.getLastKnownLocation(provider);




        if(location == null)
            Log.e("ERROR", "Location is null");
    }

    private class GetAddress extends AsyncTask <String, Void, String> {

        ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                double lat = Double.parseDouble(strings [0].split(",")[0]);
                double lng = Double.parseDouble(strings [0].split(",")[1]);
                String response;
                HttpDataHandler http = new HttpDataHandler();
                String string = String.format("https://maps.google.com/api/geocode/json?latlng=%.4f,%.4f&sensor=false", lat, lng);
                response = http.GetHTTPData(url)
            }
            catch

        }
    }
}
