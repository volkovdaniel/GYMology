package profile.gymology;

import exerciseData.gymology.ExerciseGroup;
import utilities.gymology.Types;

/**
 * UserProfile
 * User defined profile from the Profile Screen.
 * Gets and sets User input.
 */
public class UserProfile {

    private String _age;
    private String _height;
    private String _weight;
    private Boolean _calories;
    private Boolean _meals;
    private Boolean _water;

    /**
     * Workout default constructor
     * Initializes the member fields needed for setting up the workout object.
     */
    public UserProfile() {
        this._age = "";
        this._height = "";
        this._weight = "";
        this._calories = false;
        this._meals = false;
        this._water = false;
    }


    /**
     * get_age
     * Getter method for retrieving the user age
     * @return The user age
     */
    public String get_age() {
        return _age;
    }

    /**
     * get_height
     * Getter method for retrieving the user height
     * @return The user height
     */
    public String get_height() {
        return _height;
    }

    /**
     * get_weight
     * Getter method for retrieving the user weight
     * @return The user weight
     */
    public String get_weight() {
        return _weight;
    }

    /**
     * get_calories
     * Getter method for retrieving whether calories box is checked
     *
     * @return True if checked, false if not
     */
    public Boolean get_calories() {
        return _calories;
    }

    /**
     * get_water
     * Getter method for retrieving whether water box is checked
     *
     * @return True if checked, false if not
     */
    public Boolean get_water() {
        return _water;
    }

    /**
     * get_meals
     * Getter method for retrieving whether meals box is checked
     *
     * @return True if checked, false if not
     */
    public Boolean get_meals() {
        return _meals;
    }

    /**
     * set_age
     * Setter method for setting the user's age
     * @param age The user's age in profile
     */
    public void set_age(String age) {
        this._age = age;
    }

    /**
     * set_height
     * Setter method for setting the user's height
     * @param height The user's height in profile
     */
    public void set_height(String height) {
        this._height = height;
    }

    /**
     * set_weight
     * Setter method for setting the user's weight
     * @param weight The user's weight in profile
     */
    public void set_weight(String weight) {
        this._weight = weight;
    }

    /**
     * set_calories
     * Setter method for toggling whether the user wants to estimate calories
     * @param calories The value toggled based on checked profile box
     */
    public void set_calories(Boolean calories) {this._calories = calories;}

    /**
     * set_water
     * Setter method for toggling whether the user wants water reminders
     * @param water The value toggled based on checked profile box
     */
    public void set_water(Boolean water) {this._water = water;}

    /**
     * set_meals
     * Setter method for toggling whether the user wants meal reminders
     * @param meals The value toggled based on checked profile box
     */
    public void set_meals(Boolean meals) {this._meals = meals;}

}
