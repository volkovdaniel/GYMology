package profile.gymology;

public class user_profile {
    BMI bmi = new BMI();
    // variables are unused right now, I assume they will be used once
    // we link them to a field/box/area/whatever on oru display.
    private double userWeight = bmi.getWeight();
    private double userHeight = bmi.getHeight();

}
