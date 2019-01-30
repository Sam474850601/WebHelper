package com.to8to.utils.webhelper.support.ui.adapter.common;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共适配器
 */
public abstract class CommonListAdapter<T> extends BaseAdapter
{  
    protected LayoutInflater mInflater;
    protected Context mContext;
    public List<T> mDatas = new ArrayList<T>();
    protected final int layoutId;
  
    public CommonListAdapter(Context context, int layoutId)
    {  
        this.mContext = context;  
        this.mInflater = LayoutInflater.from(mContext);
        this.layoutId = layoutId;
    }

    public void notifyDataSetChanged(List<T>  datas, boolean needClear ) {
        if(needClear)
            mDatas.clear();
        if(null == datas)
            return;
        mDatas.addAll(datas);
        super.notifyDataSetChanged();
    }


    public void setData(List<T> data) {
        this.mDatas = data;
    }

    @Override
    public int getCount()  
    {  
        return mDatas.size();  
    }  
  
    @Override  
    public T getItem(int position)  
    {  
        return mDatas.get(position);  
    }  
  
    @Override  
    public long getItemId(int position)  
    {  
        return position;  
    }  
  
    @Override  
    public View getView(int position, View convertView, ViewGroup parent)
    {  
        final ViewHolder viewHolder = getViewHolder(position, convertView,  
                parent);
        onItemUpdate(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();  
  
    }  
  
    public abstract void onItemUpdate(ViewHolder helper, T item, int postion);
  
    private ViewHolder getViewHolder(int position, View convertView,  
            ViewGroup parent)
    {  
        return ViewHolder.get(mContext, convertView, parent, layoutId,
                position);  
    }

    public static class ViewHolder {
        private final SparseArray<View> mViews;
        private int mPosition;
        private View mConvertView;

        private ViewHolder(Context context, ViewGroup parent, int layoutId,
                           int position) {
            this.mPosition = position;
            this.mViews = new SparseArray<View>();
            mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                    false);
            mConvertView.setTag(this);
        }


        public static ViewHolder get(Context context, View convertView,
                                     ViewGroup parent, int layoutId, int position) {
            if (convertView == null) {
                return new ViewHolder(context, parent, layoutId, position);
            }
            return (ViewHolder) convertView.getTag();
        }

        public View getConvertView() {
            return mConvertView;
        }

        /**
         * 通过控件的Id获取对于的控件，如果没有则加入views
         */
        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }
    }

}  
