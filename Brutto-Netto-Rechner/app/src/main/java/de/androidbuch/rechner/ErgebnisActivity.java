package de.androidbuch.rechner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.Normalizer;

public class ErgebnisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ergebnis_activity);

        final Bundle extras = getIntent().getExtras();

        if( extras !=  null)
        {
            final Ergebnis ergebnis = new Ergebnis();

            ergebnis.betrag = extras.getFloat(FormularActivity.BETRAG_KEY);
            ergebnis.isNetto = extras.getBoolean(FormularActivity.BETRAG_ART, true);
            ergebnis.ustProzent = extras.getInt(FormularActivity.UST_PROZENT);

            zeigeErgebnis(ergebnis);
        }

    }

    /**
     * Ergebnis
     */

    private void zeigeErgebnis(Ergebnis ergebnis)
    {
        setTitle("Ergebnis");

        ergebnis.berechneErgebnis();

        final TextView txtNettobetrag = (TextView) findViewById(R.id.tv_nettobetrag);
        txtNettobetrag.setText(String.valueOf(ergebnis.betragNetto));

        final TextView txtUmsatzsteuer = (TextView) findViewById(R.id.tv_umsatzsteuer);
        txtUmsatzsteuer.setText(String.valueOf(ergebnis.betragUst));

        final TextView txtBruttobetrag = (TextView) findViewById(R.id.tv_bruttobetrag);
        txtBruttobetrag.setText(String.valueOf(ergebnis.betragBrutto));

    }

}
