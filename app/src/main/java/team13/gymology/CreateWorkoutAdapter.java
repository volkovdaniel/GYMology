package team13.gymology;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import exerciseData.gymology.Exercise;
import workoutData.gymology.Workout;
import workoutData.gymology.WorkoutController;

import java.util.List;

public class CreateWorkoutAdapter extends ArrayAdapter<Exercise> {
    Context actContext;
    int actResource;

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
                            ((CreateWorkout)actContext).addToUserWorkout(getItem(location));
                        }
                        System.out.println("Added Exercise");

                    } else {
                        if (actContext instanceof CreateWorkout) {
                            ((CreateWorkout)actContext).removeUserExercise(getItem(location));
                        }
                        System.out.println("Removed Exercise");
                    }
                }
            });
        } else {
            holder = (ViewHolder) alterView.getTag();
        }

        // Label each list item with it's name
        holder._workoutData.setText(getItem(location).getName());
        return alterView;
    }


}
