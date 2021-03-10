package exerciseData.gymology;
/**
 * Sets values needed for creating a URL
 */
public enum URLData {

    // WGER API VALUES
    WGI_API("https://wger.de/api/v2/"),
    EXERCISE_DB("https://wger.de/api/v2/exercise/"),
    CATEGORY_DB("https://wger.de/api/v2/exercisecategory/"),
    MUSCLE("https://wger.de/api/v2/muscle/"),
    EQUIPMENT("https://wger.de/api/v2/equipment/"),

    //Default Query
    QUERY_DEFAULT("?language=2"),

    // Accept Header Settings
    ACCEPT("application/json"),
    JSONINDENT("; indent=4"),

    // Options
    LANGUAGE("language=2"),
    CATEGORY_SINGLE("category="),
    EQUIPMENT_SINGLE("equipment="),
    MUSCLE_SINGLE("muscle="),


    ;

    private String data;

    // Construct values of constants
    URLData(String item) {
        data = item;
    }

    // Method that returns the value of the constants
    String getData() {
        return data;
    }
}
