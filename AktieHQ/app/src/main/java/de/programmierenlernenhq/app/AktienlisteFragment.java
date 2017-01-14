package de.programmierenlernenhq.app;

/**
 * Created by Claudi on 20.10.2016.
 */


import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class AktienlisteFragment extends Fragment
{
    //ArrayAdapter as Membervalue of AktienlisteFragment
    ArrayAdapter<String>mAktienlisteAdapter;

    public AktienlisteFragment()
    {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Menü bekannt geben, dadurch kann Fragment Menü-events vorbereiten
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_aktienlistefragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // is Menu-Element "action_daten_aktualisieren" selected?
        int id = item.getItemId();
        if(id == R.id.action_daten_aktualisieren)
        {
            //Erzeuge Instanz vonHoleDatenTask & start asynchroner Task
            HoleDatenTask holeDatenTask = new HoleDatenTask();
            holeDatenTask.execute("Aktie");

            // inform User, that new stockdatas are requested in Backgroundtask
            Toast.makeText(getActivity(),"Aktualisieren gedrückt!",Toast.LENGTH_LONG).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    //Callback-Method -> Load layout of Fragment
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        String LOG_TAG = AktienlisteFragment.class.getSimpleName();
        Log.v(LOG_TAG, "verbose - Meldung");
        Log.d(LOG_TAG, "debug - Meldung");
        Log.i(LOG_TAG, "information - Meldung");
        Log.w(LOG_TAG, "warning - Meldung");
        Log.e(LOG_TAG, "error - Meldung");


        // Mock data
        String [] aktienListeArray =
                {
                        "Adidas - Kurs: 73,45  €",
                        "Allianz - Kurs: 145,12 €",
                        "BASF - Kurs: 128, 60 €",
                        "Bayer - Kurs: 128,60 €",
                        "Beiersdorf - Kurs: 80,55 €",
                        "BMW St. - Kurs: 104,11 €",
                        "Commerzbank - Kurs: 12,47 €",
                        "Continental - Kurs: 209,94 €",
                        "Daimler - Kurs: 84,33 €"
                };
        // make List out of String array
        List <String> aktienListe = new ArrayList<>(Arrays.asList(aktienListeArray));

        mAktienlisteAdapter = new ArrayAdapter<>(
                getActivity(), //aktuelle Umgebung (surrent Activity)
                R.layout.list_item_aktienliste, //Id of XML-Layout-file
                R.id.list_item_aktienliste_textview, // Id of TextView
                aktienListe); // exampleData in ArrayList

        // search for ListView-Object in ViewHierarchy
        // binden des ArrayAdapters an ListView
        View rootView = inflater.inflate(R.layout.fragment_aktienliste, container, false);// create View-Hierarchie from fragment_aktienlisteenliste.xml

        ListView aktienlisteListView = (ListView)
                rootView.findViewById(R.id.listview_aktienliste); // return ListView
                aktienlisteListView.setAdapter(mAktienlisteAdapter);

        return rootView;
        //return inflater.inflate(R.layout.fragment_aktienliste, container, false);
    }

    // intern class HoleDatenTask executes asynch. task on own Workthread
    public class HoleDatenTask extends AsyncTask<String, Integer, String[]>
    {
        private final String LOG_TAG = HoleDatenTask.class.getName();

        @Override
        protected String[] doInBackground(String ... strings)
        {
            String[] ergebnisArray = new String[20];

            for(int i = 0; i < 20; i++)
            {
                // fill String with example Data
                ergebnisArray[i] = strings[0]+"_"+(i+1);

                // after 5 Elements say what you've done
                if(i%5 == 4)
                {
                    publishProgress(i+1, 20);
                }

                // simulate waiting Time
                try
                {
                    Thread.sleep(600);
                }
                catch(Exception e)
                {
                    Log.e(LOG_TAG, "Error", e);
                }
            }
            return ergebnisArray;
        }

        @Override
        protected void onProgressUpdate(Integer ... values)
        {
            //Inform User, publishProgress(int...) in doInBackground(String...) is called
            Toast.makeText(getActivity(),
                    values[0] + "von" + values[1] +"geladen",
            Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String[] strings)
        {
            // delete values in ArrayAdapter & insert new
            //new values are from doInBackground(String ...)

            if(strings != null)
            {
                mAktienlisteAdapter.clear();
                for(String aktienString : strings)
                {
                    mAktienlisteAdapter.add(aktienString);
                }
            }
            //Backgroundcalculations are finished, inform User

            Toast.makeText(getActivity(),"Aktiendaten vollständig geladen!", Toast.LENGTH_SHORT).show();
        }
    }

}
