package com.dummies.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dummies.tasks.R;
import com.dummies.tasks.adapter.TaskListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends Fragment {

    RecyclerView recyclerView;
    TaskListAdapter adapter;


    public TaskListFragment() {
        // Erforderlicher Leerer öffentlicher Konstruktor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        adapter = new TaskListAdapter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_task_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);

        /*alle haben die selbe Größe, Layout muss Größe nicht neu vermessen, so wird die Leistung verbessert*/
        recyclerView.setHasFixedSize(true);
        /*Jede Recycler View benötigt Layout Manager, die sagt, die Views dargestellt werden sollen*/
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;

    }

}
