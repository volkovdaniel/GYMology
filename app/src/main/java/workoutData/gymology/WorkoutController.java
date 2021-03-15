package workoutData.gymology;

import android.app.Activity;

import com.google.gson.Gson;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class WorkoutController {
//    private final List<String> _stringList;
//    private final Activity activity;
//    private final List<Workout> _nameList;
    private WorkoutList _workout_DB;
    private Workout _workout;
    private final String TAG = "Workout Controller: ";

    private final static Gson g = new Gson();

    public WorkoutController(WorkoutList workout) {
        _workout_DB = workout;
        _workout = new Workout();
    }
//    public WorkoutController(WeakReference<Activity> activity) {
//        this.activity = activity.get();
//        this._stringList = new ArrayList<>();
//        this._nameList = new ArrayList<>();
//    }

//    @Override
//    public void run() {
//        Log.d(TAG,
//                "run: Getting Wger API data");
//        try {
//            // Retrieve Data
//            _workout_DB = displayWorkouts();
//            for (Workout workout : _workout_DB.getWorkout()) {
//                _stringList.add(workout.getName());
//            }
//            Thread.sleep(300);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//        if (activity != null) {
//            // Define whole list
//            _viewList = activity.findViewById(R.id.list_stats);
//
//
////            // create adapter for list elements
////            _arrayAdapter = (new ArrayAdapter<>(
////                    activity.getApplicationContext(),
////                    R.layout.list_style_buttons_text,
////                    R.id.list_data,
////                    _stringList));
//            ExerciseAdapter adapter = new ExerciseAdapter(activity,
//                    R.layout.list_style_buttons_text, _exerciseList.getExercise());
//
//            new Handler(Looper.getMainLooper()).post(() -> {
//                _viewList.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//
//            });
//        }
//    } // End of run()
//
//    public Map<String,Workout> displayWorkouts() {
//
//    }
}
