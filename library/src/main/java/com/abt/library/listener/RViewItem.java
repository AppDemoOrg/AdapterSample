package com.abt.library.listener;

import com.abt.library.holder.RViewHolder;

public interface RViewItem<T> {

    int getItemLayout();

    boolean openClick();

    boolean isItemView(T entity, int position);

    // item控件和数据绑定
    void convert(RViewHolder holder, T entity, int position);
}
