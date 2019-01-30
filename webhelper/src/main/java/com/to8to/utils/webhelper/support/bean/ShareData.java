package com.to8to.utils.webhelper.support.bean;

/**
 * Created by same.li on 2018/2/9.
 */

public class ShareData
{
    private String title;
    private String content;
    private String imgUrl;
    private String targetUrl;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Override
    public String toString() {
        return "ShareData{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", targetUrl='" + targetUrl + '\'' +
                '}';
    }
}
