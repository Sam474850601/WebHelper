package com.to8to.utils.webhelper.support.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.to8to.utils.webhelper.R;
import com.to8to.utils.webhelper.support.ui.widget.actionbar.IActionBarSettingView;


/**
 * Created by same.li on 2018/1/15.
 */

public  abstract class BaseToolbarActvity extends AppCompatActivity implements IActionBarSettingView

{
    public   abstract  Toolbar getToolbar();

    protected abstract   void onNavigationViewClike();

    protected abstract  int  getMenuRes();

    private int leftIcon = 0;
    private int midleIcon = 0;
    private int moreIcon = 0;


    private boolean isLeftIconShowing = false;

    private boolean isMiddleIconShowing = false;

    private boolean isMoreIconShowing = false;


    @Override
    public void setTitle(String title) {
        getToolbar().setTitle(title);
    }




    @Override
    public void setTitleColor(int textColor) {
        getToolbar().setTitleTextColor(textColor);
    }


    @Override
    public void setActionbarAlpha(int alpha) {
        getToolbar().setAlpha(alpha);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem leftItem =  menu.findItem(R.id.action_left);
        leftItem.setIcon(leftIcon);
        leftItem.setVisible(isLeftIconShowing);

        MenuItem midleItem =  menu.findItem(R.id.action_middle);
        midleItem.setIcon(midleIcon);
        midleItem.setVisible(isMiddleIconShowing);

        MenuItem moreItem =  menu.findItem(R.id.action_more);
        moreItem.setIcon(moreIcon);
        moreItem.setVisible(isMoreIconShowing);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(getMenuRes(), menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public void updateLeftItemIcon(int icon) {
        leftIcon = icon;
        isLeftIconShowing = true;
        invalidateOptionsMenu();
    }

    @Override
    public void updateMiddleItemIcon(int icon) {
        midleIcon = icon;
        isMiddleIconShowing = true;
        invalidateOptionsMenu();
    }

    @Override
    public void updateMoreItemIcon(int icon) {
        moreIcon = icon;
        isMoreIconShowing = true;
        invalidateOptionsMenu();
    }

    @Override
    public void hideLeftItemIcon() {
        isLeftIconShowing = false;
        invalidateOptionsMenu();
    }

    @Override
    public void hideMiddleItemIcon() {
        isMiddleIconShowing = false;
        invalidateOptionsMenu();
    }


    @Override
    public void hideMoreItemIcon() {
        isMoreIconShowing = false;
        invalidateOptionsMenu();
    }



    @Override
    public void setSubtitle(String title) {
        getToolbar().setSubtitle(title);
    }

    @Override
    public void setSubtitleTextColor(int color) {
        getToolbar().setSubtitleTextColor(color);
    }





    @Override
    public void setNavigationIcon(int icon) {
        getToolbar().setNavigationIcon(icon);
    }




    @Override
    public void showLeftItemIcon() {
        isLeftIconShowing = true;
        invalidateOptionsMenu();
    }

    @Override
    public void showMiddleItemIcon() {
        isMiddleIconShowing = true;
        invalidateOptionsMenu();
    }


    @Override
    public void setToolbarBackgroundColor(int color) {
        getToolbar().setBackgroundColor(color);
    }

    @Override
    public void showMoreItemIcon() {
        isMoreIconShowing = true;
        invalidateOptionsMenu();
    }

    @Override
    public void setLogo(int icon) {
        getToolbar().setLogo(icon);
    }
}
