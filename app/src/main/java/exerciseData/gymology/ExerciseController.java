package exerciseData.gymology;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ListView;
import com.google.gson.Gson;
import team13.gymology.R;

import java.io.*;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExerciseController implements Runnable {
    private final static Gson g = new Gson();
    private static String query;
    private final String TAG = "ExerciseController";
    public List<String> _stringList;
    ListView _viewList;
    List<Exercise> _nameList;
    private Activity activity;
    private ExerciseList _exerciseList;

    /**
     * ExerciseController
     * Purpose: Saves the exercise object for later use and retrieval
     *
     * @param exercise
     */
    public ExerciseController(ExerciseList exercise) {
        this._exerciseList = exercise;
    }

    public ExerciseController(WeakReference<Activity> activity, String type) {
        this.activity = activity.get();
        this._stringList = new ArrayList<>();
        this._nameList = new ArrayList<>();
    }

    /**
     * Getter Functions
     * Purpose: Getters for the  muscle, and equipment categories.
     *
     * @return Lists
     * @throws IOException
     */
    public static SimpleList getMuscleList() throws IOException {
        return g.fromJson(connectAPI(
                URLData.MUSCLE.getData() + URLData.QUERY_DEFAULT.getData()),
                SimpleList.class);
    }

    public static SimpleList getEquipmentList() throws IOException {
        return g.fromJson(connectAPI(
                URLData.EQUIPMENT.getData() + URLData.QUERY_DEFAULT.getData()),
                SimpleList.class);
    }

    public static void saveExercise(Context context, Exercise exercise) throws IOException {
        Log.d("Exercise Controller:", " Saving file");
        // Get Files directory, and the new file name
        File file = new File(context.getFilesDir(), exercise.getId());
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(exercise.getExercise());
        bufferedWriter.close();
    }

    public static Exercise loadExercise(Context context, Exercise exercise) throws IOException {
        Log.d("Exercise Controller: ", "Loading file");
        File file = new File(context.getFilesDir(), exercise.getId());
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return g.fromJson(bufferedReader, Exercise.class);
    }

    public static void clearExercise(Context context, String id) {
        context.deleteFile(id);
    }

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

    /**
     * displayExercises
     * Purpose: Connects to the Wger API to retrieve data for the creation of Exercise Lists
     */
    public static ExerciseList displayExercises() {
        /*
        Database calls:
        -categories contains the names for groups of muscles
        -exercise_db contains all exercises, pages of them

        Todo: Process multiple pages from one category.
        DONE: Pass in the value that selects the category from an onclick event
        Todo: Use given pictures from muscle category
        Todo: Process youtube links, and see if most exercises have them.

        Ideas:
        -Call a separate function to assemble the query, since this functions purpose is display

         */

        ExerciseController exercise_CB = null;
        try {
            // Retrieve Exercise categories data
//            String response =
//                    connectAPI(URLData.CATEGORIES.getData() + URLData.QUERY_DEFAULT.getData());


            // Specific exercises from category
            /*
            10 - abs
            8 - arms
            12 - back
            14 - calves
            11 - chest
            9 - legs
            13 - shoulders
             */
            query = String.format("?%s&limit=40&%s",
                    URLData.LANGUAGE.getData(),
                    URLData.CATEGORY_SINGLE.getData() + "9");

            // Retrieve exercises from category
            String response =
                    connectAPI(URLData.EXERCISE_DB.getData() + query);
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

            // Create exercise database by category
            exercise_CB = generateList(response);

            // Obtain Muscle and Equipment Lists
            final List<ExtraItems> muscle_DB = getMuscleList().getListItems();
            final List<ExtraItems> equipment_DB = getEquipmentList().getListItems();


            /*
            Idea:
            -Make the following for loops into a function. They repeat a lot of code. Not
            sure if it is worth it to make into a function though.
             */

            // Loop through Exercises to retrieve names of each item of equipment and muscles.
            for (Exercise exercise : exercise_CB._exerciseList.getExercise()) {
                List<String> equipmentList = exercise.getEquipmentList();
                List<String> muscleList = exercise.getMuscleList();
                List<String> secondaryMList = exercise.getSecondaryMList();

                // Check if Lists are empty
                if (!(equipmentList.isEmpty())) {
                    // Loop through all items in equipment
                    for (String i : equipmentList) {
                        for (ExtraItems value : equipment_DB) {
                            if (value.id.equals(i)) {
                                equipmentList.set(equipmentList.indexOf(i), value.name);
                            }
                        }
                    }
                }
                if (!(muscleList.isEmpty())) {
                    // Loop through all items in muscleList
                    for (String i : muscleList) {
                        for (ExtraItems value : muscle_DB) {
                            if (value.id.equals(i)) {
                                muscleList.set(muscleList.indexOf(i), value.name);
                            }
                        }
                    }
                }
                if (!(secondaryMList.isEmpty())) {
                    // Loop through all items in secondaryMList
                    for (String i : secondaryMList) {
                        for (ExtraItems value : muscle_DB) {
                            if (value.id.equals(i)) {
                                secondaryMList.set(secondaryMList.indexOf(i), value.name);
                            }
                        }
                    }
                }
                exercise.setEquipmentList(equipmentList);
                exercise.setMuscleList(muscleList);
                exercise.setSecondaryMList(secondaryMList);
            }
//            for (Exercise exercise : exercise_CB._exerciseList.getExercise()) {
//                System.out.format("" +
//                                "Exercise: %s%n" +
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
            return exercise_CB._exerciseList;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        assert exercise_CB != null;
        return exercise_CB._exerciseList;

    } // End of displayExercises()

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
        try {
            // Retrieve Data
            _exerciseList = displayExercises();
            for (Exercise exercise : _exerciseList.getExercise()) {
                _stringList.add(exercise.getName());
            }
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if (activity != null) {
            // Define whole list
            _viewList = activity.findViewById(R.id.list_exercises);


            // create adapter for list elements
//            _arrayAdapter = (new ArrayAdapter<>(
//                    activity.getApplicationContext(),
//                    R.layout.list_style_buttons_text,
//                    R.id.list_data,
//                    _stringList));
            ExerciseAdapter adapter = new ExerciseAdapter(activity,
                    R.layout.list_items_new_workout, _exerciseList.getExercise());

            new Handler(Looper.getMainLooper()).post(() -> {
                _viewList.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            });
        }
    }


}

