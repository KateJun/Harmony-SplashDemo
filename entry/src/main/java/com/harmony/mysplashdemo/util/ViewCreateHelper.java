package com.harmony.mysplashdemo.util;

import com.harmony.mysplashdemo.ResourceTable;
import com.harmony.mysplashdemo.adapter.ListAdapter;
import com.harmony.mysplashdemo.adapter.ViewHolder;
import com.harmony.mysplashdemo.beans.NewsInfo;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.ListContainer;
import ohos.app.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ViewCreateHelper {

    private Context slice;
    private HashMap<String, ListAdapter> cache;

    public ViewCreateHelper(Context abilitySlice) {
        this.slice = abilitySlice;
        cache = new HashMap<>();
    }

    public Component createView(String title) {
        Component mainComponent =
                LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_pager_item, null, false);
        if (!(mainComponent instanceof ComponentContainer)) {
            return null;
        }
        ComponentContainer rootLayout = (ComponentContainer) mainComponent;
        initView(mainComponent,title);
        return rootLayout;
    }

    public void initView(Component mainComponent,String title) {
        // 列表
        ListContainer  listContainer = (ListContainer) mainComponent.findComponentById(ResourceTable.Id_list_main);
        ListAdapter<NewsInfo> listAdapter = new ListAdapter<NewsInfo>(slice, ResourceTable.Layout_item_news_layout, getData(0, title)) {
            @Override
            public void convert(ViewHolder viewHolder, NewsInfo item, int position) {
                viewHolder.setText(ResourceTable.Id_item_news_title, item.getTitle());
                viewHolder.setText(ResourceTable.Id_item_news_desc, item.getDescription());

            }
        };
        listContainer.setItemProvider(listAdapter);
        listContainer.setItemClickedListener((list, component, i, l) -> {
//            Intent in = new Intent();
//            in.setElementName("", component.getContext().getBundleName(), DetailAbility.class.getName());
//            slice.startAbility(in, 0);
        });

        cache.put(title, listAdapter);
    }


    public List<NewsInfo> getData(int tab, String title) {
        List<NewsInfo> curData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            NewsInfo info = new NewsInfo();
            info.setTitle("标题：" + title + i);
            info.setDescription("我是描述啦" + title + Math.random());
            curData.add(info);
        }
        return curData;
    }

}
