package com.to8to.utils.webhelper.support.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.to8to.utils.webhelper.R;
import com.to8to.utils.webhelper.support.ui.adapter.common.CommonListAdapter;


public class DfActionbarListAdapter extends CommonListAdapter<DfActionbarListAdapter.Item> {


    public DfActionbarListAdapter(Context context) {
        super(context, R.layout.item_more);
    }

    public DfActionbarListAdapter(Context context, int res) {
        super(context, res);
    }


    @Override
    public void onItemUpdate(ViewHolder helper, DfActionbarListAdapter.Item item, int postion) {
        ImageView icon = helper.getView(R.id.iv_item);
        icon.setImageResource(item.icon);
        TextView title = helper.getView(R.id.tv_item);
        title.setText(item.title);
    }


    public static class Item {
        public int icon;
        public String title;
        public Object data;
    }


}
