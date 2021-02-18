package com.harmony.mysplashdemo.slice;

import com.harmony.mysplashdemo.ResourceTable;

import com.harmony.mysplashdemo.adapter.MainPagerAdapter;
import com.harmony.mysplashdemo.util.ViewCreateHelper;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.PageSlider;
import ohos.agp.components.TabList;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;

import java.io.IOException;
import java.util.ArrayList;

public class MainAbilitySlice extends AbilitySlice implements TabList.TabSelectedListener, PageSlider.PageChangedListener {

    private PageSlider pageSlider;
    private TabList tabList;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        initView();
        initData();
    }

    private void initData() {
        try {
            String[] tabsTitle = getResourceManager().getElement(ResourceTable.Strarray_tab_title).getStringArray();

            for (String title: tabsTitle){
                TabList.Tab tab = tabList.new Tab(getContext());
                tab.setText(title);
                tabList.addTab(tab);
            }
            tabList.selectTabAt(0);

            pageSlider.setProvider(new MainPagerAdapter(initPageSliderViewData()));
            pageSlider.setCurrentPage(0);
            pageSlider.setReboundEffect(true);
            pageSlider.setCentralScrollMode(true);
        } catch (IOException | NotExistException | WrongTypeException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Component> initPageSliderViewData() {
        int pageSize = tabList.getTabCount();
        ArrayList<Component> pages = new ArrayList<>();
       ViewCreateHelper viewCreateHelper = new ViewCreateHelper(getContext());
        for (int i = 0; i < pageSize; i++) {
            pages.add(viewCreateHelper.createView(tabList.getTabAt(i).getText()));
        }
        return pages;
    }


    private void initView() {
        tabList = (TabList) findComponentById(ResourceTable.Id_title_tabs);
        pageSlider = (PageSlider) findComponentById(ResourceTable.Id_pager);

        tabList.addTabSelectedListener(this);
        pageSlider.addPageChangedListener(this);
    }

    @Override
    public void onPageSliding(int i, float v, int i1) {

    }

    @Override
    public void onPageSlideStateChanged(int i) {

    }

    @Override
    public void onPageChosen(int i) {
        if (tabList.getSelectedTab().getPosition() != i) {
            tabList.selectTabAt(i);
        }
    }

    @Override
    public void onSelected(TabList.Tab tab) {
        if (pageSlider.getCurrentPage() != tab.getPosition()) {
            pageSlider.setCurrentPage(tab.getPosition());
        }
    }

    @Override
    public void onUnselected(TabList.Tab tab) {

    }

    @Override
    public void onReselected(TabList.Tab tab) {

    }
}