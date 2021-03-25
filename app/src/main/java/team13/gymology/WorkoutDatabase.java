package team13.gymology;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import menu.semiradialmenu.MenuItemView;
import menu.semiradialmenu.RadialMenuView;
import utilities.gymology.Actions;
import workoutData.gymology.WorkoutController;
import workoutData.gymology.WorkoutList;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class WorkoutDatabase extends AppCompatActivity implements RadialMenuView.RadialMenuListener {
    private final String TAG = "Workout Database: ";
    private WorkoutList workout_DB;
    private WorkoutController workoutController;

    public RadialMenuView radialMenuView;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_db);

        load_DB(new WeakReference<>(this));

//        Resource to add button functionality on each list item
//        https://stackoverflow.com/questions/12596199/android-how-to-set-onclick-event-for-button-in-list-item-of-listview

//        retrieveWgerAPI(new WeakReference<>(this));
//        retrieveWorkout_DB(new WeakReference<>(this));

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

    // Testing screens
    public void workoutView(View view) {
        Intent intent = new Intent(this, WorkoutDetails.class);
        startActivity(intent);
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
                Toast.makeText(this, "Saved Workouts", Toast.LENGTH_SHORT).show();
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