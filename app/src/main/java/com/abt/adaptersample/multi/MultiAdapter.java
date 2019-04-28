package com.abt.adaptersample.multi;

import com.abt.adaptersample.bean.UserInfo;
import com.abt.library.base.RViewAdapter;
import com.abt.library.listener.RViewItem;

import java.util.List;

public class MultiAdapter extends RViewAdapter<UserInfo> {
    public MultiAdapter(List<UserInfo> datas) {
        super(datas);
        addItemStyle(new AItem());
        addItemStyle(new BItem());
        addItemStyle(new CItem());
        addItemStyle(new DItem());
        addItemStyle(new EItem());
    }

    public MultiAdapter(List<UserInfo> datas, RViewItem<UserInfo> item) {
        super(datas, item);
    }
}
