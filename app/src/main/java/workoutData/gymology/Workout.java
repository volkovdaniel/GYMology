package workoutData.gymology;

import exerciseData.gymology.Exercise;

import java.util.HashMap;
import java.util.Map;

public class Workout {
    private Map<String, Exercise> _workoutExercises;
    private Exercise _exercise;
    private String _name;

    public Workout() {
        _workoutExercises = new HashMap<>();
        _exercise = new Exercise();
    }

    public Map<String, Exercise> get_workoutMap() {
        return _workoutExercises;
    }
    public String get_name() {
        return _name;
    }
    public void set_name(String name) {
        this._name = name;
    }
    public void set_exercise(Exercise addExercise) {
        this._exercise = addExercise;
    }
    public void addTo_workoutExercises() {
        _workoutExercises.put(_exercise.getName(), _exercise);
    }

}
