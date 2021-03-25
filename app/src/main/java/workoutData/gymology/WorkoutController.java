package workoutData.gymology;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import team13.gymology.R;
import utilities.gymology.Actions;
import utilities.gymology.Types;

import java.io.*;
import java.lang.ref.WeakReference;


        /*
        TODO: Make edit functions
        TODO: LogPackage, Statistics Package
         */

public class WorkoutController implements Runnable {
    // Final Static Variables
    private final static String TAG = "Workout Controller: ";
    private final static Gson g = new Gson();
    // Private
    private Activity activity;
    private WorkoutList _workout_DB;
    private Workout _userWorkout;

    private Actions _doThis;
    private File _db_path;
    private Boolean _save;
    private ListView _DBview;


    public WorkoutController() {
        _userWorkout = new Workout();
    }

    public WorkoutController(WeakReference<Activity> activity, Workout userWorkout,
                             Actions doThis) {
        this.activity = activity.get();
        this._userWorkout = userWorkout;
        this._doThis = doThis;
    }
    public WorkoutController(WeakReference<Activity> activity, Actions doThis) {
        this.activity = activity.get();
        this._workout_DB = new WorkoutList();
        this._doThis = doThis;
    }

//    public void editWorkout(Workout workout) {
//        startActivity(new Intent(this.activity.getApplicationContext(), EditWorkout.class));
//    }

    /**
     * ClearWorkout
     * Method that removes the workout on file from local storage
     * @param context workout database activity
     * @param id name of the file
     */
    public static void clearWorkout(Context context, String id) {
        context.deleteFile(id);
    }

//    public static Workout loadWorkout(Context context, Workout workout) throws IOException {
//        Log.d("Exercise Controller: ", "Loading file");
//        File file = new File(context.getFilesDir(), workout.get_name());
//        FileReader fileReader = new FileReader(file);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        return g.fromJson(bufferedReader, Workout.class);
//    }

//    /**
//     * DisplayWorkoutByType
//     * Method to display workouts by type
//     * @param type
//     */
//    public static void displayWorkoutByType(Types type) {
//        switch (type) {
//            case CARDIO:
//                Log.d(TAG, "Loading cardio workouts");
//                break;
//            case HIIT:
//                Log.d(TAG, "Loading HIIT workouts");
//                break;
//            case WEIGHTS:
//                Log.d(TAG, "Loading strength workouts");
//                break;
//            default:
//                Log.d(TAG, "Error: Unknown workout type");
//                break;
//        }
//    }

    /**
     * GrabWorkoutFiles
     * Retrieves all files in local storage stores them for database retrieval.
     * @param context
     * @throws IOException
     */
    public void grabWorkoutFiles(Context context) throws IOException {
        Log.d(TAG, "Grabbing Files from LS");
        File newDir = context.getFilesDir();
        File[] files = newDir.listFiles();
        assert files != null;
        for (File file : files) {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            _workout_DB.addTo_DB(g.fromJson(bufferedReader, Workout.class));
        }
    }

    /**
     * CheckFileName
     * Method to check if file names already exist. If they do, created workout will
     * not be saved.
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
                    _save = false;
                    return String.format("Workout: %s already exists.", _userWorkout.get_name());
                }
            }
        }
        _save = true;
        return String.format("Workout: %s being saved.", _userWorkout.get_name());
    }

    /**
     * saveWorkout
     * Stores the user's workout in Local Storage.
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
            String jsonF = g.toJson(_userWorkout);
            System.out.println(jsonF);
            bufferedWriter.write(g.toJson(_userWorkout));
            bufferedWriter.close();
        }
    }

//    /**
//     * LoadWorkout
//     * Loads a single workout from Local Storage. Generally used for editing a workout.
//     * @param context
//     * @throws IOException
//     */
//    public void loadWorkout(Context context) throws IOException {
//        Log.d(TAG, "Loading Workout from LS");
//
//        if (_db_path.setWritable(true)) {
//            File file = new File(context.getFilesDir(), _userWorkout.get_name());
//            FileWriter fileWriter = new FileWriter(file);
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            bufferedWriter.write(g.toJson(_userWorkout));
//            bufferedWriter.close();
//        }
//    }

    @Override
    public void run() {
        Log.d(TAG, "Checking if File structure is in place");
        switch (_doThis) {
            case SAVE:
                // Save user workout
                Log.d(TAG, "Saving User Workout");
                activity.runOnUiThread(() -> {
                    try {
                        Toast.makeText(activity,
                                checkFileName(activity.getApplicationContext(), false), Toast.LENGTH_LONG).show();
                        if (_save) {
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
                try {
                    // Grab Database
                    grabWorkoutFiles(activity.getApplication());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (_workout_DB.get_workoutList().size() != (0)) {
                    assert activity != null;
                    _DBview = activity.findViewById(R.id.list_DB_exercises);
                    // create adapter for list elements
                    WorkoutAdapter adapter = new WorkoutAdapter(activity,
                            R.layout.list_items_workout_details,
                            _workout_DB.get_workoutList());

                    // Display to Designated Activity
                    new Handler(Looper.getMainLooper()).post(() -> {
                        _DBview.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    });
                } else {
                    activity.runOnUiThread(() -> Toast.makeText(activity.getApplicationContext(),
                            "Oh no, looks like there aren't any saved workouts.",
                            Toast.LENGTH_LONG).show());
                }
                break;
//            case LOAD_CAT:
//                // Load workouts database by category
//                Log.d(TAG, "Loading workout database by category");
//                break;
            default:
                Log.d(TAG, "No options were found in the run()'s switch, please debug");
        }
    } // End of run()
}
