package com.abt.adaptersample;

import android.os.Bundle;

import com.abt.library.base.RViewAdapter;

public class MultiActivity extends BaseRViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public RViewAdapter createRecycleAdapter() {
        return null;
    }
}
