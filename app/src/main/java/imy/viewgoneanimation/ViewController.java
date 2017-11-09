package imy.viewgoneanimation;

import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

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
            runGoneAnimation();
        } else if (i == View.VISIBLE) {
            runVisibleAnimation();
        }

    }

    private void runVisibleAnimation() {
        SparseIntArray targetViewHeightCache = ViewAnimationHelper.getInstance().getTargetViewHeightCache();
        int i = targetViewHeightCache.get(key);
        Log.i("ViewController", "runVisibleAnimation" + i);
        ViewGoneAnimation animation = new ViewGoneAnimation(0, i, new ViewGoneAnimation.onViewChangeListener() {
            @Override
            public void onChange(int i) {
                Log.i("ViewController", "i" + i);
                if (i>0){
                    View view = ViewAnimationHelper.getInstance().getTargetView(key);
                    if (view.getVisibility() != View.VISIBLE) {
                        view.setVisibility(View.VISIBLE);
                    }
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    layoutParams.height = i;
                    view.setLayoutParams(layoutParams);
                }

            }
        });
        animation.setDuration(duration);
        ViewAnimationHelper.getInstance().getTargetView(key).startAnimation(animation);
    }

    private void runGoneAnimation() {
        //每次取最新的高度
        View targetView = ViewAnimationHelper.getInstance().getTargetView(key);
        if (targetView.getVisibility() == View.VISIBLE) {
            final SparseIntArray targetViewHeightCache = ViewAnimationHelper.getInstance().getTargetViewHeightCache();
            Log.i("ViewController", "view height :" + targetView.getHeight());
            if (targetView.getHeight() == 0) { //未初始化完成
                targetView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        View view = ViewAnimationHelper.getInstance().getTargetView(key);
                        int height = view.getHeight();
                        ViewAnimationHelper.getInstance().getTargetViewHeightCache().append(key, height);
                        view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });
            } else {
                targetViewHeightCache.append(key, targetView.getHeight());
            }
        }
        ViewGoneAnimation animation = new ViewGoneAnimation(targetView.getHeight(), 0, new ViewGoneAnimation.onViewChangeListener() {
            @Override
            public void onChange(int i) {
                View view = ViewAnimationHelper.getInstance().getTargetView(key);
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = i;
                view.setLayoutParams(layoutParams);
                if (i == 0) {
                    view.setVisibility(View.GONE);
                }
            }
        });
        animation.setDuration(duration);
        targetView.startAnimation(animation);
    }

}
