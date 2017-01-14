package de.example.gps_ausgabe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_start, btn_stop;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = (Button) findViewById(R.id.start);
        btn_stop = (Button) findViewById(R.id.stop);
        textView = (TextView) findViewById(R.id.textView);
    }
}
