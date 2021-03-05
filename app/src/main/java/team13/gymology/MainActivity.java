package team13.gymology;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import exerciseData.gymology.ExerciseController;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(v ->
                retrieveWgerAPI(new WeakReference<>(MainActivity.this)));





    }

    private void retrieveWgerAPI(WeakReference<Activity> weakActivity) {

        // Create  and start thread
        ExerciseController exercise = new ExerciseController(weakActivity, true);
        Thread t1 = new Thread(exercise);
        t1.start();

    }
}