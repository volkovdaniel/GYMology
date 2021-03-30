package team13.gymology;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;
import exerciseData.gymology.Exercise;
import exerciseData.gymology.ExerciseController;
import exerciseData.gymology.ExerciseGroup;
import exerciseData.gymology.ExerciseSet;
import menu.semiradialmenu.MenuItemView;
import menu.semiradialmenu.RadialMenuView;
import workoutData.gymology.Workout;
import workoutData.gymology.WorkoutController;

public class WorkoutDetails extends AppCompatActivity implements RadialMenuView.RadialMenuListener {
    public RadialMenuView radialMenuView;
    public Button button;
    // Private
    private Gson g = new Gson();
    private Workout userWorkout;
    // Final
    private final String TAG = "Workout Details: ";


    /*
    TODO: On resume, Save Updated workout to LS or send to WorkoutDatabase and have it
     update it
     */
    /*
When workout is logged,
-save to ls with an edit tag in order to not flag the name already exists
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_details);

        // Grab workout to complete
        Intent intent = getIntent();
        userWorkout = g.fromJson(intent.getStringExtra("LogWorkout"),
                Workout.class);

        // Set Title of Workout
        ((TextView)findViewById(R.id.routineName)).setText(userWorkout.get_name());

        // Display Workout's Exercises
        displayUserExercises(new WeakReference<>(this));

        // Setting Back Button on Log Workout
        findViewById(R.id.logWorkout).setOnClickListener(View ->
                // Save to LS
                super.onBackPressed());


        // Radial menu
        radialMenuView = findViewById(R.id.radial_menu_view);
        button = findViewById(R.id.button);

        MenuItemView itemOne = new MenuItemView(this, "Profile", R.drawable.ic_person, R.color.grayWeb);
        MenuItemView itemTwo = new MenuItemView(this, "Workouts", R.drawable.ic_fitness, R.color.prussianBlue);
        MenuItemView itemThree = new MenuItemView(this, "Home", R.drawable.ic_home, R.color.grayWeb);
        MenuItemView itemFour = new MenuItemView(this, "Stats", R.drawable.ic_stats, R.color.prussianBlue);
        MenuItemView itemFive = new MenuItemView(this, "Calendar", R.drawable.ic_calendar, R.color.grayWeb);
        ArrayList<MenuItemView> items = new ArrayList<>();
        items.add(itemOne);
        items.add(itemTwo);
        items.add(itemThree);
        items.add(itemFour);
        items.add(itemFive);
        radialMenuView.setListener(this).setMenuItems(items).setCenterView(button).setInnerCircle(true,
                R.color.black).setOffset(10).build();
        //End radial menu
    }

    protected void onResume() {
        super.onResume();
        // Update exercise in workout object
//        updateExercise();
        // Set checkbox of completed exercise to checked
        Log.d(TAG, "Arrived from ExerciseDetails Activity");
    }

    /**
     * DisplayUserExercise
     *
     * Takes the current workout's exercises and displays them to its respective list
     * @param activity
     */
    public void displayUserExercises(WeakReference<Activity> activity) {
        // Set up the exercise controller with current exercises
        ExerciseController ec = new ExerciseController(activity,
                userWorkout.get_workoutExercises());

        Log.d(TAG, "Creating new ExerciseController Thread");
        // Display Exercises on ListView
        Thread t1 = new Thread(ec);
        t1.start();

    }

    public void updateExercise(Exercise exercise) {
        // Grab Updated exercise from Shared Preferences
        SharedPreferences sp = getSharedPreferences(exercise.getName(),MODE_APPEND);
        String exerciseSet = sp.getString(exercise.getName(), "");

    }

    public void showClose(View view) {
        radialMenuView.show();
    }

    @Override
    public void onItemClicked(int i) {
        switch (i) {
            case 0:
                // Profile Screen
                startActivity(new Intent(this, Profile.class));
                finish();
                break;
            case 1:
                // Workouts DB Screen
                startActivity(new Intent(this, WorkoutDatabase.class));
                finish();
                break;
            case 2:
                // Home Screen
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case 3:
                // Statistics
                startActivity(new Intent(this, Statistics.class));
                finish();
                break;
            case 4:
                // Calendar Screen
                startActivity(new Intent(this, Calendar.class));
                finish();
                break;
        }
    } // end OnItemClicked()
}