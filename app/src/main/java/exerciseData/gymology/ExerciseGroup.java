package exerciseData.gymology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExerciseGroup {
    private final List<Exercise> _group;

    /**
     * ExerciseGroup default constructor
     * Initializes HashMap
     */
    public ExerciseGroup() {
        _group = new ArrayList<>();
    }

    /**
     * get_group
     * Getter for the group or map of exercises
     *
     * @return exercise group
     */
    public List<Exercise> get_group() {
        return _group;
    }

    /**
     * addTo_group
     * Builds the map consisting of the Name of the Exercise and the actual Exercise
     */
    public void addTo_group(Exercise exercise) {
        _group.add(exercise);
    }

    /**
     * removeFrom_group
     * Selects the named exercise from the group and removes it
     */
    public void removeFrom_group(Exercise exercise) {
        _group.remove(exercise);
    }

    @Override
    public String toString() {
        return "ExerciseGroup{" +
                "_group=" + _group +
                '}';
    }
}
