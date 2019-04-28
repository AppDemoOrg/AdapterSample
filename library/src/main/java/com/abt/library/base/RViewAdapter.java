package com.abt.library.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.abt.library.holder.RViewHolder;
import com.abt.library.listener.ItemListener;
import com.abt.library.listener.RViewItem;
import com.abt.library.manager.RViewItemManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 多item样式适配器
 */
public class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {

    private RViewItemManager<T> itemStyle;
    private ItemListener<T> itemListener;
    private List<T> datas;

    public void addDatas(List<T> datas) {
        if (datas == null) return;
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void updateDatas(List<T> datas) {
        if (datas == null) return;
        this.datas = datas;
        notifyDataSetChanged();
    }

    public RViewAdapter(List<T> datas) {
        if (datas == null) this.datas = new ArrayList<>();
        this.datas = datas;
        itemStyle = new RViewItemManager<>();
    }

    public RViewAdapter(List<T> datas, RViewItem<T> item) {
        if (datas == null) this.datas = new ArrayList<>();
        this.datas = datas;
        itemStyle = new RViewItemManager<>();
        addItemStyle(item);
    }

    public void addItemStyle(RViewItem<T> item) {
        itemStyle.addStyle(item);
    }

    public void setItemListener(ItemListener<T> itemListener) {
        this.itemListener = itemListener;
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RViewItem item = itemStyle.getRViewItem(viewType);
        int layoutId = item.getItemLayout();
        RViewHolder viewHolder = RViewHolder.createViewHolder(parent.getContext(), parent, layoutId);
        if (item.openClick()) setListener(viewHolder);
        return viewHolder;
    }

    private void setListener(RViewHolder holder) {
        holder.getmConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null) {
                    int position = holder.getAdapterPosition();
                    itemListener.onItemClick(v, datas.get(position), position);
                }

            }
        });

        holder.getmConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemListener != null) {
                    int position = holder.getAdapterPosition();
                    return itemListener.onItemLongClick(v, datas.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int position) {
        convert(holder, datas.get(position));
    }

    private void convert(RViewHolder holder, T entity) {
        itemStyle.convert(holder, entity, holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (hasMultiType()) itemStyle.getItemViewType(datas.get(position), position);
        return super.getItemViewType(position);
    }

    public boolean hasMultiType() {
        return itemStyle.getItemViewStyleCount() > 1; // default 0
    }
}
