package workoutData.gymology;

import android.app.Activity;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import exerciseData.gymology.Exercise;


import java.io.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class WorkoutController {
    private List<String> _stringList;
    private Activity activity;
    private List<Workout> _nameList;
    private WorkoutList _workout_DB;
    private Workout _workout;
    private final static String TAG = "Workout Controller: ";

    private final static Gson g = new Gson();

    public WorkoutController(WorkoutList workout) {
        _workout_DB = workout;
        _workout = new Workout();
    }
    public WorkoutController(WeakReference<Activity> activity) {
        this.activity = activity.get();
        this._stringList = new ArrayList<>();
        this._nameList = new ArrayList<>();
    }


//    @Override
//    public void run() {
//        Log.d(TAG,
//                "run: Getting Workout DataBase");
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

    public static Workout loadWorkout(Context context, Workout workout) throws IOException {
        Log.d(TAG, "Loading Workout file");
        File file = new File(context.getFilesDir(), workout.get_name());
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return g.fromJson(bufferedReader,Workout.class);
    }

    public static void saveWorkout(Context context, Workout workout) throws IOException {
        Log.d(TAG," Saving Workout file");
        // Get Files directory, and the new file name
        File file = new File(context.getFilesDir(), workout.get_name());
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(g.toJson(workout));

        System.out.format(g.toJson(workout));

        bufferedWriter.close();
    }
//
//    public Map<String,Workout> displayWorkouts() {
//
//    }
}
