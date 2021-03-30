package utilities.gymology;

import com.google.gson.annotations.SerializedName;
import exerciseData.gymology.ExerciseSet;

import java.util.ArrayList;
import java.util.List;


/**
 * Class to create a List, along with usage of the ExtraItems class.
 * Purpose: Used to create simple lists of only an id and name.
 */
public class SimpleList {

    // Private
    private List<ExerciseSet> setGroup;

    // Final
    @SerializedName("results")
    private final List<ExtraItems> listItems;



    public SimpleList() {
        this.listItems = new ArrayList<>();
        this.setGroup = new ArrayList<>();

    }

    public List<ExtraItems> getListItems() {
        return listItems;
    }

    /**
     * GetSetGroup
     *
     * Sends a group of sets for a completed exercise
     *
     * @return The completed sets
     */
    public List<ExerciseSet> get_setGroup() { return setGroup;}

    public List<ExerciseSet> getSetGroup() {
        return setGroup;
    }
}