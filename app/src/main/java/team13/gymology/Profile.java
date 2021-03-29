package team13.gymology;

import android.content.Intent;
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
import profile.gymology.BMI;

public class Profile extends AppCompatActivity implements RadialMenuView.RadialMenuListener {

    public RadialMenuView radialMenuView;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

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

    // Testing layouts
    public void bmiCalc(View view) {
        Intent intent = new Intent(this, BMI.class);
        startActivity(intent);
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