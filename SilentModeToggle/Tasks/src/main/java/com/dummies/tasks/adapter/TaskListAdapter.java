package com.dummies.tasks.adapter;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;

import com.dummies.tasks.R;

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
    public ViewHolder onCreateViewHolder (ViewGroup parent, int i)
    {
        // eine neue View erstellen
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_task, parent, false);
        // in ViewHolder packen

        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
        viewHolder.titleView.setText(fakeData[i]);
    }

    @Override
    public int getItemCount()
    {
        return fakeData.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        TextView titleView;

        public ViewHolder (CardView card)
        {
            super(card);
            //Log.d(Tag, "Klasse ViewHolder");
            cardView = card;
            titleView = (TextView) card.findViewById(R.id.text1);
        }
    }
}
