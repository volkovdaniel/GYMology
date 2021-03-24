package team13.gymology;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


/*
Done Get exercise name attached to URL to work
 */

public class ExerciseVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_video);

        View inflatedView = getLayoutInflater().inflate(R.layout.exercise_details, null);
        TextView eName = (TextView) inflatedView.findViewById(R.id.exerciseName);

        String enStr = eName.getText().toString();
        enStr = enStr.replaceAll("\\s", "");
        final WebView webView = findViewById(R.id.webView);

        webView.loadUrl(
                "https://www.youtube.com/results?search_query=" + enStr);
    }
}




