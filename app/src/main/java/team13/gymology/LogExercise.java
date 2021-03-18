package team13.gymology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_details);
    }

    public void stopwatch(View view) {
        Intent intent = new Intent(this, Stopwatch.class);
        startActivity(intent);
    }

    public void timer(View view) {
        Intent intent = new Intent(this, Timer.class);
        startActivity(intent);
    }
}