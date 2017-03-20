package com.dummies.tasks.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dummies.tasks.R;
import com.dummies.tasks.interfaces.OnEditTask;
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
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final Context context = viewHolder.titleView.getContext();                    /*ermittelt den Kontext, für das Listenelement. Picasso benötigt Kontext später*/
        viewHolder.titleView.setText(fakeData[i]);

        // Miniaturbild festlegen
        Picasso.with(context)                                                   /*festlegen welcher Kontext für bild laden verwendet werden soll*/
                .load(getImageUrlForTask(i))                                    /*welche URL geladen werden soll*/
                .into(viewHolder.imageView);                                    /*sage Picasso, in welche Image View Bild gestellt werden soll, nach dem Laden*/

        // Klick-Aktion festlegen auf die cardView
        viewHolder.cardView.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        ((OnEditTask) context).editTask(i);
                        //erstelle OnEditTask-Schnittstelle, sodass TaskListActivity implementiert werden kann
                    }
                    /*OnClickListener forder Kontext(auch Aktivität) an, um den Task zu bearbeiten,
                    indem er editTask für die Aktivität aufruft.*/
                }
        );
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
            card.setCardBackgroundColor(Color.rgb(255, 185, 54));                   /*Hintergrundfarbe der Katen verändert, kann in rgb gehen muss auch irgendwie selbst definiert werden können*/
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
