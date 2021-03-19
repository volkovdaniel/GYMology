package workoutData.gymology;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;

import java.io.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class WorkoutController implements Runnable {
    // Final Static Variables
    private final static String TAG = "Workout Controller: ";
    private final static Gson g = new Gson();
    //Static Variables
    private static String db_cardio_fPath;
    private static String db_strength_fPath;
    private static String db_hiit_fPath;
    private List<String> _stringList;
    private Activity activity;
    private List<Workout> _nameList;
    private WorkoutList _workout_DB;
    private Workout _workout;

    public WorkoutController(WorkoutList workout) {
        _workout_DB = workout;
        _workout = new Workout();
    }

    public WorkoutController(WeakReference<Activity> activity) {
        this.activity = activity.get();
        this._stringList = new ArrayList<>();
        this._nameList = new ArrayList<>();
    }

    /*
    TODO: Make Utility package containing overloaded methods like loadWorkout, and adapter View's
     */
    public static Workout loadWorkout(Context context, Workout workout) throws IOException {
        Log.d(TAG, "Loading Workout file");
        File file = new File(context.getFilesDir(), workout.get_name());
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return g.fromJson(bufferedReader, Workout.class);
    }

//    public void editWorkout(Workout workout) {
//        startActivity(new Intent(this.activity.getApplicationContext(), EditWorkout.class));
//    }

    public static void saveWorkout(Context context, Workout workout) throws IOException {
        Log.d(TAG, " Saving Workout file");
        // Get Files directory, and the new file name
        File file = new File(context.getFilesDir(), workout.get_name());
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(g.toJson(workout));

        System.out.format(g.toJson(workout));

        bufferedWriter.close();
    }

    // Not sure if this works yet, building the save part to add workouts to ls
    public static void load_workout_DB(Context context) throws FileNotFoundException {
        Log.d(TAG, "Loading Workout Database");

        // Grab the directory Saved Workouts
        File newDir = context.getDir("SavedWorkouts", Context.MODE_PRIVATE);

        // Make the directory if it doesn't exist
        if (!newDir.exists()) {
            newDir.mkdirs();
        }

        // Grab Type Folders
        String db_path = newDir.getAbsolutePath();
        File db_cardio = context.getDir(db_path + "/cardio", Context.MODE_PRIVATE);
        File db_strength = context.getDir(db_path + "/strength", Context.MODE_PRIVATE);
        File db_hiit = context.getDir(db_path + "/hiit", Context.MODE_PRIVATE);

        // Make Folders if they don't exist
        if (!db_cardio.exists() && !db_strength.exists() && !db_hiit.exists()) {
            db_cardio.mkdirs();
            db_strength.mkdirs();
            db_hiit.mkdirs();
        }

        //File path names
        db_cardio_fPath = db_cardio.getAbsolutePath();
        db_strength_fPath = db_strength.getAbsolutePath();
        db_hiit_fPath = db_hiit.getAbsolutePath();

        //Get files in folders
        File[] cardioFiles = db_cardio.listFiles();
        File[] strengthFiles = db_cardio.listFiles();
        File[] hiitFiles = db_cardio.listFiles();

        // Files, display, and add listeners
        for (int i = 0; i < cardioFiles.length; i++) {
            FileReader fileReader = new FileReader(cardioFiles[i]);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            WorkoutList workoutList = g.fromJson(bufferedReader, WorkoutList.class);
            new WorkoutController(workoutList);
        }


    }

    public static void displayWorkoutByType(String type) {
        switch (type) {
            case "cardio":
                Log.d(TAG, "Loading cardio workouts");
                break;
            case "hiit":
                Log.d(TAG, "Loading HIIT workouts");
                break;
            case "strength":
                Log.d(TAG, "Loading strength workouts");
                break;
            default:
                Log.d(TAG, "Error: Unknown workout type");
                break;


        }


    }
        /*
        TODO: Make edit functions
        TODO: LogPackage, Statistics Package
         */
//    public List<Workout> displayWorkouts() {
//        //Load Workout
//        _workout_DB
//
//
//    }

    @Override
    public void run() {
        Log.d(TAG,
                "run: Getting Workout DataBase");
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
//            ExerciseAdapter adapter = new ExerciseAdapter(activity,
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
