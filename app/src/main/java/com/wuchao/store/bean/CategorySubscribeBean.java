package com.wuchao.store.bean;

import java.util.List;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategorySubscribeBean {
    List<AppBean> appBeanList ;

    public CategorySubscribeBean(List<AppBean> appBeanList) {
        this.appBeanList = appBeanList;
    }

    public List<AppBean> getAppBeanList() {
        return appBeanList;
    }
}
