package com.wjs.myapplication.been;

import java.util.List;

/**
 * Created by Administrator on 2015/7/17.
 */
public class Event {
    /** 列表加载事件 */
    public static class ItemListEvent
    {
        private List<Item> items;

        public ItemListEvent(List<Item> items)
        {
            this.items = items;
        }

        public List<Item> getItems()
        {
            return items;
        }
    }
}
