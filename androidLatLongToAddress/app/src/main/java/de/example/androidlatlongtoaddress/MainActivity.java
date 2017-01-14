package de.example.androidlatlongtoaddress;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText latText = (EditText) findViewById(R.id.latText);
        final EditText lonText = (EditText) findViewById(R.id.lonText);
        Button btnFind = (Button) findViewById(R.id.find);

        final ListView listResult = (ListView) findViewById(R.id.listResult);

        geocoder = new Geocoder(this);

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Nimm die Eingaben aus EditText, wandle in String & gib an strLat
                String strLat = latText.getText().toString();
                String strLon = lonText.getText().toString();

                boolean parsable = true;
                Double lat = null, lon = null;

                try
                {
                    //wandle String in Double
                    lat = Double.parseDouble(strLat);
                }
                catch (NumberFormatException ex)
                {
                    parsable = false;
                    Toast.makeText(MainActivity.this,
                            "Latitude doesn't contain parsable double",
                            Toast.LENGTH_LONG).show();
                }

                //wandle String in Double
                try{
                    lon = Double.parseDouble(strLon);
                }
                catch (NumberFormatException ex)
                {
                    parsable = false;
                    Toast.makeText(MainActivity.this,
                            "Longitude does not contain a parsable double",
                            Toast.LENGTH_LONG).show();
                }

                if(parsable)
                {
                    Toast.makeText(MainActivity.this, "find" + lat + ":" + lon,
                            Toast.LENGTH_LONG).show();

                    List<Address> geoResult = findGeocoder(lat, lon);

                    if(geoResult != null)
                    {
                        List<String> geoStringResult = new ArrayList<String>();

                        int i = 0;
                        Address thisAddress = geoResult.get(i);
                        String stringThisAddress = "";


                        stringThisAddress +=
                                "Adresse:" + "\n"
                                        + thisAddress.getAddressLine(0) + "\n"                      // Straße & Hausnummer
                                        + thisAddress.getPostalCode()                               // Postleitzahl
                                        + " " + thisAddress.getLocality() + "\n"                    // Stadt
                                        + thisAddress.getAdminArea() + "\n"                         // Bundesstaat
                                        + thisAddress.getCountryName();

                        geoStringResult.add(stringThisAddress);

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_list_item_1, android.R.id.text1, geoStringResult);

                        listResult.setAdapter(adapter);
                    }
                }

            }
        });


    }

    private List<Address> findGeocoder(Double lat, Double lon) {

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
