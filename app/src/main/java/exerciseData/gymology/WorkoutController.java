package exerciseData.gymology;//package hyatt;
//
//import java.io.IOException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.Scanner;
//
///**
// * Purpose: Saves or creates workouts and stores them in a JSON by type: Cardio, Strength,HIIT.
// *
// */
//public class WorkoutController {
//    private static String query;
//
//    public WorkoutController() {

//    /*
//    Main used for testing purposes
//     */
//    public static void main(String[] args) {
//        displayWorkouts();
//    }
//
//
//    /**displayWorkouts
//     * Purpose: Displays current stored workouts
//     */
//    public static void displayWorkouts() {
        /*
        DONE: Stored exercises by id, as an exercise object all inclusive
        TODO: Call ExerciseController to retrieve exercise infoby ID
        TODO: Workout json would include: Type, ExercisesList(id, name), (Saved Preferences?)
         */
//        try {
//            // Call stored JSON file and retrieve each workout and its name for display
//
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//    }
//
////    /**
////     * Method that makes a connection to an API
////     * @param url
////     * @see URLData enum
////     * @return String response
////     * @throws IOException -if problems occur while connecting
////     */
////    public static String connectAPI(String url) throws IOException {
////        // Open Connection
////        URLConnection connection =
////                new URL(url).openConnection();
////
////        // Display Json data to console with indents
////        connection.setRequestProperty("Accept",
////                URLData.ACCEPT.getData() + URLData.JSONINDENT.getData());
////
////        // Process return stream
////        Scanner scanner = new Scanner(connection.getInputStream());
////        return scanner.useDelimiter("\\A").next();
////    }
//
//
//
//}
