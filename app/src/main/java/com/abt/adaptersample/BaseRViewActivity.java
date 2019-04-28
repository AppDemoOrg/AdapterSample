package com.abt.adaptersample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.abt.library.RViewHelper;
import com.abt.library.SwipseRefreshHelper;
import com.abt.library.listener.RViewCreate;

import java.util.List;

public abstract class BaseRViewActivity extends Activity implements RViewCreate, SwipseRefreshHelper.SwipeRefreshListener {

    private RViewHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        helper = new RViewHelper.Builder(this, this).build();
    }

    @Override
    public android.support.v4.widget.SwipeRefreshLayout createSwipeRefresh() {
        return findViewById(R.id.swipeRefreshLayout);
    }

    @Override
    public int[] colorRes() {
        return new int[0];
    }

    @Override
    public android.support.v7.widget.RecyclerView createRecycleView() {
        return findViewById(R.id.recycleView);
    }

    @Override
    public android.support.v7.widget.RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public android.support.v7.widget.RecyclerView.ItemDecoration createItemDecoration() {
        return new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
    }

    @Override
    public int startPageNumber() {
        return 1;
    }

    @Override
    public boolean isSupportPaging() {
        return false;
    }

    protected void notifyAdapterDataSetChange(List data) {
        helper.notifyAdapterDataSetChange(data);
    }
}
