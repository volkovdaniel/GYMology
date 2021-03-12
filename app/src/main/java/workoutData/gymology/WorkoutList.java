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
    private List<Exercise> _workout;

    public WorkoutList() {
        this._workout = new ArrayList<>();
    }

    public List<Exercise> getWorkout() {
        return _workout;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "workoutData=" + _workout +
                '}';
    }
}