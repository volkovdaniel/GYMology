package team13.gymology;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import menu.semiradialmenu.RadialMenuView;

public class WorkoutDetails extends AppCompatActivity implements RadialMenuView.RadialMenuListener {
    RadialMenuView radialMenuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_details);
    }

    // testing screens
    public void exerciseView(View view) {
        Intent intent = new Intent(this, LogExercise.class);
        startActivity(intent);
    }

    public void showClose(View view) {
        radialMenuView.show();
    }

    public void onItemClicked(int i) {
        Toast.makeText(this, String.valueOf(i), Toast.LENGTH_SHORT).show();
    }
}