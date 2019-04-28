package com.abt.adaptersample.multi;

import android.widget.TextView;

import com.abt.adaptersample.R;
import com.abt.adaptersample.bean.UserInfo;
import com.abt.library.holder.RViewHolder;
import com.abt.library.listener.RViewItem;

public class CItem implements RViewItem<UserInfo> {
    @Override
    public int getItemLayout() {
        return R.layout.item_e;
    }

    @Override
    public boolean openClick() {
        return false;
    }

    @Override
    public boolean isItemView(UserInfo entity, int position) {
        return entity.getType() == 3;
    }

    @Override
    public void convert(RViewHolder holder, UserInfo entity, int position) {
        TextView textView = holder.getView(R.id.tv);
        textView.setText(entity.getAccout());
    }
}
