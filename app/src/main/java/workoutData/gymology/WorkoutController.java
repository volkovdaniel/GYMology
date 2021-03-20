package workoutData.gymology;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import utilities.gymology.Actions;
import utilities.gymology.Types;

import java.io.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


        /*
        TODO: Make edit functions
        TODO: LogPackage, Statistics Package
         */

public class WorkoutController implements Runnable {
    //Static Variables
    // Final Static Variables
    private final static String TAG = "Workout Controller: ";
    private final static Gson g = new Gson();
    // Private
    private List<String> _stringList;
    private Activity activity;
    private List<Workout> _nameList;
    private WorkoutList _workout_DB;
    private Workout _userWorkout;
    private Actions _doThis;
    private File _db_path;
    private File _db_cardio;
    private File _db_weights;
    private File _db_hiit;
    private Boolean _redo;


    public WorkoutController() {
    }

    public WorkoutController(WorkoutList workoutList) {
        _workout_DB = workoutList;
    }

    public WorkoutController(WeakReference<Activity> activity, Workout userWorkout,
                             Actions doThis) {
        this.activity = activity.get();
        this._stringList = new ArrayList<>();
        this._nameList = new ArrayList<>();
        this._userWorkout = userWorkout;
        this._doThis = doThis;
    }

//    public void editWorkout(Workout workout) {
//        startActivity(new Intent(this.activity.getApplicationContext(), EditWorkout.class));
//    }


//     Couldn't figure out how to save files to the directory without context being included.
//     Might not need to if the data base of workouts is already loaded.

//    public void checkDirLayout(Context context) throws IOException {
//        Log.d(TAG, "Creating new directories if they do not exist");
//        File newDir = context.getDir("SavedWorkouts", Context.MODE_PRIVATE);
//        File[] files = newDir.listFiles();
//        if (files != null) {
//            if (files.length != 0) {
//                _db_cardio = files[0];
//                _db_weights = files[1];
//                _db_hiit = files[2];
//            } else {
//                _db_cardio = new File(newDir, "cardio");
//                _db_weights = new File(newDir, "weights");
//                _db_hiit = new File(newDir, "hiit");
//            }
//        }
//    }

    public static Workout loadWorkout(Context context, Workout workout) throws IOException {
        Log.d("Exercise Controller: ", "Loading file");
        File file = new File(context.getFilesDir(), workout.get_name());
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return g.fromJson(bufferedReader, Workout.class);
    }

    public static void displayWorkoutByType(Types type) {
        switch (type) {
            case CARDIO:
                Log.d(TAG, "Loading cardio workouts");
                break;
            case HIIT:
                Log.d(TAG, "Loading HIIT workouts");
                break;
            case WEIGHTS:
                Log.d(TAG, "Loading strength workouts");
                break;
            default:
                Log.d(TAG, "Error: Unknown workout type");
                break;


        }


    }

    /**
     * checkFileName
     *
     * @param context current activity context
     * @param edit    if editing file instead
     */
    public String checkFileName(Context context, Boolean edit) throws IOException {
        Log.d(TAG, "Creating new directories if they do not exist");
        _db_path = context.getFilesDir();
        File[] files = _db_path.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(_userWorkout.get_name()) && !edit) {
                    _redo = true;
                    return String.format("Workout: %s already exists.", _userWorkout.get_name());
                } else {
                    _redo = false;
                }
            }
        }
        return String.format("Workout: %s being saved.", _userWorkout.get_name());
    }

    /**
     * saveWorkout
     * Stores the user's workout in Local Storage unless the workout named file already exists.
     *
     * @return String for toast message
     * @throws IOException
     */
    public void saveWorkout(Context context) throws IOException {
        Log.d(TAG, "Saving Workout to file");

        if (_db_path.setWritable(true)) {
            File file = new File(context.getFilesDir(), _userWorkout.get_name());
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(g.toJson(_userWorkout.getWorkout()));
            bufferedWriter.close();
        }
    }

//    public List<Workout> displayWorkouts() {
//        //Load Workout
//        _workout_DB
//
//
//    }

    @Override
    public void run() {
        Log.d(TAG, "Checking if File structure is in place");


        // DONE: Check if we're saving data, loading it, loading db, or loading category
        switch (_doThis) {
            case SAVE:
                // Save user workout
                Log.d(TAG, "Saving User Workout");
                activity.runOnUiThread(() -> {
                    try {
                        Toast.makeText(activity,
                                checkFileName(activity.getApplicationContext(), false), Toast.LENGTH_LONG).show();
                        if (_redo) {
                            saveWorkout(activity.getApplicationContext());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                break;
            case LOAD:
                //Load user workout
                Log.d(TAG, "Loading user workout");
                break;
            case LOAD_DB:
                // Load workout database
                Log.d(TAG, "Loading workout database");
                break;
            case LOAD_CAT:
                // Load workouts database by category
                Log.d(TAG, "Loading workout database by category");
                break;
            default:
                Log.d(TAG, "No options were found in the run()'s switch, please debug");
        }


//        try {
//            // Retrieve Data
//            _workout_DB = displayWorkouts();
//            for (Workout workout : _workout_DB.getWorkout()) {
//                _stringList.add(workout.get_name());
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
//            CreateWorkoutAdapter adapter = new CreateWorkoutAdapter(activity,
//                    R.layout.list_style_buttons_text, _exerciseList.getExercise());
//
//            new Handler(Looper.getMainLooper()).post(() -> {
//                _viewList.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//
//            });
//        }
    } // End of run()
}
