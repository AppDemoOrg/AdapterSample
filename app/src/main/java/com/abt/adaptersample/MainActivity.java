package com.abt.adaptersample;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.abt.adaptersample.bean.UserInfo;
import com.abt.library.base.RViewAdapter;
import com.abt.library.holder.RViewHolder;
import com.abt.library.listener.ItemListener;
import com.abt.library.listener.RViewItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseRViewActivity {

    private List<UserInfo> datas = new ArrayList<>();
    private RViewAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initData();
        listener();
    }

    private void listener() {
        adapter.setItemListener(new ItemListener() {
            @Override
            public void onItemClick(View view, Object entity, int position) {
                Toast.makeText(MainActivity.this, "item:"+position+"onItemClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, Object entity, int position) {
                Toast.makeText(MainActivity.this, "item:"+position+"onItemLongClick", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public void onRefresh() {
        initData();
    }

    private void initData() {
        if (datas.isEmpty()) {
            for (int i=0; i<100; i++) {
                datas.add(new UserInfo("Name"+i, "123"+i));
            }
        }
        notifyAdapterDataSetChange(datas);
    }

    @Override
    public RViewAdapter createRecycleAdapter() {
        adapter = new RViewAdapter<>(datas, new RViewItem() {
            @Override
            public int getItemLayout() {
                return R.layout.item_list;
            }

            @Override
            public boolean openClick() {
                return true;
            }

            @Override
            public boolean isItemView(Object entity, int position) {
                return true;
            }

            @Override
            public void convert(RViewHolder holder, Object entity, int position) {
                TextView textView = holder.getView(R.id.item_tv);
                textView.setText(entity.toString());
            }
        });
        return adapter;
    }
}
