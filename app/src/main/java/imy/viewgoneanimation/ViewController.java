package imy.viewgoneanimation;

import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 4399-蒋明伟 on 2017/11/9.
 */

public class ViewController {
    private int key;
    private int duration = 300;  // animation time

    public ViewController(int key) {
        this.key = key;
    }

    public void setVisibility(int i) {
        //获取View对象
        View targetView = ViewAnimationHelper.getInstance().getTargetView(key);
        if (i == View.GONE) {
            runGoneAnimation(targetView);
        } else if (i == View.VISIBLE) {
            runVisibleAnimation(targetView);
        }

    }

    private void runVisibleAnimation(final View targetView) {
        SparseIntArray targetViewHeightCache = ViewAnimationHelper.getInstance().getTargetViewHeightCache();
        int i = targetViewHeightCache.get(key);
       ViewGoneAnimation animation =  new ViewGoneAnimation(0, i, new ViewGoneAnimation.onViewChangeListener() {
            @Override
            public void onChange(int i) {
                if (targetView.getVisibility()!=View.VISIBLE){
                    targetView.setVisibility(View.VISIBLE);
                }
                ViewGroup.LayoutParams layoutParams = targetView.getLayoutParams();
                layoutParams.height = i;
                targetView.setLayoutParams(layoutParams);
            }
        });
        animation.setDuration(duration);
        targetView.startAnimation(animation);
    }

    private void runGoneAnimation(final View targetView) {
        //每次取最新的高度
        if (targetView.getVisibility() == View.VISIBLE) {
            SparseIntArray targetViewHeightCache = ViewAnimationHelper.getInstance().getTargetViewHeightCache();
            targetViewHeightCache.append(key, targetView.getHeight());
        }
        ViewGoneAnimation animation = new ViewGoneAnimation(targetView.getHeight(), 0, new ViewGoneAnimation.onViewChangeListener() {
            @Override
            public void onChange(int i) {
                ViewGroup.LayoutParams layoutParams = targetView.getLayoutParams();
                layoutParams.height = i;
                targetView.setLayoutParams(layoutParams);
                if (i == 0) {
                    targetView.setVisibility(View.GONE);
                }
            }
        });
        animation.setDuration(duration);
        targetView.startAnimation(animation);
    }

}
