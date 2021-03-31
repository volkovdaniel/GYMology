package team13.gymology;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import menu.semiradialmenu.MenuItemView;
import menu.semiradialmenu.RadialMenuView;
import workoutData.gymology.Workout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadialMenuView.RadialMenuListener {


    /*
    Overall items to accomplish

    TODO: Exit each of the new activity created on menu change, except ones like the
     WorkoutDetails class.

     TODO: Build and implement Exercise sets/reps into Workout object

     TODO: Stats activity created and accessing completed workouts

     TODO: Summary on Main displaying past five workouts, or last workout, or do random
       workout - anything

     TODO: Workout object including date created.

     TODO: WorkoutDetails resuming activity state from started exercise

     TODO: Starting saved workout places in saved values for changing

     TODO: Profile page displaying editable stuff, and saving to sharedPreferences Not LS


     */

    public RadialMenuView radialMenuView;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    } // end onCreate()

    // Show that radial menu
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
                Toast.makeText(this, "Welcome Home", Toast.LENGTH_SHORT).show();
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