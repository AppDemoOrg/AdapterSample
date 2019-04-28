package com.abt.library.manager;

import android.support.v4.util.SparseArrayCompat;

import com.abt.library.holder.RViewHolder;
import com.abt.library.listener.RViewItem;

public class RViewItemManager<T> {

    private SparseArrayCompat<RViewItem<T>> style = new SparseArrayCompat<>();

    public void addStyle(RViewItem<T> item) {
        if (item != null) {
            style.put(style.size(), item);
        }
    }

    public int getItemViewStyleCount() {
        return style.size();
    }

    public RViewItem getRViewItem(int viewType) {
        return style.get(viewType);
    }

    public int getItemViewType(T entity, int position) {
        for (int i = style.size()-1; i > 0 ; i--) {
            RViewItem<T> item = style.valueAt(i);
            if (item.isItemView(entity, position)) {
                return style.keyAt(i);
            }
        }
        throw new IllegalArgumentException("该位置没有匹配的条目样式");
    }

    public void convert(RViewHolder holder, T entity, int position) {
        for (int i = 0; i < style.size(); i++) {
            RViewItem<T> item = style.valueAt(i);
            if (item.isItemView(entity, position)) {
                item.convert(holder,entity,position);
                return;
            }
        }
        throw new IllegalArgumentException("该位置没有匹配的条目样式");
    }
}
