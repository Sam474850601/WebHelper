package com.to8to.utils.webhelper_sample.ui.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.to8to.utils.webhelper.support.ui.activity.TX5WebActivity;
import com.to8to.utils.webhelper.support.ui.adapter.common.CommonListAdapter;
import com.to8to.utils.webhelper.support.ui.widget.X5WebView;
import com.to8to.utils.webhelper.support.ui.widget.actionbar.TToolbar;
import com.to8to.housekeeper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by same.li on 2018/1/30.
 */

public class TToobarWindowTest1Activity extends TX5WebActivity<TToobarWindowTest1Activity.MenuItemData> {


    @Override
    public void onInitData(Bundle savedInstanceState) {

    }


    @Override
    protected void onInitViews(View parent, X5WebView x5WebView) {
        setTitle("自定义菜单列表");
        TToolbar tToolbar =  getTToolbar();
        //gridview必须需要配置 lv_moreitems这个固定id
        //设置弹窗popuwindow布局
        tToolbar.setWindowLayout(R.layout.window_botton_menu, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        CommonListAdapter adapter = new CommonListAdapter<MenuItemData>(this,
                R.layout.item_menueitems) {
            @Override
            public void onItemUpdate(ViewHolder helper, MenuItemData item, int postion) {
                ImageView imageView =  helper.getView(R.id.iv_menu_ic);
                TextView textView =  helper.getView(R.id.tv_title);
                textView.setText(item.title);
                imageView.setImageResource(item.icon);
            }
        };

        tToolbar.setAdapter(adapter);

        //更换
        tToolbar.setOnShowProxy(new TToolbar.OnShowProxy() {
            @Override
            public void onShow(TToolbar toolbar) {
                toolbar.showWindowAtLocation(parentView, Gravity.BOTTOM, 0, 0);
            }
        });
        // initWindow后才能使用window
        tToolbar.initWindow();

        final PopupWindow popupWindow =  tToolbar.getWindow();
        TextView textView = (TextView) popupWindow.getContentView().findViewById(R.id.tv_windowTitle);
        textView.setText("网页由xxx提供");

        //设置window动画
        popupWindow.setAnimationStyle(R.style.windowStyle);

        List<MenuItemData> list = new ArrayList<>();
        MenuItemData shareData = new MenuItemData();
        shareData.title  =  "分享";
        shareData.icon = R.mipmap.ic_launcher;
        list.add(shareData);
        MenuItemData followData = new MenuItemData();
        followData.title  =  "收藏";
        followData.icon = R.mipmap.ic_launcher;
        list.add(followData);
        MenuItemData searchContent = new MenuItemData();
        searchContent.title  =  "搜索页面内容";
        searchContent.icon = R.mipmap.ic_launcher;
        list.add(searchContent);

        MenuItemData copyUrl = new MenuItemData();
        copyUrl.title  =  "复制页面内容";
        copyUrl.icon = R.mipmap.ic_launcher;
        list.add(copyUrl);

        updateMoreItemIcon(R.mipmap.ic_more);


        MenuItemData readUserHome = new MenuItemData();
        readUserHome.title  =  "查看用户主页";
        readUserHome.icon = R.mipmap.ic_launcher;
        list.add(readUserHome);


        MenuItemData openOnBrower = new MenuItemData();
        openOnBrower.title  =  "在浏览器打开";
        openOnBrower.icon = R.mipmap.ic_launcher;
        list.add(openOnBrower);


        MenuItemData freshPage = new MenuItemData();
        freshPage.title  =  "刷新";
        freshPage.icon = R.mipmap.ic_launcher;
        list.add(freshPage);

        MenuItemData complaint = new MenuItemData();
        complaint.title  =  "投诉";
        complaint.icon = R.mipmap.ic_launcher;
        list.add(complaint);
        adapter.setData(list);
        adapter.notifyDataSetChanged();
    }


    public static class MenuItemData
    {
        public String title;
        public int icon;
    }

    @Override
    public void onMoreItemClike(int position, MenuItemData data) {
        Toast.makeText(getApplicationContext(), data.title, Toast.LENGTH_SHORT).show();
    }
}
