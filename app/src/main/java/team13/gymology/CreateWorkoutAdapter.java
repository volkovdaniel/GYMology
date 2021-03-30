package team13.gymology;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import exerciseData.gymology.Exercise;


import java.util.List;

public class CreateWorkoutAdapter extends ArrayAdapter<Exercise> {
    Context actContext;
    int actResource;
    final String TAG = "CreateWorkoutAdapter: ";

    public CreateWorkoutAdapter(@NonNull Context context, int resource, @NonNull List<Exercise> objects) {
        super(context, resource, objects);
        actContext = context;
        actResource = resource;
    }

    public static class ViewHolder {
        RelativeLayout _listLayout;
        TextView _workoutData;
        CheckBox _addButton;
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

            // Hold the items in each list item
            holder._listLayout = alterView.findViewById(R.id.list_layout);
            holder._workoutData = alterView.findViewById(R.id.list_data);
            holder._addButton = alterView.findViewById(R.id.cb_add);


            // Add Listener to each checkbox
            holder._addButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                /**
                 * Called when the checked state of a compound button has changed.
                 *
                 * @param buttonView The compound button view whose state has changed.
                 * @param isChecked  The new checked state of buttonView.
                 */
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (actContext instanceof CreateWorkout) {
                            Log.d(TAG, "Adding exercise to userWorkout");
                            ((CreateWorkout)actContext).addToUserWorkout(getItem(location));
                        }
                        else if (actContext instanceof WorkoutDetails) {
                            // Disable checkbox from activation again
                            buttonView.setEnabled(false);
                            // Package the Workout and send to new activity
                            Intent intent = new Intent(actContext, ExerciseDetails.class);
                            intent.putExtra("Exercise",
                                    (new Gson()).toJson(getItem(location)));
                            Log.d(TAG, "Starting new ExerciseDetails activity for " +
                                    "selected exercise");

                            // Starting Exercise Details Activity
                            actContext.startActivity(intent);
                        }

                    } else {
                        if (actContext instanceof CreateWorkout) {
                            Log.d(TAG, "Removing exercise to userWorkout");
                            ((CreateWorkout)actContext).removeUserExercise(getItem(location));
                        }
                    }
                }
            });

            // Label each list item with it's name
            System.out.println(getItem(location).getName());
            try {
                holder._workoutData.setText(getItem(location).getName());
            } catch (NullPointerException ex) {
                Log.d("Create Workout Adapter: ",ex.getMessage());
                ex.getStackTrace();
            }
            return alterView;
        } else {
            return alterView;
        }


    }


}
