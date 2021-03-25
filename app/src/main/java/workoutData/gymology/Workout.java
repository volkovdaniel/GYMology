package workoutData.gymology;

import exerciseData.gymology.Exercise;
import exerciseData.gymology.ExerciseGroup;
import utilities.gymology.Types;

/**
 * Workout
 * User built workout from the Create Workout Screen.
 * Builds the group of Exercises selected.
 * Saves the workout as a certain type.
 */
public class Workout {
    private final ExerciseGroup _workoutExercises;
    private String _name;
    private Boolean _completed;
    private Types _type;

    /**
     * Workout default constructor
     * Initializes the member fields needed for setting up the workout object.
     */
    public Workout() {
        this._workoutExercises = new ExerciseGroup();
        this._name = "";
        this._completed = false;
        this._type = Types.CARDIO;
    }

//    public Workout getWorkout() {
//        return this;
//    }

    /**
     * get_type
     * Getter method for retrieving the type
     *
     * @return The workout type
     */
    public Types get_type() {
        return _type;
    }

    /**
     * get_name
     * Getter method for retrieving the name
     * @return The workout name
     */
    public String get_name() {
        return _name;
    }

    /**
     * get_workoutExercises
     * Getter method for retrieving the exercises
     * @return The exercises group
     */
    public ExerciseGroup get_workoutExercises() { return _workoutExercises; }

    /**
     * set_type
     * Setter method for setting the workout's type
     * @param type The user's selected type
     */
    public void set_type(Types type) { this._type = type; }

    /**
     * set_name
     * Setter method for setting the workout's name
     * @param name The user's created name for the workout
     */
    public void set_name(String name) {
        this._name = name;
    }

    /**
     * set_completed
     * Setter method for toggling whether the workout has been completed
     * @param completed The value toggled based on Logging an exercise
     */
    public void set_completed(Boolean completed) {this._completed = completed;}

    /**
     * generateExerciseGroup
     * Builds and returns the group of exercises defined by the user for Workout Creation
     * @param exercise The user selected exercise
     */
    public void generateGroup(Exercise exercise) {
        _workoutExercises.addTo_group(exercise);
    }

    /**
     * removeExerciseFromGroup
     * Method to clear specific exercises from the Exercise group
     *
     * @param exercise The user exercise for removal
     */
    public void removeFromGroup(Exercise exercise) {
        _workoutExercises.removeFrom_group(exercise);
    }

    @Override
    public String toString() {
        return "Workout{" +
                "_workoutExercises=" + _workoutExercises +
                ", _name='" + _name + '\'' +
                ", _completed=" + _completed +
                ", _type=" + _type +
                '}';
    }
}
