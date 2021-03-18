package team13.gymology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import profile.gymology.BMI;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);
    }

    // Testing layouts
    public void bmiCalc(View view) {
        Intent intent = new Intent(this, BMI.class);
        startActivity(intent);
    }
}