package team13.gymology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WorkoutDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_details);
    }

    // testing screens
    public void exerciseView(View view) {
        Intent intent = new Intent(this, LogExercise.class);
        startActivity(intent);
    }
}