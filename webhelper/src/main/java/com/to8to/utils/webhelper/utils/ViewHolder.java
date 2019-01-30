package com.to8to.utils.webhelper.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by same.li on 2018/2/8.
 */

public final  class ViewHolder  {

    private final SparseArray<View> views = new SparseArray<>();

    private View mParent;

    public void setView(View parent)
    {
        mParent = parent;
    }

    public <T extends View> T loadView(int viewId)
    {
        View view = views.get(viewId);
        if(null == view)
        {
            views.put(viewId, view = mParent.findViewById(viewId));
        }
        return (T) view;
    }
}
