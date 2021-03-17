package team13.gymology;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

// imports for radial menu
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import exerciseData.gymology.ExerciseController;
import menu.semiradialmenu.MenuItemView;
import menu.semiradialmenu.RadialMenuView;
import menu.semiradialmenu.Utils;

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

        // Testing radial menu
        radialMenuView = findViewById(R.id.radial_menu_view);
        button = findViewById(R.id.button);

        MenuItemView itemOne = new MenuItemView(this ,"Profile",R.drawable.ic_person, R.color.grayWeb);
        MenuItemView itemTwo = new MenuItemView(this,"Workouts",R.drawable.ic_fitness, R.color.prussianBlue);
        MenuItemView itemThree = new MenuItemView(this,"Home", R.drawable.ic_home, R.color.grayWeb);
        MenuItemView itemFour = new MenuItemView(this,"Stats", R.drawable.ic_stats, R.color.prussianBlue);
        MenuItemView itemFive = new MenuItemView(this, "Calendar", R.drawable.ic_calendar, R.color.grayWeb);
        ArrayList<MenuItemView> items = new ArrayList<>();
        items.add(itemOne);
        items.add(itemTwo);
        items.add(itemThree);
        items.add(itemFour);
        items.add(itemFive);
        radialMenuView.setListener(this).setMenuItems(items).setCenterView(button).setInnerCircle(true,
                R.color.white).setOffset(10).build();
//        ArrayList<View> menuViews = getRootView().getFocusables((R.id.btn_menu));
//        if (radialMenuView.isOpen) {
//            for (View menu : menuViews) {
//                menu.setOnClickListener(v -> {
//                    Toast.makeText(getContext(),
//                            ":::DO IT:::::",
//                            Toast.LENGTH_SHORT).show();
//                });
//            }
//        }
        // end test
    }

    // Testing radial menu
    public void showClose(View view) {
        radialMenuView.show();
    }

    @Override
    public void onItemClicked(int i) {
        switch (i) {
            case 0:
                savedWorkouts(new WeakReference<>(this));
                break;
            case 1:
                savedWorkouts(new WeakReference<>(this));
                break;
        }
    }
//     end test

    public void savedWorkouts(WeakReference<Activity> weakActivity) {
        // Start Activity
        startActivity(new Intent(this, SavedWorkouts.class));
//        ExerciseController exercise = new ExerciseController(weakActivity, "category");
//        Thread t1 = new Thread(exercise);
//        t1.start();
    }


    public void statistics() {
        // Start Activity
        startActivity(new Intent(this, Statistics.class));
    }

}