package team13.gymology;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

// imports for radial menu
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
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

        findViewById(R.id.btn_savedW).setOnClickListener(v -> savedWorkouts());

        // Testing radial menu
        radialMenuView = findViewById(R.id.radial_menu_view);
        button = findViewById(R.id.button);

        MenuItemView itemOne = new MenuItemView(this ,"Soru Sor",R.drawable.ic_profile_white, R.color.orange);
        MenuItemView itemTwo = new MenuItemView(this,"Arkadaşlar",R.drawable.ic_babies_calendar, R.color.green);
        MenuItemView itemThree = new MenuItemView(this,"Galeri", R.drawable.ic_drawer_settings, R.color.vividPurple);
        MenuItemView itemFour = new MenuItemView(this,"Naber", R.drawable.ic_blog_white, R.color.darkRed);
        MenuItemView itemFive = new MenuItemView(this, "Selam", R.drawable.ic_profile_white, R.color.darkGreen2);
        ArrayList<MenuItemView> items = new ArrayList<>();
        items.add(itemOne);
        items.add(itemTwo);
        items.add(itemThree);
        items.add(itemFour);
        items.add(itemFive);
        radialMenuView.setListener(this).setMenuItems(items).setCenterView(button).setInnerCircle(true, R.color.white).setOffset(10).build();
        // end test
    }

    // Testing radial menu
    public void showClose(View view) {
        radialMenuView.show();
    }

    @Override
    public void onItemClicked(int i) {
        Toast.makeText(this, "Item clicked - " + String.valueOf(i), Toast.LENGTH_SHORT).show();
    }
    // end test

    public void savedWorkouts() {
        // Start Activity
        startActivity(new Intent(this, SavedWorkouts.class));
    }


}