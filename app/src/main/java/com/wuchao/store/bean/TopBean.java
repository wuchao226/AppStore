package com.wuchao.store.bean;

import java.util.List;
import java.util.Map;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class TopBean {

    private Map<String,List<AppBean>> appBeanMap ;
    private List<TopTopBean> topTopBeanList ;

    public TopBean(Map<String, List<AppBean>> appBeanMap, List<TopTopBean> topTopBeanList) {
        this.appBeanMap = appBeanMap;
        this.topTopBeanList = topTopBeanList;
    }

    public Map<String, List<AppBean>> getAppBeanMap() {
        return appBeanMap;
    }

    public List<TopTopBean> getTopTopBeanList() {
        return topTopBeanList;
    }

    public static class TopTopBean {

        private String iconUrl;
        private String name;

        public TopTopBean(String iconUrl, String name) {
            this.iconUrl = iconUrl;
            this.name = name;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public String getName() {
            return name;
        }
    }
}
