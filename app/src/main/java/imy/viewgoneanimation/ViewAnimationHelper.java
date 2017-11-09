package imy.viewgoneanimation;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;

import java.lang.ref.SoftReference;

/**
 * Created by 4399-蒋明伟 on 2017/11/9.
 */

public class ViewAnimationHelper {
    private volatile static ViewAnimationHelper instance;
    private SparseArray<SoftReference<View>> viewCacheMap = new SparseArray<SoftReference<View>>();
    private SparseIntArray viewHeightCache = new SparseIntArray();

    public static ViewAnimationHelper getInstance() {
        if (instance == null) {
            synchronized (ViewAnimationHelper.class) {
                if (instance == null) {
                    instance = new ViewAnimationHelper();

                }
            }
        }
        return instance;
    }

    public ViewController attach(View view) {
        SoftReference<View> vsr = viewCacheMap.get(view.hashCode());
        if (vsr == null) { //软引用不存在
            //添加软引用
            vsr = new SoftReference<View>(view);
            viewCacheMap.append(view.hashCode(), vsr);
            //需判断View的状态 如果是Visibile才进行加入原始高度
            if (view.getVisibility() == View.VISIBLE) {
                viewHeightCache.append(view.hashCode(), view.getHeight());
            }
        }
        ViewController viewController = new ViewController(view.hashCode());
        return viewController;
    }

    public View getTargetView(int hashCode) {
        SoftReference<View> vsr = viewCacheMap.get(hashCode);
        if (vsr == null) {
            return null;
        } else {
            return vsr.get();
        }
    }

    public SparseIntArray getTargetViewHeightCache() {
       return  viewHeightCache;
    }
}
