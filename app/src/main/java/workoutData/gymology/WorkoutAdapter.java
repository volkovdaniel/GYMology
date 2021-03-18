package workoutData.gymology;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import team13.gymology.Profile;
import team13.gymology.R;

import java.io.IOException;
import java.util.List;

public class WorkoutAdapter extends ArrayAdapter<Workout> {
    Context actContext;
    int actResource;

    public WorkoutAdapter(@NonNull Context context, int resource, @NonNull List<Workout> objects) {
        super(context,resource,objects);
        actContext = context;
        actResource = resource;
    }

    public static class ViewHolder {
        RelativeLayout _listLayout;
        TextView _workoutData;
        Button _addButton;
        Button _clearButton;
        Button _editButton;
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
            TODO: Replace Toasts with moving to the respective activity
            TODO: Add checking for if file exists before trying to load/save it -> msg the user
            TODO: Make compatible for Workouts, and generalize the adapter

             */
            // Set Listeners to each button for each list item
            holder._editButton.setOnClickListener(view -> {
//                try {
//                    (new Intent(actContext, EditWorkout.class));
//                    // Retrieves Workout from json file by id as a Workout object
//                    Workout workout = WorkoutController.loadWorkout(actContext,
//                            getItem(location));
                    Toast.makeText(actContext,
                            String.format("Loaded: %s", getItem(location).get_name()),
                            Toast.LENGTH_SHORT).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            });

//            holder._addButton.setOnClickListener(view -> {
//                try {
//                    // getItem is essentially accessing the current workout in the list
//                    WorkoutController.saveWorkout(actContext, getItem(location));
//                    Toast.makeText(actContext,
//                            String.format("Saved: %s", getItem(location).getName()), Toast.LENGTH_SHORT).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });

//            holder._clearButton.setOnClickListener(view -> {
//                // Deletes workout in localStorage
//                WorkoutController.clearWorkout(actContext,getItem(location).getId());
//                Toast.makeText(actContext,getItem(location).getId(), Toast.LENGTH_SHORT).show();
//            });
//
//            alterView.setTag(holder);
        }
        else {
            holder = (ViewHolder)alterView.getTag();
        }

//        holder._workoutData.setText(getItem(location).getName());
        return alterView;
   }
}
