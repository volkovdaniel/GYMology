package utilities.gymology;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import androidx.appcompat.widget.AppCompatRadioButton;

public class UncheckableRadioBtn extends AppCompatRadioButton {

    /**
     * Uncheckable Radio Button default constructor
     *
     * @param context
     */
    public UncheckableRadioBtn(Context context) {
        super(context);
    }

    /**
     * Uncheckable Radio Button parameterized constructor
     * Retrieves the context, and attributes of the radio buttons
     *
     * @param context
     * @param attributes
     */
    public UncheckableRadioBtn(Context context, AttributeSet attributes) {
        super(context,attributes);
    }

    /**
     * Uncheckable Radio Button parameterized constructor
     * Retrieves the radio button's context, attributes, and id for defining specific attribute
     * style.
     *
     * @param context
     * @param attributes
     * @param defineStyleAttribute
     */
    public UncheckableRadioBtn(Context context, AttributeSet attributes, int defineStyleAttribute) {
        super(context,attributes, defineStyleAttribute);
    }

    /**
     * Toggle Override
     * Method that allows for undoing a click on a radio button.
     *
     */
    @Override
    public void toggle() {
        if (isChecked()) {

            // If the container has something, and is a RadioGroup check for clearing activation
            if (getParent() != null && getParent() instanceof RadioGroup) {
                ((RadioGroup) getParent()).clearCheck();
            }
            else {
                super.toggle();
            }

        }
    }

    //
    @Override
    public CharSequence getAccessibilityClassName() {
        return UncheckableRadioBtn.class.getName();
    }

}
