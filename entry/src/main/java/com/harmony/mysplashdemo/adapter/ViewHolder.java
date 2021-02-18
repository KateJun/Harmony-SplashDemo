package com.harmony.mysplashdemo.adapter;

import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.app.Context;

import java.util.HashMap;

public class ViewHolder {
    int position;

    int layoutId;
    private Component component;
    private Context context;
    private HashMap<Integer, Component> views;

    ViewHolder(Context context, Component itemView, ComponentContainer parent, int position) {
        this.context = context;
        this.component = itemView;
        this.position = position;
        views = new HashMap<>(0);
        component.setTag(this);
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getView(int viewId) {
        Component view = views.get(viewId);
        if (view == null) {
            view = component.findComponentById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    Component getComponentView() {
        return component;
    }

    public void setText(int viewId, String text) {
        Text tv = getView(viewId);
        tv.setText(text);
    }

    public void setImageResource(int viewId, int resId) {
        Image image = getView(viewId);
        image.setPixelMap(resId);
        image.setScaleMode(Image.ScaleMode.STRETCH);
    }

}
