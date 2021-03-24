package team13.gymology;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import menu.semiradialmenu.RadialMenuView;
import utilities.gymology.Actions;
import workoutData.gymology.WorkoutController;
import workoutData.gymology.WorkoutList;

import java.lang.ref.WeakReference;

public class WorkoutDatabase extends AppCompatActivity implements RadialMenuView.RadialMenuListener {
    private final String TAG = "Workout Database: ";
    private WorkoutList workout_DB;
    private WorkoutController workoutController;

    RadialMenuView radialMenuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_db);

        load_DB(new WeakReference<>(this));

//        Resource to add button functionality on each list item
//        https://stackoverflow.com/questions/12596199/android-how-to-set-onclick-event-for-button-in-list-item-of-listview

//        retrieveWgerAPI(new WeakReference<>(this));
//        retrieveWorkout_DB(new WeakReference<>(this));


    }

    //    private void retrieveWgerAPI(WeakReference<Activity> weakActivity) {
//        // Create  and start thread
//        ExerciseController exercise = new ExerciseController(weakActivity, "category");
//        Thread t1 = new Thread(exercise);
//        t1.start();
//
//    }
//    private void retrieveWorkout_DB(WeakReference<Activity> weakActivity) {
//        // Create  and start thread
//        WorkoutController workout_DB = new WorkoutController(weakActivity);
//        Thread t1 = new Thread(workout_DB);
//        t1.start();
//
//    }

    private void load_DB(WeakReference<Activity> weakActivity) {
        Log.d(TAG, "Loading Workout DB");
        // Create and start thread
        workoutController = new WorkoutController(weakActivity, Actions.LOAD_DB);
        Thread t1 = new Thread(workoutController);
        t1.start();
    }


    public void showClose(View view) {
        radialMenuView.show();
    }

    public void onItemClicked(int i) {
        Toast.makeText(this, String.valueOf(i), Toast.LENGTH_SHORT).show();
    }

    // Testing screens
    public void workoutView(View view) {
        Intent intent = new Intent(this, WorkoutDetails.class);
        startActivity(intent);
    }
}