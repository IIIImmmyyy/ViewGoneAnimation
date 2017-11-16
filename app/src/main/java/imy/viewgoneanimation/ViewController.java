package imy.viewgoneanimation;

import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import imy.viewgoneanimation.ViewAnimationHelper;
import imy.viewgoneanimation.ViewGoneAnimation;

/**
 * Created by 4399-蒋明伟 on 2017/11/9.
 */

public class ViewController {
    private int key;
    private int duration = 300;  // animation time
    private boolean isCircle = false;

    public ViewController setDuration(int duration){
        this.duration =duration;
        return this;
    }
    public void setCircle(boolean b) {
        isCircle = b;
    }

    public ViewController(int key) {
        this.key = key;
    }

    public void setVisibility(int i) {
        //获取View对象
        View targetView = ViewAnimationHelper.getInstance().getTargetView(key);
        if (i == View.GONE) {
            runGoneAnimation(false);
        } else if (i == View.VISIBLE) {
            runVisibleAnimation();
        } else if (i == View.INVISIBLE) {
            initViewHeight();
        }
    }

    private void initViewHeight() {
        View targetView = ViewAnimationHelper.getInstance().getTargetView(key);
        final SparseIntArray targetViewHeightCache = ViewAnimationHelper.getInstance().getTargetViewHeightCache();
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

    public void setVisibility(int i, boolean isInit) {
        //获取View对象
        View targetView = ViewAnimationHelper.getInstance().getTargetView(key);
        if (i == View.GONE) {
            runGoneAnimation(isInit);
        } else if (i == View.VISIBLE) {
            runVisibleAnimation();
        } else if (i == View.INVISIBLE) {
            initViewHeight();
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
                if (i > 0) {
                    View view = ViewAnimationHelper.getInstance().getTargetView(key);
                    if (view.getVisibility() != View.VISIBLE) {
                        view.setVisibility(View.VISIBLE);
                    }
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    layoutParams.height = i;
                    if (isCircle) {
                        layoutParams.width = i;
                    }
                    view.setLayoutParams(layoutParams);
                }

            }
        });
        animation.setDuration(duration);
        ViewAnimationHelper.getInstance().getTargetView(key).startAnimation(animation);
    }

    private void runGoneAnimation(final boolean isInt) {
        //每次取最新的高度
        View targetView = ViewAnimationHelper.getInstance().getTargetView(key);
        if (targetView.getVisibility() == View.VISIBLE || targetView.getVisibility() == View.INVISIBLE) {
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
                if (isInt) {
                    targetViewHeightCache.append(key, targetView.getHeight());
                    View view = ViewAnimationHelper.getInstance().getTargetView(key);
                    view.setVisibility(View.GONE);
                    return;
                } else {
                    targetViewHeightCache.append(key, targetView.getHeight());
                }
            }
        }
        ViewGoneAnimation animation = new ViewGoneAnimation(targetView.getHeight(), 0, new ViewGoneAnimation.onViewChangeListener() {
            @Override
            public void onChange(int i) {
                View view = ViewAnimationHelper.getInstance().getTargetView(key);
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = i;
                if (isCircle) {
                    layoutParams.width = i;
                }
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
