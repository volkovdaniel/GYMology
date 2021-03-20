package team13.gymology;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import exerciseData.gymology.ExerciseController;
import menu.semiradialmenu.RadialMenuView;
import workoutData.gymology.WorkoutController;

import java.lang.ref.WeakReference;

public class CreateWorkout extends AppCompatActivity implements RadialMenuView.RadialMenuListener {
    // Public
    RadialMenuView radialMenuView;

    //Private
    private RadioGroup typeGroup;
    private int checkedBtnId;

    // Final
    private final String TAG = "CreateWorkout Activity: ";
    private final String category = "arms";
    private Spinner category_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_workout);

//        Resource to add button functionality on each list item
//        https://stackoverflow.com/questions/12596199/android-how-to-set-onclick-event-for-button-in-list-item-of-listview

        // TODO: Dropdown menu passes in category
        retrieveWgerAPI(new WeakReference<>(this), category);

        // Save Workout
        findViewById(R.id.btn_saveNewWO).setOnClickListener(v ->
                saveWorkout(new WeakReference<>(this)));

//        typeGroup = (RadioGroup) findViewById(R.id.radioGroup);

        // Check for which type is selected and save to a member field
//        typeGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            Log.d(TAG, "Type of Workout Selected");
//
//            // Save checked workout type for later
//            checkedBtnId = checkedId;
//        });

        // Dropdown Menu Creation Adapter
        ArrayAdapter<CharSequence> dropDownAdapter = ArrayAdapter.createFromResource(this,
                R.array.workout_array, android.R.layout.simple_spinner_dropdown_item);

        // Set drop down details
        category_menu = findViewById(R.id.dropdown_exercise_group);
        category_menu.setAdapter(dropDownAdapter);
    }

    /**
     * Retrieve exercise database by category
     *
     * @param weakActivity
     * @param category     default "arms" from dropdown menu
     */
    private void retrieveWgerAPI(WeakReference<Activity> weakActivity, String category) {
        // Create  and start thread
        ExerciseController exercise = new ExerciseController(weakActivity, category);
        Thread t1 = new Thread(exercise);
        t1.start();

    }

    private void saveWorkout(WeakReference<Activity> weakActivity) {

        // Create and start thread
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