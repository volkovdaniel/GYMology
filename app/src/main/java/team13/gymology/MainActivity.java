package team13.gymology;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_savedW).setOnClickListener(v -> savedWorkouts());
    }

    public void savedWorkouts() {
        // Start Activity
        startActivity(new Intent(this, SavedWorkouts.class));
    }


}