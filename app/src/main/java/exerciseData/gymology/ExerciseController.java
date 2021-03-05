package exerciseData.gymology;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ExerciseController implements Runnable {
    private static String query;
    private Activity activity;
    private ExerciseList _exerciseList;
    private final static Gson g = new Gson();
    private Boolean _tempOnly;

    private final String TAG = "ExerciseController";

    /** ExerciseController
     * Purpose: Saves the exercise object for later use and retrieval
     * @param exercise
     */
    public ExerciseController(ExerciseList exercise) {
        this._exerciseList = exercise;
    }
    public ExerciseController(WeakReference<Activity> activity, Boolean tempOnly) {
        _tempOnly = tempOnly;
        this.activity = activity.get();
    }

    @Override
    public void run() {
        Log.d(TAG,
                "run: Getting Wger API data");
        try {
            // Check which data is to be retrieved
            if (_tempOnly) {
                _exerciseList = displayExercises();


                Log.d(TAG,
                        "run: DisplayExercises()");
                new Handler(Looper.getMainLooper()).post(() -> {
                    // Grab context of application, display toast
                    if (activity != null) {
                        Toast msg = Toast.makeText(activity.getApplicationContext(),
                                String.format(Locale.getDefault(), "Exercise's found: %s",
                                        this._exerciseList.getExercise().size()),
                                Toast.LENGTH_LONG);
                        msg.show();
                    }
                });
            }
            else {
                displayExercises();
            }
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
//        if (activity != null) {
//            // define list element
//            _displayForecast = activity.findViewById(R.id.displayForecast);
//
//            // create adapter, plus font to white
//            _arrayAdapter = (new ArrayAdapter<>(activity, R.layout.list_font_black, R.id.list_data,
//                    _forecastList
//
//            ));
//        }
//        if (_tempOnly) {
//            _forecastList.add(_temp);
//        }
//
//        new Handler(Looper.getMainLooper()).post(() -> {
//            _displayForecast.setAdapter(_arrayAdapter);
//            _arrayAdapter.notifyDataSetChanged();
//
//        });
    }

    /**Getter Functions
     * Purpose: Getters for the  muscle, and equipment categories.
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
    /**
     * Method that makes a connection to an API
     * @param url-made custom from
     * @see URLData enum
     * @return String response
     * @throws IOException -if problems occur while connecting
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

    /**displayExercises
     * Purpose: Connects to the Wger API to retrieve data for the creation of Exercise Lists
     */
    public static ExerciseList displayExercises() {
        /*
        Database calls:
        -categories contains the names for groups of muscles
        -exercise_db contains all exercises, pages of them

        Todo: Process multiple pages from one category.
        Todo: Pass in the value that selects the category from an onclick event
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
            for (Exercise exercise : exercise_CB._exerciseList.getExercise()){
                System.out.format("" +
                                "Exercise: %s%n" +
                                "ID: %s%n" +
                                "Category: %s%n" +
                                "Muscle Groups: %s,%s%n" +
                                "Equipment: %s%n" +
                                "Description: %s%n%n",
                        exercise.getName(),
                        exercise.getId(),
                        exercise.getCategory(),
                        exercise.getMuscleList(),
                        exercise.getSecondaryMList(),
                        exercise.getEquipmentList(),
                        exercise.getDescription());
            }
            return exercise_CB._exerciseList;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return exercise_CB._exerciseList;

    } // End of displayExercises()


    /** generateList
     * Purpose: Provides a list of json data to save as an object.
     * @param response - json string provided from Gson
     * @return Exercise List by Category
     */
    public static ExerciseController generateList(String response) {
        Gson g = new Gson();
        // Deserialize response
        ExerciseList exercise = g.fromJson(response, ExerciseList.class);
        return new ExerciseController(exercise);
    }


}

