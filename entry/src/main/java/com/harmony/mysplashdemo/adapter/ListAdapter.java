package com.harmony.mysplashdemo.adapter;

import com.harmony.mysplashdemo.beans.NewsInfo;
import ohos.agp.components.BaseItemProvider;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.app.Context;

import java.util.List;

public abstract class ListAdapter<T> extends BaseItemProvider {

    private List<T> data;
    private Context ct;
    private int itemId;

    public ListAdapter(Context ct, int itemId, List<T> data) {
        this.data = data;
        this.ct = ct;
        this.itemId = itemId;
    }

    public abstract void convert(ViewHolder viewHolder, T item, int position);

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public T getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Component getComponent(int i, Component component, ComponentContainer componentContainer) {
        ViewHolder viewHolder = null;
        if (component == null) {
            Component itemView = LayoutScatter.getInstance(ct).parse(itemId, componentContainer, false);
            viewHolder = new ViewHolder(ct, itemView, componentContainer, i);
            viewHolder.layoutId = itemId;
        } else {
            Object object = component.getTag();
            if (object instanceof ViewHolder) {
                viewHolder = (ViewHolder) object;
                viewHolder.position = i;
            }
        }
        convert(viewHolder, getItem(i), i);
        return viewHolder == null ? null : viewHolder.getComponentView();
    }
}
