package workoutData.gymology;

import com.google.gson.annotations.SerializedName;
import exerciseData.gymology.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: Provides the base layout for the makeup of an exercise
 */
public class WorkoutList {
    @SerializedName("results")
    private final List<Exercise> _workoutList;

    public WorkoutList() {
        this._workoutList = new ArrayList<>();
    }

    public List<Exercise> get_workoutList() {
        return _workoutList;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "workoutData=" + _workoutList +
                '}';
    }
}