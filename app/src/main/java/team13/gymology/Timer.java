package team13.gymology;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import menu.semiradialmenu.MenuItemView;
import menu.semiradialmenu.RadialMenuView;

import java.util.ArrayList;
import java.util.Locale;

public class Timer extends AppCompatActivity implements RadialMenuView.RadialMenuListener {

    public RadialMenuView radialMenuView;
    public Button button;
    private static final long START_TIME_IN_MILLIS = 600000;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDownText();

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

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
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
