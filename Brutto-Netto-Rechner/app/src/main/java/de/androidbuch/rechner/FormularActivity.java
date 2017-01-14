package de.androidbuch.rechner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class FormularActivity extends AppCompatActivity {

    public static final String BETRAG_KEY ="betrag";
    public static final String BETRAG_ART = "art";
    public static final String UST_PROZENT = "ust";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formular_activity);
    }

    @Override
    public void onClickBerechnen(View Button)
    {
        //Betrag finden & brarbeiten
        final EditText txtBetrag =
                (EditText) findViewById(R.id.edt_betrag);
        // umwandeln in Kommazahl
        final float betrag = Float.parseFloat(txtBetrag.getText().toString());

        //Art des Betrags
        boolean isNetto = true;
        final RadioGroup rg =
                (RadioGroup) findViewById(R.id.rg_art);
        switch (rg.getCheckedRadioButtonId())
        {
            case R.id.rb_art_netto:
                isNetto = true;
                break;
            case R.id.rb_art_brutto:
                isNetto=false;
                break;
            default:
        }

        // Prozentwert Umsatzsteuer
        final Spinner spinner =
                (Spinner) findViewById(R.id.sp_umsatzsteuer);
        final int pos = spinner.getSelectedItemPosition();
        final int [] prozentwerte = getResources().getIntArray(R.array.ust_werte);

        final int prozentwert = prozentwerte[pos];

        final Intent intent = new Intent(this,ErgebnisActivity.class);

        intent.putExtra(BETRAG_KEY, betrag);
        intent.putExtra(BETRAG_ART, isNetto);
        intent.putExtra(UST_PROZENT,prozentwert);
        startActivity(intent);
    }

    @Override
    public boolean onCreatOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected()
    {

    }
}
