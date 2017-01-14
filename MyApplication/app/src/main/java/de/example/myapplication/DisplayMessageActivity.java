package de.example.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
    }

    // getIntent() grabs the intent, that startet the activity
    Intent intent = getIntent();
    //MainActivity from AndroidManifest.xml
    // getStringExtra()retrieves data from the first activity
    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
    TextView textView = new TextView(this);
    // -> Zusatzvariable, woher id
    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,40);
    textView.setText(message);


    // cast Layout to viewgroup, cause it's the superclass of all layouts & contains addView() method
    // layout has to have the android:id=" @+id/activity..."
    ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
    layout.addView(textView);


}
