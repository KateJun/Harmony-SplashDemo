package com.harmony.mysplashdemo.adapter;

import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.PageSliderProvider;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends PageSliderProvider {

    private List<Component> pages;


    public MainPagerAdapter(ArrayList<Component> pages) {
        this.pages = pages;
    }

    @Override
    public int getCount() {
        return pages == null ? 0 : pages.size();
    }


    @Override
    public Object createPageInContainer(ComponentContainer componentContainer, int i) {
        componentContainer.addComponent(pages.get(i));
        return componentContainer;
    }

    @Override
    public void destroyPageFromContainer(ComponentContainer componentContainer, int i, Object o) {
        componentContainer.removeComponent(pages.get(i));
    }

    @Override
    public boolean isPageMatchToObject(Component component, Object o) {
        return component == o;
    }

}
