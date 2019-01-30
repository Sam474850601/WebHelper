package com.to8to.utils.webhelper.support.bean;

public  class ToolbarBuilder {
        private String title;
        private String subTitle;
        private int titleColor;
        private int subTitleColor;
        private int navigationIcon;
        private int backgound;
        private int logo;

        public ToolbarBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ToolbarBuilder setSubTitle(String subTitle) {
            this.subTitle = subTitle;
            return this;
        }

        public ToolbarBuilder setTitleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public ToolbarBuilder setSubTitleColor(int subTitleColor) {
            this.subTitleColor = subTitleColor;
            return this;
        }

        public ToolbarBuilder setNavigationIcon(int navigationIcon) {
            this.navigationIcon = navigationIcon;
            return this;
        }

        public ToolbarBuilder setBackgound(int backgound) {
            this.backgound = backgound;
            return this;
        }

        public ToolbarBuilder setLogo(int logo) {
            this.logo = logo;
            return this;
        }

        public ToolbarAttr build() {
            final ToolbarAttr attr = new ToolbarAttr();
            attr.setBackground(backgound);
            attr.setLogo(logo);
            attr.setNavigationIcon(navigationIcon);
            attr.setSubTitle(subTitle);
            attr.setTitle(title);
            attr.setTitleColor(titleColor);
            attr.setSubTitleColor(subTitleColor);
            return attr;
        }
    }