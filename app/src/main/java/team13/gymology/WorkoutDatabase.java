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

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class WorkoutDatabase extends AppCompatActivity implements RadialMenuView.RadialMenuListener {
    private final String TAG = "Workout Database: ";
    public RadialMenuView radialMenuView;
    public Button button;

    //        Resource to add button functionality on each list item
//        https://stackoverflow.com/questions/12596199/android-how-to-set-onclick-event-for-button-in-list-item-of-listview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_db);

        load_DB(new WeakReference<>(this));


        // Set Create Workout Button Listener
        findViewById(R.id.btn_add_newWO).setOnClickListener((v) -> startActivity(new Intent(this, CreateWorkout.class)));

        // Radial Menu Creation
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
    } // end onCreate

    /**
     * Load_DB
     * Loads the current saved workouts to a list.
     *
     * @param weakActivity
     */
    private void load_DB(WeakReference<Activity> weakActivity) {
        Log.d(TAG, "Loading Workout DB");
        // Create and start thread
        WorkoutController workoutController = new WorkoutController(weakActivity, Actions.LOAD_DB);
        Thread t1 = new Thread(workoutController);
        t1.start();
    }

    public void showClose(View view) {
        radialMenuView.show();
    }

    /**
     * OnItemClicked
     * Method to assign the locations for each button to go to on click.
     *
     * @param i
     */
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