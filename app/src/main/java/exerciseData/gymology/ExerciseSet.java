package exerciseData.gymology;

import java.time.Duration;

/**
 * ExerciseSet
 *
 * Saves the reps done for the specific exercise
 */
public class ExerciseSet {
    // Return a object of int, String
    private int _reps;
    private int _set;
    private Duration _duration;

    public ExerciseSet() {
        _reps = 0;
        _set = 0;
        _duration = Duration.ZERO;
    }
    public void set_duration(Duration _duration) {
        this._duration = _duration;
    }

    public Duration get_duration() {
        return _duration;
    }
}
