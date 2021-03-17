package team13.gymology;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import exerciseData.gymology.ExerciseController;
import menu.semiradialmenu.RadialMenuView;

import java.lang.ref.WeakReference;

public class CreateWorkout extends AppCompatActivity implements RadialMenuView.RadialMenuListener {

    RadialMenuView radialMenuView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_workouts);

//        Resource to add button functionality on each list item
//        https://stackoverflow.com/questions/12596199/android-how-to-set-onclick-event-for-button-in-list-item-of-listview

        retrieveWgerAPI(new WeakReference<>(this));



    }
        private void retrieveWgerAPI(WeakReference<Activity> weakActivity) {
        // Create  and start thread
        ExerciseController exercise = new ExerciseController(weakActivity, "category");
        Thread t1 = new Thread(exercise);
        t1.start();

    }
    public void showClose(View view) {
        radialMenuView.show();
    }

    public void onItemClicked(int i) {
        Toast.makeText(this, String.valueOf(i), Toast.LENGTH_SHORT).show();
    }
}