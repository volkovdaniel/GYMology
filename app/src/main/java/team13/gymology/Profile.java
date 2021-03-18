package team13.gymology;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import menu.semiradialmenu.RadialMenuView;
import profile.gymology.BMI;

public class Profile extends AppCompatActivity implements RadialMenuView.RadialMenuListener {

    RadialMenuView radialMenuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
    }

    @Override
    public void onItemClicked(int i) {
        switch (i) {
            case 0:
                // Profile Screen
                startActivity(new Intent(this, Profile.class));
                break;
            case 1:
                // Workouts DB Screen
                startActivity(new Intent(this, WorkoutDatabase.class));
                break;
            // Home Screen
            case 3:
                Toast.makeText(this, "Welcome Home", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                // New Workout
                startActivity(new Intent(this, CreateWorkout.class));
                break;
            case 5:
                // Calendar Screen
                startActivity(new Intent(this, Calendar.class));
                break;
        }

    }
    // Testing layouts
    public void bmiCalc(View view) {
        Intent intent = new Intent(this, BMI.class);
        startActivity(intent);
    }

    public void showClose(View view) {
        radialMenuView.show();
    }
}