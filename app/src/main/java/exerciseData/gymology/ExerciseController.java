package exerciseData.gymology;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ListView;
import com.google.gson.Gson;
import team13.gymology.CreateWorkout;
import team13.gymology.CreateWorkoutAdapter;
import team13.gymology.R;
import team13.gymology.WorkoutDetails;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExerciseController implements Runnable {
    // Public
    public List<String> _stringList;
    ListView _viewList;
    List<Exercise> _nameList;
    // Private
    private Activity activity;
    private ExerciseList _exerciseList;
    private int category;
    private ExerciseGroup _exerciseGroup;
    // Final
    private final static Gson g = new Gson();
    private final String TAG = "ExerciseController";


    /**
     * ExerciseController
     * Purpose: Saves the exercise object for later use and retrieval
     *
     * @param exercise
     */
    public ExerciseController(ExerciseList exercise) {
        this._exerciseList = exercise;
    }

    public ExerciseController(WeakReference<Activity> activity, int category) {
        this.activity = activity.get();
        this._stringList = new ArrayList<>();
        this._nameList = new ArrayList<>();
        this.category = category;
    }

    /**
     * ExerciseController
     *
     * Displays the user's current selected exercise list
     *
     * @param activity
     * @param exerciseGroup The class containing the Map of exercises
     */
    public ExerciseController(WeakReference<Activity> activity,
                              ExerciseGroup exerciseGroup) {
        this.activity = activity.get();
        this._exerciseGroup = exerciseGroup;
    }

    /**
     * Getter Functions
     * Purpose: Getters for the  muscle, and equipment categories.
     *
     * @return Lists
     * @throws IOException
     */

    /*
    Separate possible calls to the api for retrieving based on muscles, or equipment
     */
//    public static SimpleList getMuscleList() throws IOException {
//        return g.fromJson(connectAPI(
//                URLData.MUSCLE.getData() + URLData.QUERY_DEFAULT.getData()),
//                SimpleList.class);
//    }

//    public static SimpleList getEquipmentList() throws IOException {
//        return g.fromJson(connectAPI(
//                URLData.EQUIPMENT.getData() + URLData.QUERY_DEFAULT.getData()),
//                SimpleList.class);
//    }

    /*
    Saves an exercise to local storage. Could be turned into prepping the display
    exercises to save on loading lag.
     */
//    public static void saveExercise(Context context, Exercise exercise) throws IOException {
//        Log.d("Exercise Controller:", " Saving file");
//        // Get Files directory, and the new file name
//        File file = new File(context.getFilesDir(), exercise.getId());
//        FileWriter fileWriter = new FileWriter(file);
//        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//        bufferedWriter.write(exercise.getExercise());
//        bufferedWriter.close();
//    }

    /*
    Simply Loads exercises from local storage
     */
//    public static Exercise loadExercise(Context context, Exercise exercise) throws IOException {
//        Log.d("Exercise Controller: ", "Loading file");
//        File file = new File(context.getFilesDir(), exercise.getId());
//        FileReader fileReader = new FileReader(file);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        return g.fromJson(bufferedReader, Exercise.class);
//    }

    /*
    Clears exercise files from storage
     */
//    public static void clearExercise(Context context, String id) {
//        context.deleteFile(id);
//    }

    /**
     * Method that makes a connection to an API
     *
     * @param url-made custom from
     * @return String response
     * @throws IOException -if problems occur while connecting
     * @see URLData enum
     */
    public static String connectAPI(String url) throws IOException {
        // Open Connection
        URLConnection connection =
                new URL(url).openConnection();

        // Display Json data to console with indents
        connection.setRequestProperty("Accept",
                URLData.ACCEPT.getData() + URLData.JSONINDENT.getData());

        // Process return stream
        Scanner scanner = new Scanner(connection.getInputStream());
        return scanner.useDelimiter("\\A").next();
    }

    /*
        Database calls:
        -categories contains the names for groups of muscles
        -exercise_db contains all exercises, pages of them

        Ideas:
        -Call a separate function to assemble the query, since this functions purpose is display

         */
        /* OUTPUTS:
            count - how many items its found, limit is currently set to 40 per page
            next - reference url to next page
            previous - reference url to previous page
            results[] {
            id - can be searched by id instead
            category -
            description - html tags included
            name - name of exercise
            muscles - sometimes empty <- separate call to display muscle data
            muscles_secondary - sometimes empty <- separate call to display muscle data
            equipment - int, sometimes multiple <- separate call to display equipment data
            language - should all be english, but might want to check to be sure
            variations - not sure how this is referencing other things <- separate call to display
            }
             */

    /**
     * displayExercises
     * Purpose: Connects to the Wger API to retrieve data for the creation of Exercise Lists
     */
    public ExerciseList displayExercises() {
        ExerciseController exercise_CB = null;
        try {
            // Create Query String
            // Static
            String query = String.format("?%s&limit=40&%s",
                    URLData.LANGUAGE.getData(),
                    URLData.CATEGORY_SINGLE.getData() + category);

            // Retrieve exercises from category
            String response =
                    connectAPI(URLData.EXERCISE_DB.getData() + query);


            // Create exercise database by category
            exercise_CB = generateList(response);
            /*
            Took out the following code because we aren't using it, and no need to
            waste extra space.

            Idea:
            -Make the following for loops into a function. They repeat a lot of code.
             */

//            // Obtain Muscle and Equipment Lists
//            final List<ExtraItems> muscle_DB = getMuscleList().getListItems();
//            final List<ExtraItems> equipment_DB = getEquipmentList().getListItems();

//            // Loop through Exercises to retrieve names of each item of equipment and muscles.
//            for (Exercise exercise : exercise_CB._exerciseList.getExercise()) {
//                List<String> equipmentList = exercise.getEquipmentList();
//                List<String> muscleList = exercise.getMuscleList();
//                List<String> secondaryMList = exercise.getSecondaryMList();
//
//                // Check if Lists are empty
//                if (!(equipmentList.isEmpty())) {
//                    // Loop through all items in equipment
//                    for (String i : equipmentList) {
//                        for (ExtraItems value : equipment_DB) {
//                            if (value.id.equals(i)) {
//                                equipmentList.set(equipmentList.indexOf(i), value.name);
//                            }
//                        }
//                    }
//                }
//                if (!(muscleList.isEmpty())) {
//                    // Loop through all items in muscleList
//                    for (String i : muscleList) {
//                        for (ExtraItems value : muscle_DB) {
//                            if (value.id.equals(i)) {
//                                muscleList.set(muscleList.indexOf(i), value.name);
//                            }
//                        }
//                    }
//                }
//                if (!(secondaryMList.isEmpty())) {
//                    // Loop through all items in secondaryMList
//                    for (String i : secondaryMList) {
//                        for (ExtraItems value : muscle_DB) {
//                            if (value.id.equals(i)) {
//                                secondaryMList.set(secondaryMList.indexOf(i), value.name);
//                            }
//                        }
//                    }
//                }
//                exercise.setEquipmentList(equipmentList);
//                exercise.setMuscleList(muscleList);
//                exercise.setSecondaryMList(secondaryMList);
//            }

            /*
            Outputs the contents of the call to system output.
             */
//            for (Exercise exercise : exercise_CB._exerciseList.getExercise()) {
//                System.out.format("" +
//                                "%nExercise: %s%n" +
//                                "ID: %s%n" +
//                                "Category: %s%n" +
//                                "Muscle Groups: %s,%s%n" +
//                                "Equipment: %s%n" +
//                                "Description: %s%n%n",
//                        exercise.getName(),
//                        exercise.getId(),
//                        exercise.getCategory(),
//                        exercise.getMuscleList(),
//                        exercise.getSecondaryMList(),
//                        exercise.getEquipmentList(),
//                        exercise.getDescription());
//            }
//            return exercise_CB._exerciseList;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        assert exercise_CB != null;
        return exercise_CB._exerciseList;

    } // End of displayExercises()

    public void displayExerciseGroup() {

    }

    /**
     * generateList
     * Purpose: Provides a list of json data to save as an object.
     *
     * @param response - json string provided from Gson
     * @return Exercise List by Category
     */
    public static ExerciseController generateList(String response) {
        Gson g = new Gson();
        // Deserialize response
        ExerciseList exercise = g.fromJson(response, ExerciseList.class);
        return new ExerciseController(exercise);
    }

    @Override
    public void run() {
        Log.d(TAG,
                "run: Getting Wger API data");
        assert activity != null;

        if (activity instanceof WorkoutDetails) {
            // Define whole list
            _viewList = activity.findViewById(R.id.routineExerciseList);

            // create adapter for list elements
            CreateWorkoutAdapter adapter = new CreateWorkoutAdapter(activity,
                    R.layout.list_items_new_workout, _exerciseGroup.get_group());

            // Display to Designated Activity
            new Handler(Looper.getMainLooper()).post(() -> {
                _viewList.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            });
        }
        else if (activity instanceof CreateWorkout) {
            try {
                // Retrieve Data
                _exerciseList = displayExercises();
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
                // Define whole list
                _viewList = activity.findViewById(R.id.list_exercises);

                // create adapter for list elements
                CreateWorkoutAdapter adapter = new CreateWorkoutAdapter(activity,
                        R.layout.list_items_new_workout, _exerciseList.getExercise());

                // Display to Designated Activity
                new Handler(Looper.getMainLooper()).post(() -> {
                    _viewList.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                });
        }
    }


}

