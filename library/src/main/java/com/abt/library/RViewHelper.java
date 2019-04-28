package com.abt.library;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.abt.library.base.RViewAdapter;
import com.abt.library.listener.RViewCreate;

import java.util.List;

public class RViewHelper<T> {
    private SwipseRefreshHelper swipeRefreshHelper;
    private final SwipeRefreshLayout swipeRefresh;
    private final RecyclerView recycleView;
    private final RViewAdapter adapter;
    private final RecyclerView.ItemDecoration itemDecoration;
    private final RecyclerView.LayoutManager layoutManager;
    private final int startPageNumber;
    private final boolean isSupportPaging;
    private final SwipseRefreshHelper.SwipeRefreshListener listener;
    private int currentPageNum;

    private RViewHelper(Builder builder) {
        this.swipeRefresh = builder.create.createSwipeRefresh();
        this.recycleView = builder.create.createRecycleView();
        this.adapter = builder.create.createRecycleAdapter();
        this.layoutManager = builder.create.createLayoutManager();
        this.itemDecoration = builder.create.createItemDecoration();
        this.startPageNumber = builder.create.startPageNumber();
        this.isSupportPaging = builder.create.isSupportPaging();
        this.listener = builder.listener;

        this.currentPageNum = this.startPageNumber;
        int[] colorRes = builder.create.colorRes();
        if (swipeRefresh != null) {
            if (colorRes == null) {
                swipeRefreshHelper = SwipseRefreshHelper.createSwipeRefreshHelper(swipeRefresh);
            } else {
                swipeRefreshHelper = SwipseRefreshHelper.createSwipeRefreshHelper(swipeRefresh, colorRes);
            }
        }
        init();
    }

    private void init() {
        recycleView.setLayoutManager(layoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        if (itemDecoration != null) recycleView.addItemDecoration(itemDecoration);
        if (swipeRefreshHelper != null) {
            swipeRefreshHelper.setSwipeRefreshListener(() -> {
                dismissSwipeRefresh();
                currentPageNum = startPageNumber;
                if (listener != null) listener.onRefresh();
            });
        }
    }

    public void notifyAdapterDataSetChange(List data) {
        if (currentPageNum == startPageNumber) {
            adapter.updateDatas(data);
        } else {
            adapter.addDatas(data);
        }
        recycleView.setAdapter(adapter);
        if (isSupportPaging) {
            //TODO
        }
    }

    public RViewAdapter<T> getAdapter() {
        return adapter;
    }

    private void dismissSwipeRefresh() {
        if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }

    public static class Builder<T> {
        private final SwipseRefreshHelper.SwipeRefreshListener listener;
        private final RViewCreate<T> create;

        public Builder(RViewCreate<T> create, SwipseRefreshHelper.SwipeRefreshListener listener) {
            this.create = create;
            this.listener = listener;
        }

        public RViewHelper build() {
            return new RViewHelper<>(this);
        }
    }

}
