package exerciseData.gymology;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ExerciseSet
 *
 * Saves the reps done for the specific exercise
 */
public class ExerciseSet {
    private int _reps;
    private int _set;
    private Date _duration;
    private String _name;

    public ExerciseSet() {
        _name = "";
        _reps = 0;
        _set = 0;
        _duration = new Date();
    }

    /**
     * Set_duration
     *
     * The time it took to complete the exercise
     *
     * @param duration The specified time
     * @throws ParseException
     */
    public void set_duration(String duration) throws ParseException {
        DateFormat formatTime = new SimpleDateFormat("mm:ss");
        this._duration = formatTime.parse(duration);
    }

    public Date get_duration() {
        return _duration;
    }

    public String get_name() { return _name; }

    public void set_name(String _name) { this._name = _name; }

    public int get_reps() {
        return _reps;
    }

    public void set_reps(int reps) {
        this._reps = reps;
    }

    public int get_set() {
        return _set;
    }

    public void set_set(int set) {
        this._set = set;
    }

    @Override
    public String toString() {
        return "ExerciseSet{" +
                "_reps=" + _reps +
                ", _set=" + _set +
                ", _duration=" + _duration +
                '}';
    }
}
