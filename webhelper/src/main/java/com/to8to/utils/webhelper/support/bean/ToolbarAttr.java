package com.to8to.utils.webhelper.support.bean;

import java.io.Serializable;

public class ToolbarAttr implements Serializable {
    protected ToolbarAttr(){}
    private String title;
    private String subTitle;
    private int titleColor;
    private int subTitleColor;
    private int navigationIcon;
    private int background;
    private int logo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getSubTitleColor() {
        return subTitleColor;
    }

    public void setSubTitleColor(int subTitleColor) {
        this.subTitleColor = subTitleColor;
    }

    public int getNavigationIcon() {
        return navigationIcon;
    }

    public void setNavigationIcon(int navigationIcon) {
        this.navigationIcon = navigationIcon;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getBackground() {
        return background;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}