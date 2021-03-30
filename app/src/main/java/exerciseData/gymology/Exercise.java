package exerciseData.gymology;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import utilities.gymology.SimpleList;

import java.util.ArrayList;
import java.util.List;


/**
 * Exercise
 * Purpose: To provide an exercise object that includes all info on said exercise.
 */
public class Exercise {
    private String id;
    private String category;
//    private String description;
    private String name;
    private SimpleList _setGroup;
//    // Values below will need to be retrieved.
//    @SerializedName("muscles")
//    private List<String> muscleList;
//    @SerializedName("muscles_secondary")
//    private List<String> secondaryMList;
//    @SerializedName("equipment")
//    private List<String> equipmentList;
    // private int variation; Not sure what this is referencing

    /*
    For Description, and anything that includes html tags:
    https://abhiandroid.com/ui/html#:~:text=text.,it%20supports%20all%20major%20tags.

    Android can take strings, and display the html content.
     */
    public Exercise() {
//        this.muscleList = new ArrayList<>();
//        this.secondaryMList = new ArrayList<>();
//        this.equipmentList = new ArrayList<>();
    }

    /**
     * Getter and Setter Methods
     */
    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

//    public String getDescription() {
//        return description;
//    }


    public SimpleList get_setGroup() {
        return _setGroup;
    }

    public String getName() {
        return name;
    }

    public void set_setGroup(SimpleList setGroup) {
        this._setGroup = setGroup;
    }

    //    public List<String> getMuscleList() {
//        return muscleList;
//    }
//
//    public void setMuscleList(List<String> muscle) {
//        this.muscleList = muscle;
//    }
//
//    public List<String> getSecondaryMList() {
//        return secondaryMList;
//    }

//    public void setSecondaryMList(List<String> secondary) {
//        this.secondaryMList = secondary;
//    }

//    public List<String> getEquipmentList() {
//        return equipmentList;
//    }

//    public void setEquipmentList(List<String> equipment) {
//        this.equipmentList = equipment;
//    }

    public String getExercise() {
        Gson g = new Gson();
        return g.toJson(this);
    }

    @Override
    public String toString() {
        return "ExerciseData{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                '}';
    }
}