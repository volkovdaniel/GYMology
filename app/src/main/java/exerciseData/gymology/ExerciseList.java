package exerciseData.gymology;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: Provides the base layout for the makeup of an exercise
 */
public class ExerciseList {
    @SerializedName("results")
    private final List<Exercise> exercise;

    public ExerciseList() {
        this.exercise = new ArrayList<>();
    }

    public List<Exercise> getExercise() {
        return exercise;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "exerciseData=" + exercise +
                '}';
    }
}