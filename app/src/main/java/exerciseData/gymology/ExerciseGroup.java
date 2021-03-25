package exerciseData.gymology;

import java.util.HashMap;
import java.util.Map;

public class ExerciseGroup {
    private final Map<String, Exercise> _group;

    /**
     * ExerciseGroup default constructor
     * Initializes HashMap
     */
    public ExerciseGroup() {
        _group = new HashMap<>();
    }

    /**
     * get_group
     * Getter for the group or map of exercises
     *
     * @return exercise group
     */
    public Map<String, Exercise> get_group() {
        return _group;
    }

    /**
     * addTo_group
     * Builds the map consisting of the Name of the Exercise and the actual Exercise
     */
    public void addTo_group(Exercise exercise) {
        _group.put(exercise.getName(), exercise);
    }

    /**
     * removeFrom_group
     * Selects the named exercise from the group and removes it
     */
    public void removeFrom_group(Exercise removedExercise) {
        _group.remove(removedExercise.getName());
    }

    @Override
    public String toString() {
        return "ExerciseGroup{" +
                "_group=" + _group +
                '}';
    }
}
