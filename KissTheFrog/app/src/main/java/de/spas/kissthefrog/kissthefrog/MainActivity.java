package de.spas.kissthefrog.kissthefrog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    // MainActivity überschreibt Funktion in elternklasse
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // legtr fest, welches layout zur Activity gehört
        setContentView(R.layout.activity_main);
        newGame();
    }

    private int points;
    private int round;
    private int countdown;

    private void newGame()
    {
        points = 0;
        round = 1;
        initRound();
    }

    private void initRound()
    {
        countdown = 10;
        update();
    }

    private void fillTextView(int id, String text)
    {
        TextView tv = (TextView) findViewById(id);
        tv.setText(text);
    }

    private void update()
    {
        fillTextView(R.id.points, Integer.toString(points));
        fillTextView(R.id.round, Integer.toString(round));
        fillTextView(R.id.countdown, Integer.toString(countdown*1000));
    }
}
