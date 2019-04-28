package com.abt.library;

import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * 下拉刷新的帮助类
 */
public class SwipseRefreshHelper {

    private static SwipeRefreshLayout swipeRefresh;
    public static SwipeRefreshListener swipeRefreshListener;

    static SwipseRefreshHelper createSwipeRefreshHelper(SwipeRefreshLayout swipeRefresh, @ColorRes int... colorResIds) {
        return new SwipseRefreshHelper(swipeRefresh, colorResIds);
    }

    private SwipseRefreshHelper(@Nullable SwipeRefreshLayout swipeRefresh, @ColorRes int... colorResIds) {
        this.swipeRefresh = swipeRefresh;
        init(colorResIds);
    }

    private static void init(int[] colorResIds) {
        if (colorResIds == null || colorResIds.length == 0) {
            swipeRefresh.setColorSchemeResources(android.R.color.holo_orange_dark,
                    android.R.color.holo_green_dark, android.R.color.holo_blue_dark);
        } else {
            swipeRefresh.setColorSchemeResources(colorResIds);
        }
        swipeRefresh.setOnRefreshListener(() -> {
            if (swipeRefreshListener != null) {
                swipeRefreshListener.onRefresh();
            }
        });
    }

    public interface SwipeRefreshListener {
        void onRefresh();
    }

    public void setSwipeRefreshListener(SwipeRefreshListener swipeRefreshListener) {
        this.swipeRefreshListener = swipeRefreshListener;
    }
}
