package team13.gymology;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseDetails extends AppCompatActivity {

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

    public void exerciseVideo(View view) {
        Intent intent = new Intent(this, ExerciseVideo.class);
        startActivity(intent);
    }
}