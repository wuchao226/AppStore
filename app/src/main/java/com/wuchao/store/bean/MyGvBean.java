package com.wuchao.store.bean;

/**
 * Created by xzhang on 2017/5/25.
 */

public class MyGvBean {

    private String name ;
    private int iconId ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconUrl(int iconId) {
        this.iconId = iconId;
    }

    public MyGvBean(String name, int iconId) {

        this.name = name;
        this.iconId = iconId;
    }
}
