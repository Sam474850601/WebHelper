package com.to8to.utils.webhelper.support.ui.activity;

import android.os.Bundle;
import android.view.View;

/**
 * Created by same.li on 2018/1/15.
 */

public interface IActivity {

    int getLayoutResId();

    void onInitViews(View parent);

    void onInitData(Bundle savedInstanceState);



}
