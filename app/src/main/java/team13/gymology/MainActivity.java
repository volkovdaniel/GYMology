package team13.gymology;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import menu.semiradialmenu.MenuItemView;
import menu.semiradialmenu.RadialMenuView;
import workoutData.gymology.Workout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadialMenuView.RadialMenuListener {


    /*
    TODO: Save any updated workouts/exercises onStop() of app
     */
    // Testing radial menu
    RadialMenuView radialMenuView;
    Button button;
    // end test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //       findViewById(R.id.btn_savedW).setOnClickListener(v -> savedWorkouts());
//        radialMenuView.createRadialMenu();

        // Testing radial menu
        radialMenuView = findViewById(R.id.radial_menu_view);
        button = findViewById(R.id.button);

        MenuItemView itemOne = new MenuItemView(this, "Profile", R.drawable.ic_person, R.color.grayWeb);
        MenuItemView itemTwo = new MenuItemView(this, "Workouts", R.drawable.ic_fitness, R.color.prussianBlue);
        MenuItemView itemThree = new MenuItemView(this, "Home", R.drawable.ic_home, R.color.grayWeb);
        MenuItemView itemFour = new MenuItemView(this, "Stats", R.drawable.ic_stats,
                R.color.prussianBlue);
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
                startActivity(new Intent(this, CreateWorkout.class));
                break;
            case 1:
                // Workouts DB Screen
                startActivity(new Intent(this, WorkoutDatabase.class));
                break;
            case 2:
                // Home Screen
                Toast.makeText(this, "Welcome Home", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                // Statistics
                startActivity(new Intent(this, Statistics.class));
                break;
            case 4:
                // Calendar Screen
                startActivity(new Intent(this, Stopwatch.class));
                break;
        }
    } // end OnItemClicked()


}