package workoutData.gymology;

import exerciseData.gymology.Exercise;
import exerciseData.gymology.ExerciseGroup;

/**
 * Workout
 * User built workout from the Create Workout Screen.
 * Builds the group of Exercises selected.
 * Saves the workout as a certain type.
 */
public class Workout {
    private final String _completed;
    private final String _type;
    private final ExerciseGroup _workoutExercises;
    private String _name;

    public Workout() {

        this._workoutExercises = new ExerciseGroup();
        this._name = _name;
        this._completed = "";
        this._type = "";
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    /**
     * generateExerciseGroup
     * Builds and returns the group of exercises defined by the user for Workout Creation
     */
    public void generateGroup(Exercise exercise) {
        _workoutExercises.addTo_group(exercise);
    }

    /**
     * removeExerciseFromGroup
     * Method to clear specific exercises from the Exercise group
     */
    public void removeFromGroup(Exercise exercise) {
        _workoutExercises.removeFrom_group(exercise);
    }

}
