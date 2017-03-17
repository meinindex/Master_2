package com.dummies.tasks.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import com.dummies.tasks.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Claudi on 14.03.2017.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    static String[] fakeData = new String[]
            {
                    "Eins",
                    "Zwei, Freddy kommt vorbei",
                    "Drei",
                    "vier, er steht schon vor der Tür",
                    "Fünf",
                    "Sechs, jetzt kommt die alte Hetz",
                    "Sieben",
                    "Acht, schlaf nicht ein bei Nacht",
                    "Neun",
                    "Zehn, du darfst nicht schlafen gehn!"
            };

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        // eine neue View erstellen
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_task, parent, false);
        // in ViewHolder packen

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Context context = viewHolder.titleView.getContext();                    /*ermittelt den Kontext, für das Listenelement. Picasso benötigt Kontext später*/
        viewHolder.titleView.setText(fakeData[i]);

        // Miniaturbild festlegen
        Picasso.with(context)                                                   /*festlegen welcher Kontext für bild laden verwendet werden soll*/
                .load(getImageUrlForTask(i))                                    /*welche URL geladen werden soll*/
                .into(viewHolder.imageView);                                    /*sage Picasso, in welche Image View Bild gestellt werden soll, nach dem Laden*/
    }

    @Override
    public int getItemCount() {
        return fakeData.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView titleView;
        ImageView imageView;

        public ViewHolder(CardView card) {
            super(card);
            //Log.d(Tag, "Klasse ViewHolder");
            cardView = card;
            titleView = (TextView) card.findViewById(R.id.text1);
            imageView = (ImageView) card.findViewById(R.id.image);/*fügt View Holder ImageView hinzu
            diese wurde bei card_task.xml definiert. Es muss also nur noch ein Feld dafür erstellt werden, und über
            findViewbyId aufgerufen zu werden.*/
        }
    }

    public static String getImageUrlForTask(long taskId)
    {
        /*verwendet folgenden Dienst*/
        return "http://lorempixel.com/600/400/cats/?fakeId="+taskId;
    }

}
