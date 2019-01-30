package com.to8to.utils.webhelper.support.bean;

public   class ShareDatas
    {
        ShareData qq;
        ShareData qZone;
        ShareData sina;
        ShareData wechat;
        ShareData wechatCircle;


        public ShareData getQq() {
            return qq;
        }

        public void setQq(ShareData qq) {
            this.qq = qq;
        }

        public ShareData getqZone() {
            return qZone;
        }

        public void setqZone(ShareData qZone) {
            this.qZone = qZone;
        }

        public ShareData getSina() {
            return sina;
        }

        public void setSina(ShareData sina) {
            this.sina = sina;
        }

        public ShareData getWechat() {
            return wechat;
        }

        public void setWechat(ShareData wechat) {
            this.wechat = wechat;
        }

        public ShareData getWechatCircle() {
            return wechatCircle;
        }

        public void setWechatCircle(ShareData wechatCircle) {
            this.wechatCircle = wechatCircle;
        }

        @Override
        public String toString() {
            return "ShareDatas{" +
                    "qq=" + qq +
                    ", qZone=" + qZone +
                    ", sina=" + sina +
                    ", wechat=" + wechat +
                    ", wechatCircle=" + wechatCircle +
                    '}';
        }
    }