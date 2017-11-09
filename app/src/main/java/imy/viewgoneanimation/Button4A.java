package imy.viewgoneanimation;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

/**
 * Created by 4399-蒋明伟 on 2017/11/9.
 */

public class Button4A extends AppCompatButton implements ViewGoneAnimation.onViewChangeListener {
    public Button4A(Context context) {
        super(context);
    }

    public Button4A(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Button4A(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setVisibility(int visibility) {
        Log.i("Button4A", "getHeight():" + getHeight());
        ViewGoneAnimation viewGoneAnimation = new ViewGoneAnimation(getHeight(), 0, this);
        viewGoneAnimation.setDuration(300);
        startAnimation(viewGoneAnimation);
    }

    @Override
    public void onChange(int i) {
        Log.i("Button4A", "onChange:" + i);
        if (i == 0) {
            super.setVisibility(GONE);
        }
        ViewGroup.LayoutParams layoutParams =
                getLayoutParams();
        layoutParams.height = i;
        setLayoutParams(layoutParams);

    }
}
