package com.wuchao.store.bean;

import android.graphics.drawable.Drawable;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppInfo {

    private String name ;
    private String packageName ;
    private Drawable icon ;
    private long firstInstallTime ;
    private String versionName ;

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public long getFirstInstallTime() {
        return firstInstallTime;
    }

    public String getVersionName() {
        return versionName;
    }

    public AppInfo(String name, String packageName, Drawable icon, long firstInstallTime, String versionName) {

        this.name = name;
        this.packageName = packageName;
        this.icon = icon;
        this.firstInstallTime = firstInstallTime;
        this.versionName = versionName;
    }
}
