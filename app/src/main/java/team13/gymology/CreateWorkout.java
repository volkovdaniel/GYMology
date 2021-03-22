package team13.gymology;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import exerciseData.gymology.ExerciseController;
import menu.semiradialmenu.RadialMenuView;
import workoutData.gymology.WorkoutController;

import java.lang.ref.WeakReference;

public class CreateWorkout extends AppCompatActivity implements RadialMenuView.RadialMenuListener {

    private String category = "arms";
    RadialMenuView radialMenuView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_workout);

//        Resource to add button functionality on each list item
//        https://stackoverflow.com/questions/12596199/android-how-to-set-onclick-event-for-button-in-list-item-of-listview

        // TODO: Dropdown menu passes in category
        retrieveWgerAPI(new WeakReference<>(this), category);

        /*
        Event Listeners
         */

        // Save Workout
        findViewById(R.id.btn_saveNewWO).setOnClickListener(v ->
                saveWorkout(new WeakReference<Activity>(this)));

        // Add Exercise to Workout

        // Edit

    }


    /**
     * Retrieve exercise database by category
     * @param weakActivity
     * @param category default "arms"
     */
        private void retrieveWgerAPI(WeakReference<Activity> weakActivity, String category) {
        // Create  and start thread
        ExerciseController exercise = new ExerciseController(weakActivity, category);
        Thread t1 = new Thread(exercise);
        t1.start();

    }

    /**
     * Retrieve exercise database by category
     * @param weakActivity
     * @param category default "arms"
     */
    private void saveWorkout(WeakReference<Activity> weakActivity) {
        // Create  and start thread
        WorkoutController workout = new WorkoutController(weakActivity);
        Thread t1 = new Thread(workout);
        t1.start();

    }

    public void showClose(View view) {
        radialMenuView.show();
    }

    public void onItemClicked(int i) {
        Toast.makeText(this, String.valueOf(i), Toast.LENGTH_SHORT).show();
    }
}