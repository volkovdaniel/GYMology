package exerciseData.gymology;//package hyatt;
//
//import com.google.gson.Gson;
//import com.google.gson.annotations.SerializedName;
//
//import java.util.List;
//
//public class Category_DB {
//    @SerializedName("results")
//    private List<Exercise> _exercise;
//
//    public Category_DB(Exercise exercise) {
//        this._exercise = exercise;
//    }
//
//    /** generateList
//     * Purpose: Provides a list of json data to save as an object.
//     * @param response - json string provided from Gson
//     * @return Exercise List by Category
//     */
//    public static Category_DB generateList(String response) {
//        Gson g = new Gson();
//        // Deserialize response
//        Exercise exercise = g.fromJson(response, Exercise.class);
//        return new Category_DB(exercise);
//    }
//
//    @Override
//    public String toString() {
//        return "Category_DB{" +
//                "_exercise=" + _exercise.toString() +
//                '}';
//    }
//}
