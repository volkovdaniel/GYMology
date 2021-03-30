package team13.gymology;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import menu.semiradialmenu.MenuItemView;
import menu.semiradialmenu.RadialMenuView;

public class ExerciseDetails extends AppCompatActivity implements RadialMenuView.RadialMenuListener {

    public RadialMenuView radialMenuView;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_details);

        // Setting Back Button on Log Exercise
        findViewById(R.id.btn_saveNewWO).setOnClickListener(View -> super.onBackPressed());

        //Radial Menu Creation
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
        // End of Radial Menu Creations
    }

    public void stopwatch(View view) {
        Intent intent = new Intent(this, Stopwatch.class);
        startActivity(intent);
    }

    public void timer(View view) {
        Intent intent = new Intent(this, Timer.class);
        startActivity(intent);
    }

    public void exerciseVideo(View view) {
        TextView eName = findViewById(R.id.exerciseName);
        String enStr = eName.getText().toString();
        final WebView webView = findViewById(R.id.webView);
        webView.loadUrl("https://www.youtube.com/results?search_query=" + enStr);
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