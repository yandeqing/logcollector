package com.logstacksdk.utils;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.logstacksdk.bean.body.ViewPath;

/**
 * @author yandeqing
 * @version 备注:
 * @date 2018/9/20
 */

public class ViewScratchUtil {
    private static final String TAG = ViewScratchUtil.class.getSimpleName();
    private static final boolean debug = true;

    private static ViewScratchUtil mViewScratchUtil = new ViewScratchUtil();

    private ViewScratchUtil() {
    }

    public static ViewScratchUtil getInstance() {
        return mViewScratchUtil;
    }


    private View rootView;

    private String rootViewTree;

    private String bigDataPrefix;

    private String bigDataIngorePrefix;

    private String bigDataEventPrefix;


    public void attachActivity(Activity activity) {
        rootView = activity.getWindow().getDecorView();
        rootViewTree = activity.getClass().getSimpleName();
        bigDataPrefix = "Tamic_test";
        bigDataIngorePrefix = bigDataPrefix + "";
        bigDataEventPrefix = bigDataIngorePrefix + "Igmore";
    }

    public ViewPath findClickView(MotionEvent ev) {
        ViewPath clickView = new ViewPath(rootView, rootViewTree);
        Log.e(TAG, "bigdata-->findClickView");
        return searchClickView(clickView, ev, 0);
    }


    private ViewPath searchClickView(ViewPath myView, MotionEvent event, int index) {
        ViewPath clickView = null;
        View view = myView.getView();
        if (isInView(view, event)) {
            //遍历根view下的子view以及所有子view上的控件
            // 当第二层不为LinearLayout时，说明系统进行了改造，多了一层,需要多剔除一层
            myView.level++;
            if (myView.level == 2 && !"LinearLayout".equals(view.getClass().getSimpleName())) {
                myView.filterLevelCount++;
            }
            if (myView.level > myView.filterLevelCount) {
                myView.viewTree = myView.viewTree + "." + view.getClass().getSimpleName() + "[" + index + "]";
            }
            Log.i(TAG, "bigdata-->tag = " + view.getTag());
            // 如果Layout有设置特定的tag，则直接返回View，主要用于复合组件的点击事件
            if (view.getTag() != null) {
                // 主动标记不需要统计时，不进行自动统计
                String tag = view.getTag().toString();
                if (tag.startsWith(bigDataIngorePrefix)) {
                    return null;
                } else if (tag.startsWith(bigDataPrefix)) {
                    if (tag.startsWith(bigDataEventPrefix)) {
                        myView.specifyTag = tag.replace(bigDataEventPrefix, "");
                    }
                    return myView;
                }
            }
            if (view instanceof ViewGroup) {
                //遇到一些Layout之类的ViewGroup，继续遍历它下面的子View
                if (view instanceof AbsListView) {
                    Log.i(TAG, "bigdata-->AbsListView ");
                    return null;
                }
                ViewGroup group = (ViewGroup) view;
                int childCount = group.getChildCount();
                if (childCount == 0) {
                    return myView;
                }
                for (int i = childCount - 1; i >= 0; i--) {
                    myView.setView(group.getChildAt(i));
                    clickView = searchClickView(myView, event, i);
                    if (clickView != null) {
                        return clickView;
                    }
                }
            } else {
                clickView = myView;
            }
        }
        return clickView;
    }

    private boolean isInView(View view, MotionEvent event) {
        //能被点击的view必然是可见的
        if (view == null || view.getVisibility() != View.VISIBLE) {
            return false;
        }
        int clickX = (int) event.getRawX();
        int clickY = (int) event.getRawY();
        //如下的view表示Activity中的子View或者控件
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        int width = view.getWidth();
        int height = view.getHeight();
        //返回true，则判断这个view被点击了
        return clickX > x && clickX < (x + width) && clickY > y && clickY < (y + height);
    }
}
