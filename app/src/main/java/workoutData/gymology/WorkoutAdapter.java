package workoutData.gymology;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import team13.gymology.CreateWorkout;
import team13.gymology.R;
import team13.gymology.WorkoutDetails;

import java.util.List;

public class WorkoutAdapter extends ArrayAdapter<Workout> {
    Context actContext;
    int actResource;

    public WorkoutAdapter(@NonNull Context context, int resource, @NonNull List<Workout> objects) {
        super(context, resource, objects);
        actContext = context;
        actResource = resource;
    }

    @NonNull
    @Override
    public View getView(final int location, View alterView, ViewGroup group) {
        final ViewHolder holder;
        if (alterView == null) {
            // Grabs the context to inflate the layout, or take apart and manipulate the view item
            // Essentially places the view into an xml layout, to alter
            LayoutInflater inflater = LayoutInflater.from(actContext);
            alterView = inflater.inflate(actResource, group, false);
            holder = new ViewHolder();

            // Hold the items in the listview
            holder._listLayout = alterView.findViewById(R.id.list_layout);
            holder._workoutData = alterView.findViewById(R.id.list_data);
            holder._editButton = alterView.findViewById(R.id.btn_edit);
            holder._addButton = alterView.findViewById(R.id.btn_add);
            holder._clearButton = alterView.findViewById(R.id.btn_clear);


            /*
            DONE: Replace Toasts with moving to the respective activity
             */

            holder._workoutData.setText(getItem(location).get_name());

            // Set Listeners to each button for each list item
            holder._editButton.setOnClickListener(view -> {
//                try {
                Intent intent = new Intent(actContext, CreateWorkout.class);
                intent.putExtra("Edit", (new Gson()).toJson(getItem(location)));

                Toast.makeText(actContext,
                        String.format("Loaded: %s", getItem(location).get_name()),
                        Toast.LENGTH_SHORT).show();

                Log.d("Workout Adapter: ", "Editing current selected workout");

                (actContext).startActivity(intent);
            });

            holder._addButton.setOnClickListener(view -> {
                // Create new intent for Logging the workout selected
                Intent intent = new Intent(actContext, WorkoutDetails.class);
                intent.putExtra("Log", (new Gson()).toJson(getItem(location)));


                Toast.makeText(actContext,
                        String.format("Saved: %s", getItem(location).get_type()),
                        Toast.LENGTH_SHORT).show();

                Log.d("Workout Adapter: ", "Starting new WorkoutDetails Activity");

                (actContext).startActivity(intent);
            });

            holder._clearButton.setOnClickListener(view -> {
                // Deletes workout in localStorage
                WorkoutController.clearWorkout(actContext, getItem(location).get_name());
                // Clear List item
                this.remove(getItem(location));
                // Display confirmation toast
                ((Activity) actContext).runOnUiThread(() -> Toast.makeText(actContext,
                        "Cleared: " + getItem(location).get_name(),
                        Toast.LENGTH_SHORT).show());
            });
        } else {
            holder = (ViewHolder) alterView.getTag();
        }
        return alterView;
    }

    public static class ViewHolder {
        RelativeLayout _listLayout;
        TextView _workoutData;
        Button _addButton;
        Button _clearButton;
        Button _editButton;
    }
}
