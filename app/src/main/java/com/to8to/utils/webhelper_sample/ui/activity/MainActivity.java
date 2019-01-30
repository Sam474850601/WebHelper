package com.to8to.utils.webhelper_sample.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.to8to.utils.webhelper.support.bean.ToolbarBuilder;
import com.to8to.utils.webhelper.support.bean.ToolbarAttr;
import com.to8to.utils.webhelper.support.ui.activity.TDefaultWebActivity;
import com.to8to.housekeeper.R;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test7Clike(View view)
    {
        Intent intent =   new Intent(getApplicationContext() , X5PgActivity.class);
        startActivity(intent);
    }

    public void test8Clike(View view)
    {

        TDefaultWebActivity.jumpX5Browser(this , "http://www.qq.com", "腾讯");

    }

    public void test8_1Clike(View view)
    {

        ToolbarAttr attr = new ToolbarBuilder()
                .setBackgound(Color.parseColor("#909090"))
                .setNavigationIcon(R.mipmap.icon_close_white)
                .setTitle("腾讯网站")
//                .setTitleColor(Color.GREEN)
//                .setSubTitle("副标题")
//                .setSubTitleColor(Color.parseColor("#ffaa00cc"))
//                .setLogo(R.mipmap.ic_launcher)
                .build();
        TDefaultWebActivity.jumpX5Browser(this ,
                "http://www.qq.com", attr);

    }


    public void test1Clike(View view)
    {
       startActivity(new Intent(getApplicationContext() , Test1Activity.class));
    }

    public void test2Clike(View view)
    {
        startActivity(new Intent(getApplicationContext() , Test2Activity.class));
    }


    public void test3Clike(View view)
    {
        startActivity(new Intent(getApplicationContext() , Test3Activity.class));
    }

    public void test4Clike(View view)
    {
        Intent intent =   new Intent(getApplicationContext() , SampleTX5WebActivity.class);
        startActivity(intent);
    }


    public void test5Clike(View view)
    {
        Intent intent =   new Intent(getApplicationContext() , Sample2TX5WebActivity.class);
        startActivity(intent);
    }

    public void test6Clike(View view)
    {
        Intent intent =   new Intent(getApplicationContext() , TToobarWindowTest1Activity.class);

        startActivity(intent);
    }

    public void dialogClike(View view)
    {
        Intent intent =   new Intent(getApplicationContext() , DialogSampleActivity.class);
        startActivity(intent);
    }


}
