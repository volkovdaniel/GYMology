package team13.gymology;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import menu.semiradialmenu.MenuItemView;
import menu.semiradialmenu.RadialMenuView;
import profile.gymology.BMI;
import profile.gymology.UserProfile;
import utilities.gymology.Actions;
import utilities.gymology.Types;
import workoutData.gymology.Workout;
import workoutData.gymology.WorkoutController;

public class Profile extends AppCompatActivity implements RadialMenuView.RadialMenuListener {

    public RadialMenuView radialMenuView;
    public Button button;

    // examples to edit from CreateWorkout.java
    private final String TAG = "Profile Activity: ";
    public UserProfile userProfile;
    //Private
    private WorkoutController workoutController;
    //end

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        //examples to edit from CreateWorkout.java
        userProfile = new UserProfile();
        workoutController = new WorkoutController();
//        final CheckBox checkBox_calories = (CheckBox) findViewById(R.id.checkbox_calories);
//        if (checkBox_calories.isChecked()) {
//            checkBox_calories.setChecked(false);
//        }

        //end

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

        // Save Profile
        findViewById(R.id.btn_saveSettings).setOnClickListener(v ->
                saveProfile(new WeakReference<>(this)));

    }

    // Testing layouts
    public void bmiCalc(View view) {
        Intent intent = new Intent(this, BMI.class);
        startActivity(intent);
    }

    private void saveProfile(WeakReference<Activity> weakActivity) {
        Log.d(TAG, "Saving User Profile");
        userProfile.set_age(((EditText)findViewById(R.id.input_age)).getText().toString());
        userProfile.set_height(((EditText)findViewById(R.id.input_height)).getText().toString());
        userProfile.set_weight(((EditText)findViewById(R.id.input_weight)).getText().toString());
        userProfile.set_calories(((CheckBox)findViewById(R.id.checkbox_calories)).isChecked());
        userProfile.set_water(((CheckBox)findViewById(R.id.checkbox_water)).isChecked());
        userProfile.set_meals(((CheckBox)findViewById(R.id.checkbox_meal)).isChecked());
    }

    public void showClose(View view) { radialMenuView.show(); }

    @Override
    public void onItemClicked(int i) {
        switch (i) {
            case 0:
                // Profile Screen
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
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

    }
}