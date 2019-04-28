package com.abt.library.listener;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.abt.library.base.RViewAdapter;

public interface RViewCreate<T> {

    SwipeRefreshLayout createSwipeRefresh();

    int[] colorRes();

    RecyclerView createRecycleView();

    RViewAdapter<T> createRecycleAdapter();

    RecyclerView.LayoutManager createLayoutManager();

    RecyclerView.ItemDecoration createItemDecoration();

    int startPageNumber();

    boolean isSupportPaging();

}
