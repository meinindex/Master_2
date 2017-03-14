package de.example.silentmodetoggle;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import de.example.silentmodetoggle.util.RingerHelper;

public class Silent_Mode_Toggle extends Activity {

    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // immer zuerst das hier aufrufen
        super.onCreate(savedInstanceState);

        /*Referenz auf den AudioManager von Android abrufen, sodass wir ihm zum
        * Umschalten auf den Klingelton verwenden können.*/
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        //Initialisierung des Layouts
        setContentView(R.layout.activity_silent__mode__toggle);

        // Die View mit der ID "Content" in unserer Layoutdatei finden
        FrameLayout contentView = (FrameLayout) findViewById(R.id.content);

        /* Einen KlickListener für die contentView erstellen,
        * die den Lautlos-Status des Telefons umschaltet und die UI
        * aktualisiert, um den neuen Status zu reflektieren*/

        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Klingelmodus umschalten. Wenn Normal, dann stummschalten
                * wenn stumm, anderherum*/

                // UI aktualisieren
                updateUi();
            }
        });
        Log.d("SilentModeAPP", "Dies ist ein Test");
        /*d:Debugging-Eintrag
        * I: Information
        * w: Warnung
        * wtf: What a terrible failure (Fürchterlicher Fehler)
        * v: ausführlich (verbose)*/
    }

    /*Aktualisiert das Bild auf der UI, das zeigt,
    ob der stumme oder der normale Modus aktiv ist*/
    private void updateUi()
    {
        /*View phone_icon, da ImageView, im Layout, wird in ImageView übernommen*/
        ImageView imageView = (ImageView) findViewById(R.id.phone_icon);

        /*PhoneImage auf die Id des Bildes setzen, das anzeigt,
        ob der Klingelton aktiviert ist oder nicht. Bilder in  mipmap-xxhdpi*/
        /*(<boolescher Ausdruck>) ? AusgabewertTrue : AusgabewertFalse;*/

        int phoneImage = RingerHelper.isPhoneSilent(audioManager)                   // Ternary Operator If-Else nur kürzer
                ? R.mipmap.ringer_off                                               // gibt isPhoneSilent true, dann das
                : R.mipmap.ringer_on;                                               // sonst das

        //ImageView auf das Bild des phoneImage setzen
        imageView.setImageResource(phoneImage);
    }

    /*Wenn Aktivität fortgesetzt wird, müssen Schaltflächen
    aktualisiert werden, um Systemstatus zu reflektieren*/

    @Override
    protected void onResume()
    {
        super.onResume();
        //UI aktualisieren, falls sich etwas geändert hat
        updateUi();
    }
}


