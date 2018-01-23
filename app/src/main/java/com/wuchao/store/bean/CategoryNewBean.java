package com.wuchao.store.bean;

import java.util.List;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategoryNewBean {
    private Head head ;
    private List<AppBean> appBeanList ;

    public CategoryNewBean(Head head, List<AppBean> appBeanList) {
        this.head = head;
        this.appBeanList = appBeanList;
    }

    public Head getHead() {
        return head;
    }

    public List<AppBean> getAppBeanList() {
        return appBeanList;
    }

    public static class Head {
        private String icon ;
        private String intro ;

        public String getIcon() {
            return icon;
        }

        public String getIntro() {
            return intro;
        }

        public Head(String icon, String intro) {

            this.icon = icon;
            this.intro = intro;
        }
    }
}
