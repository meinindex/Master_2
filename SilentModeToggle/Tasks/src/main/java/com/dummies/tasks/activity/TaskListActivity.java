package com.dummies.tasks.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;

              /*importet class, more Options*/


import com.dummies.tasks.R;

public class TaskListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        setActionBar((Toolbar) findViewById(R.id.toolbar));
    }

     /*
        * Wird aufgerufen, wenn der Benutzer einen Task bearbeiten oder einfügen will*/
    @Override
    public void editTask(long id)
     {
         /*Wenn aufgefodert werden Task zu bearbeiten oder einzufügen.
         * Starte TaskEditActivity mit der ID des zu bearbeitenden Tasks*/
         startActivity(
                 new Intent(this, TaskEditActivity.class)
                 .putExtra(TaskEditActivity.EXTRA_TASKID, id));
     }
}
