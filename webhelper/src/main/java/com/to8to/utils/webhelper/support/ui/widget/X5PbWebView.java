package com.to8to.utils.webhelper.support.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.to8to.utils.webhelper.R;

/**
 * Created by same.li on 2018/2/1.
 */

public class X5PbWebView extends LinearLayout {
    private ProgressBar progressBar;
    private X5WebView x5WebView;
    final  DefaultChromeClient defaultChromeClient = new DefaultChromeClient();
    final  DefaultWebViewClient defaultWebViewClient = new DefaultWebViewClient();

    public X5PbWebView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public X5PbWebView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {

        setOrientation(LinearLayout.VERTICAL);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.X5PbWebView);

        float progressHeight = typedArray.getDimension(R.styleable.X5PbWebView_progressHeight,
              getResources().getDimension(R.dimen.pgHeight));

        View pbLayout   =  LayoutInflater.from(context).inflate(R.layout.layout_pb, null);
        addView(pbLayout);
        progressBar = (ProgressBar) findViewById(R.id.pb_web);
        LayoutParams progressBarlayoutParams = (LayoutParams) progressBar.getLayoutParams();
        progressBarlayoutParams.height = (int) progressHeight;
        progressBar.setLayoutParams(progressBarlayoutParams);
        int  progressDrawableId = typedArray.getResourceId(R.styleable.X5PbWebView_progressDrawable,
                R.drawable.progress_horizontal);
        progressBar.setIndeterminate(false);
        progressBar.setProgressDrawable(getResources().getDrawable(progressDrawableId));
        defaultChromeClient.progressBar = progressBar;
        defaultWebViewClient.progressBar = progressBar;
        x5WebView  = new X5WebView(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        x5WebView.setLayoutParams(layoutParams);
        x5WebView.setWebViewClient(defaultWebViewClient);
        x5WebView.setWebChromeClient(defaultChromeClient);
        addView(x5WebView);
        typedArray.recycle();
    }

    public void setProgress(int newProgress)
    {
        progressBar.setProgress(newProgress);
    }

    public ProgressBar getProgressBar()
    {
        return progressBar;
    }

    public void setProgressDrawable(Drawable drawable)
    {
        progressBar.setProgressDrawable(drawable);
    }


    public void loadUrl(String url)
    {
        x5WebView.loadUrl(url);
    }

    final  static   class DefaultChromeClient extends WebChromeClient
    {
        private volatile ProgressBar progressBar;
        @Override
        public void onProgressChanged(WebView webView, int newProgress) {
            if(null == progressBar)
                return;
            Log.e("newProgress", ""+newProgress);
            progressBar.setProgress(newProgress);
        }
    }



    public void destroy()
    {
        defaultChromeClient.progressBar = null;
        defaultWebViewClient.progressBar = null;
        removeView(x5WebView);
        x5WebView.destroy();
    }

    public X5WebView getWebView()
    {
        return  x5WebView;
    }

    final  static    class  DefaultWebViewClient extends WebViewClient
    {
        private volatile ProgressBar progressBar;

        /**
         * 防止加载网页时调起系统浏览器
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            WebView.HitTestResult hitTestResult = webView.getHitTestResult();
            if (!TextUtils.isEmpty(url) && hitTestResult == null) {
                webView.loadUrl(url);
                return true;
            }
            return false;
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            if(null == progressBar)
                return;

            try
            {
                if (GONE !=  progressBar.getVisibility() )
                    progressBar.setVisibility(GONE);
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }

        }

        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
            if(null == progressBar)
                return;
            try
            {
                if (VISIBLE !=  progressBar.getVisibility() )
                    progressBar.setVisibility(VISIBLE);
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }
    }



    public void onPageFinished(WebView webView, String s) {
        defaultWebViewClient.onPageFinished(webView, s);

    }

    public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
        defaultWebViewClient.onPageStarted(webView, s, bitmap);
    }

    public void onProgressChanged(WebView webView, int newProgress) {
        defaultChromeClient.onProgressChanged(webView, newProgress);
    }

}
