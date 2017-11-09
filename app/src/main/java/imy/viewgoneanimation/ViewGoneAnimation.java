package imy.viewgoneanimation;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by user on 2016/2/26.
 */
public class ViewGoneAnimation extends Animation {
    private int start;
    private int end;
    private onViewChangeListener listener;

    public ViewGoneAnimation(int start, int end, onViewChangeListener listener) {
        this.start = start;
        this.end = end;
        this.listener = listener;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Integer margin = evaluate(interpolatedTime, start, end);
        if (listener != null) {
            listener.onChange(margin);
        }
        super.applyTransformation(interpolatedTime, t);
    }

    public interface onViewChangeListener {
        void onChange(int i);
    }

    /**
     * 类型估值器
     *
     * @param fraction
     * @param startValue
     * @param endValue
     * @return
     */
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int) (startInt + fraction * (endValue - startInt));
    }
}

