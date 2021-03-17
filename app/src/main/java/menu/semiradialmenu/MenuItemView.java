package menu.semiradialmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import team13.gymology.R;

public class MenuItemView extends LinearLayout {
    Utils utils;
    private String title;
    private int image;
    @ColorRes
    private int color;
    private float angleRotation;
    private boolean allowTitle = true;
    private View v;

    /** MenuItemView Class
     * The LinearLayout of the Menu display on click
     * @param context activity context
     * @param title button title
     * @param image button image
     * @param color button color
     */
    public MenuItemView(Context context, String title, int image, int color) {
        super(context);
        this.title = title;
        this.image = image;
        this.color = color;
        init();
    }

    public MenuItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        init();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    private void init() {

        // Grab the context of the radialMenuItemLayout for placing buttons
        if (v == null) {
            v = inflate(getContext(), R.layout.radial_menu_item_layout, this);
        }

        // Grab each view item
        TextView tv = v.findViewById(R.id.text);
        ImageView iv = v.findViewById(R.id.image);

        tv.setEnabled(allowTitle);
        tv.setText(title);

        iv.setImageResource(image);
    }

    public void setAllowTitle(boolean allowTitle) {
        this.allowTitle = allowTitle;
        init();
    }

}
