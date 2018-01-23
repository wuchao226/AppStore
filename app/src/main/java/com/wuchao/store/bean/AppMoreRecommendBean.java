package com.wuchao.store.bean;

import java.util.List;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppMoreRecommendBean {

    private List<AppBean> moreAppBean ;

    public AppMoreRecommendBean(List<AppBean> moreAppBean) {
        this.moreAppBean = moreAppBean;
    }

    public List<AppBean> getMoreAppBean() {
        return moreAppBean;
    }
}
