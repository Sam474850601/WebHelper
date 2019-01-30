package com.to8to.utils.webhelper.support.ui.widget.actionbar;


import com.to8to.utils.webhelper.support.ui.view.IView;

/**
 * Created by same.li on 2018/1/15.
 */

public interface IActionBarSettingView extends IView
{
    void setTitle(String title);


    void setTitleColor(int  color);

    void setToolbarBackgroundColor(int color);

    //设置透明度
    void setActionbarAlpha(int alpha);



    //副标题
    void setSubtitle(String title);

    //副标题颜色
    void setSubtitleTextColor(int color);


    /**
     * 1.如果3个图标，只刷新这个方法，那么这个图标靠最右边。
     * 2.如果调用了updateMiddleItemIcon（ico1）（或updateMoreItemIcon（icon））， 同时调用这个方法updateMiddleItemIcon（ico0）
     * 那么ico1会在最右侧，ico0在ico1左侧
     * 3.如果调用了updateMiddleItemIcon（ico1）， 同时调用这个方法updateMiddleItemIcon（ico0），还有
     *   updateMoreItemIcon(ico2), 那么它门都会在右侧，排序顺序是. ico0->ico1->ico2
     * @param icon 更新图标
     */
    void  updateLeftItemIcon(int icon);


    /**
     * 1.如果3个图标，只刷新这个方法，那么这个图标靠最右边。
     * 2.如果调用了updateMiddleItemIcon（ico1）（或updateMoreItemIcon（icon））， 同时调用这个方法updateMiddleItemIcon（ico0）
     * 那么ico1会在最右侧，ico0在ico1左侧
     * 3.如果调用了updateMiddleItemIcon（ico1）， 同时调用这个方法updateMiddleItemIcon（ico0），还有
     *   updateMoreItemIcon(ico2), 那么它门都会在右侧，排序顺序是. ico0->ico1->ico2
     * @param icon 更新图标
     */
    void  updateMiddleItemIcon(int icon);


    /**
     * 1.如果3个图标，只刷新这个方法，那么这个图标靠最右边。
     * 2.如果调用了updateMiddleItemIcon（ico1）（或updateMoreItemIcon（icon））， 同时调用这个方法updateMiddleItemIcon（ico0）
     * 那么ico1会在最右侧，ico0在ico1左侧
     * 3.如果调用了updateMiddleItemIcon（ico1）， 同时调用这个方法updateMiddleItemIcon（ico0），还有
     *   updateMoreItemIcon(ico2), 那么它门都会在右侧，排序顺序是. ico0->ico1->ico2
     * @param icon 更新图标
     */
    void  updateMoreItemIcon(int icon);

    void  hideLeftItemIcon();

    void  hideMiddleItemIcon();

    void  hideMoreItemIcon();

    //设置bar最左侧的图标,通常是用做返回图标
    void setNavigationIcon(int icon);

    //
    void showLeftItemIcon();

    void showMiddleItemIcon();

    void showMoreItemIcon();

    //设置标题右侧的图标
    void setLogo(int icon);

}
