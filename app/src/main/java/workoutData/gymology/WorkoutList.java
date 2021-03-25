package workoutData.gymology;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: Provides the base layout for the makeup of an exercise
 */
public class WorkoutList {
    private final List<Workout> _DB;

    public WorkoutList() {
        this._DB = new ArrayList<>();
    }

    public List<Workout> get_workoutList() {
        return _DB;
    }

    public void addTo_DB(Workout workout) {
        _DB.add(workout);
    }
}