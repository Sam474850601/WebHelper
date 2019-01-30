package com.to8to.utils.webhelper.support.webservice.component;

import android.content.Context;
import android.graphics.Color;

import com.to8to.utils.webhelper.core.TBaseComponent;
import com.to8to.utils.webhelper.core.ann.RequestMethod;
import com.to8to.utils.webhelper.support.ui.widget.actionbar.IActionBarSettingView;

/**
 * Created by same.li on 2018/1/18.
 */


public class ToolbarComponent extends TBaseComponent {


    @RequestMethod("updateTitle")
    void setTitle(IActionBarSettingView view, String title)
    {
        view.setTitle(title);
    }


    @RequestMethod("updateTitleColor")
    void setTitleColor( IActionBarSettingView view , String  color)
    {

        view.setTitleColor(Color.parseColor(color));
    }


    @RequestMethod("updateBackgroundColor")
    void setBackgroundColor(IActionBarSettingView view,String color)
    {
        view.setToolbarBackgroundColor(Color.parseColor(color));
    }


    @RequestMethod("updateActionbarAlpha")
    void setActionbarAlpha(IActionBarSettingView view, int alpha)
    {
        view.setActionbarAlpha(alpha);
    }



    @RequestMethod("updateSubtitle")
    void setSubtitle(IActionBarSettingView view, String title){
        view.setSubtitle(title);
    }


    @RequestMethod("updateSubtitleTextColor")
    void setSubtitleTextColor(IActionBarSettingView view, String color){
        view.setSubtitleTextColor(Color.parseColor(color));

    }



    @RequestMethod("hideLeftItemIcon")
    void  hideLeftItemIcon(IActionBarSettingView view)
    {
        view.hideLeftItemIcon();
    }


    @RequestMethod("hideMiddleItemIcon")
    void  hideMiddleItemIcon(IActionBarSettingView view)
    {
        view.hideMiddleItemIcon();
    }


    @RequestMethod("hideMoreItemIcon")
    void  hideMoreItemIcon(IActionBarSettingView view)
    {

        view.hideMoreItemIcon();
    }



    @RequestMethod("showLeftItemIcon")
    void showLeftItemIcon(IActionBarSettingView view)
    {

        view.showLeftItemIcon();
    }

    @RequestMethod("showMiddleItemIcon")
    void showMiddleItemIcon(IActionBarSettingView view)
    {

        view.showMiddleItemIcon();
    }


    @RequestMethod("showMoreItemIcon")
    void showMoreItemIcon(IActionBarSettingView view)
    {
        view.showMoreItemIcon();
    }



}
