package com.dummies.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.dummies.tasks.R;
import com.dummies.tasks.activity.TaskEditActivity;
import com.dummies.tasks.adapter.TaskListAdapter;
import com.squareup.picasso.Picasso;

import static android.R.attr.id;

/**
 * Created by Claudi on 18.03.2017.
 */

public class TaskEditFragment extends Fragment {

    public static final String DEFAULT_FRAGMENT_TAG = "taskEditFragment";
    /*"Name den verwende, um Fragment zu identifizieren"*/

    static final String TASK_ID = "taskID";
    /*Name des Eintrags, der verwendet wird, um taskID zu speichern, wenn das Fragment seinen Status speichern muss*/

    //Views
    View rootView;
    EditText titleText;
    EditText notesText;
    ImageView imageView;

    long taskId;
    /*ID des zu bearbeitenden Tasks. Wird eine neue Task angelegt, ist die ID anfänglich 0, wird aber auf
    *  die neue ID des Tasks gesetzt, nachdem er gespeichert wurde.*/

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        /*Setzt Task ID den intent-Argumenten entsprechend,
        falls verfügbar Fragmente erhalten ihre Argumente nicht von den Konstruktoren wie normale Java-Objekte.
        Stattdessen musst du deine Argumente unter Verwendung der Methoden getArguments und setArguments abfragen beziheungsweise
        festlegen.
        */
        if(arguments != null)
        {
            taskId = arguments.getLong(TaskEditActivity.EXTRA_TASKID, 0L);
        }

        if(savedInstanceState != null)
        {
            taskId = savedInstanceState.getLong(TASK_ID);
        }
        /*stellt taskID aus savedInstanceState wieder her, falls verfügbar*/
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        /*Wird aufgerufen, wenn Android Fragment zerstören muss, hauptsächlich, wenn Speicher freigegeben werden muss.
        * Eine Wiederherstellung kann später notwendig sein. Android speichert alle aktuellen WWerte der Views im otStateBoundle.
        * Wird die Aktivität wieder hergestellt, werden die WErte automatisch in onCreate aus dem savedInstanceState-Bundle wieder
        * eingetragen.
        * !!!Nicht vergessen!!!
        * Immmer wenn ein Status vorhanden ist, den der Nutzer ändern kann, musst du dafür sorgen, dass er korrekt gespeichert wird.
        * Dazu einfach im outState-Bundle speichern un aus dem savedInstanceState-Bundle in onCreate wieder einlesen.*/

        outState.putLong(TASK_ID, taskId);
        /*Feld kann sich geändert haben, während des Ausführens der Aktivität. Muss also sicherstellen, dass in outStateBundle
        * gespeichert wird, damit in onCreate später herstellen kann*/

    }


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_task_edit, container, false);
        /*Füllt Layout und legt den Container fest. Das Layout ist die View, die zurückgegeben wird*/

        rootView = v.getRootView();
        /*erstellt unter Verwendung des Layouts ein paar Views, mit denen geareitet wird.*/
        titleText = (EditText) v.findViewById(R.id.title);
        notesText = (EditText) v.findViewById(R.id.notes);
        imageView = (ImageView) v.findViewById(R.id.image);


        // Minaturbild festlegen
        Picasso.with(getActivity())
                .load(TaskListAdapter.getImageUrlForTask(taskId))
                .into(imageView);

        /*Seite benötigt Bild*/

        return v;
    }

    public static TaskEditFragment newInstance (long ID)
    {
        TaskEditFragment fragment = new TaskEditFragment();
        Bundle args = new Bundle();
        args.putLong(TaskEditActivity.EXTRA_TASKID, id);
        fragment.setArguments(args);
        return fragment;
    }
    /*Fragment benötigt ID des Tasks, der bearbeitet werden soll, deshalb muss Methoder erstellt werden, die neues Fragment für
    * vorgegebene ID erstellen kann. Dazu benötigt TaskEditFragmentID die statische Factory-Methode.*/

}


