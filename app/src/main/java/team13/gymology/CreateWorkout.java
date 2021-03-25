package team13.gymology;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import exerciseData.gymology.Exercise;
import exerciseData.gymology.ExerciseController;
import menu.semiradialmenu.MenuItemView;
import menu.semiradialmenu.RadialMenuView;
import utilities.gymology.Actions;
import utilities.gymology.Categories;
import utilities.gymology.Types;
import workoutData.gymology.Workout;
import workoutData.gymology.WorkoutController;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * CreateWorkout
 * Activity that creates a workout and saves the workout to local storage.
 */
public class CreateWorkout extends AppCompatActivity implements RadialMenuView.RadialMenuListener, AdapterView.OnItemSelectedListener {
    // Final
    private final String TAG = "CreateWorkout Activity: ";
    // Public
    public RadialMenuView radialMenuView;
    public Button button;
    public Workout userWorkout;
    //Private
    private RadioGroup typeGroup;
    private Types checkedBtnValue = Types.CARDIO;
    private Spinner category_menu;
    private int category = 8;
    private WorkoutController workoutController;

/*
TODO: Retrieve User Created Name from the edit text view
DONE: Make Name Text area customizable? Can't change the name for the workout created atm.
DONE: Display Saved Workout button
TODO: Fix Menu
DONE: Save Completed Workout to Local Storage
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_workout);
        userWorkout = new Workout();
        workoutController = new WorkoutController();

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

//        Resource to add button functionality on each list item
//        https://stackoverflow.com/questions/12596199/android-how-to-set-onclick-event-for-button-in-list-item-of-listview

        // Save Workout
        findViewById(R.id.btn_saveNewWO).setOnClickListener(v ->
                saveWorkout(new WeakReference<>(this)));


        typeGroup = (RadioGroup) findViewById(R.id.typeGroup);

//         Check for which type is selected and save to a member field
        typeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            Log.d(TAG, "Type of Workout Selected");

            // Grab selected Radio Button, get the Text, convert to Type enum
            checkedBtnValue =
                    Types.valueOf(((RadioButton)findViewById(checkedId)).getText().toString().toUpperCase());
        });


        // DONE: Dropdown menu passes in category

        // Dropdown Menu Creation Adapter
        ArrayAdapter<CharSequence> dropDownAdapter = ArrayAdapter.createFromResource(this,
                R.array.workout_array, android.R.layout.simple_spinner_dropdown_item);

        // Set drop down details
        category_menu = findViewById(R.id.dropdown_exercise_group);
        category_menu.setAdapter(dropDownAdapter);

        // Listener to bring up the updated page
        category_menu.setOnItemSelectedListener(this);

    }



    /**
     * Retrieve the exercise database by category
     *
     * @param weakActivity The weak reference to the current CreateWorkout Activity
     * @param category     A Category of exercises to draw from, default arms
     */
    private void retrieveWgerAPI(WeakReference<Activity> weakActivity, int category) {
        Log.d(TAG, "Loading exercises by specified category");
        // Create  and start thread
        ExerciseController exercise = new ExerciseController(weakActivity, category);
        Thread t2 = new Thread(exercise);
        t2.start();

    }

    private void saveWorkout(WeakReference<Activity> weakActivity) {
        Log.d(TAG, "Saving User Workout");
        userWorkout.set_name(((EditText)findViewById(R.id.input_name)).getText().toString());
        userWorkout.set_type(checkedBtnValue);
        // Create and start thread
        workoutController = new WorkoutController(weakActivity, userWorkout, Actions.SAVE);
        Thread t1 = new Thread(workoutController);
        t1.start();

    }

    /**
     * <p>Callback method to be invoked when an item in this view has been
     * selected. This callback is invoked only when the newly selected
     * position is different from the previously selected position or if
     * there was no selected item.</p>
     * <p>
     * Implementers can call getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param parent   The AdapterView where the selection happened
     * @param view     The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id       The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // Convert string item to category value
        Categories userCat = Categories.valueOf((parent.getItemAtPosition(position)).toString().toUpperCase());

        // Save Category to correct id for display
        switch (userCat) {
            case ARMS:
                category = 8;
                break;
            case ABS:
                category = 10;
                break;
            case BACK:
                category = 12;
                break;
            case CALVES:
                category = 14;
                break;
            case CHEST:
                category = 11;
                break;
            case LEGS:
                category = 9;
                break;
            case SHOULDERS:
                category = 13;
                break;
        }
        // Update List Items
        retrieveWgerAPI(new WeakReference<>(this), category);

    }

    /**
     * Callback method to be invoked when the selection disappears from this
     * view. The selection can disappear for instance when touch is activated
     * or when the adapter becomes empty.
     *
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * AddtoUserWorkout
     * Adds checked exercises to current userWorkout
     * @param isChecked selected exercise
     */
    public void addToUserWorkout(Exercise isChecked) {
        userWorkout.generateGroup(isChecked);
    }

    /**
     * removeUserExercise
     * Remove's un-checked exercise from current userWorkout
     * @param notChecked
     */
    public void removeUserExercise(Exercise notChecked) {
        userWorkout.removeFromGroup(notChecked);
    }

    // Shows the radial menu
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