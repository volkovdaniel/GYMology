package exerciseData.gymology;

import utilities.gymology.SimpleList;

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
     * RemoveFrom_Group
     *
     * Removes an exercise from the current exercise group
     *
     * @param exercise The Exercise that's removed
     */
    public void removeFrom_group(Exercise exercise) { _group.remove(exercise); }

    /**
     * RemoveFrom_Group
     *
     * Removes an exercise from the current group by String Name
     *
     * @param exerciseName The name of the exercise to be removed
     */
    public void removeFrom_group(String exerciseName) {
        // Collections remove if, quick way to grab an item from a list's matching string
        _group.removeIf(e -> e.getName().equals(exerciseName));
    }

    @Override
    public String toString() {
        return "ExerciseGroup{" +
                "_group=" + _group +
                '}';
    }
}
