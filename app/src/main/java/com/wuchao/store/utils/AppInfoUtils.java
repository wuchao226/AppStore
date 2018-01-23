package com.wuchao.store.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.wuchao.store.bean.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppInfoUtils {


    private static final String SCHEME = "package";
    /**
     * 调用系统InstalledAppDetails界面所需的Extra名称(用于Android 2.1及之前版本)
     */
    private static final String APP_PKG_NAME_21 = "com.android.settings.ApplicationPkgName";
    /**
     * 调用系统InstalledAppDetails界面所需的Extra名称(用于Android 2.2)
     */
    private static final String APP_PKG_NAME_22 = "pkg";
    /**
     * InstalledAppDetails所在包名
     */
    private static final String APP_DETAILS_PACKAGE_NAME = "com.android.settings";
    /**
     * InstalledAppDetails类名
     */
    private static final String APP_DETAILS_CLASS_NAME = "com.android.settings.InstalledAppDetails";




    public static List<AppInfo> getAppInfos(Context context){
        List<AppInfo> appInfoList = new ArrayList<>() ;

        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取已安装的包信息
        List<PackageInfo> packageInfos = pm.getInstalledPackages(0);

        for(PackageInfo packageInfo : packageInfos){
            //获取包名
            String packageName = packageInfo.packageName;
            //获取应用图标
            Drawable icon = packageInfo.applicationInfo.loadIcon(pm);
            //获取应用的名称
            String name = packageInfo.applicationInfo.loadLabel(pm).toString();
            //获取第一次安装的时间
            long firstInstallTime = packageInfo.firstInstallTime;
            //获取版本号
            int versionCode = packageInfo.versionCode;
            //获取版本名称
            String versionName = packageInfo.versionName;

            AppInfo appInfo = new AppInfo(name,packageName,icon,firstInstallTime,versionName);
            appInfoList.add(appInfo);
        }
        return appInfoList ;
    }

    public static void uninstallApplication(Context context,String packageName){
        Intent intent = new Intent() ;
        intent.setAction("android.intent.action.DELETE");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("package:"+packageName));
        context.startActivity(intent);
    }

    public static void openApplication(Context context,String packageName) {
        Intent intent=isexit(context,packageName);
        if(intent==null){
            System.out.println("APP not found!....:"+packageName);
        }
        context.startActivity(intent);
    }

    /**
     * 通过packagename判断应用是否安装
     * @param context
     *
     * @return 跳转的应用主activity Intent
     * */

    public static Intent isexit(Context context,String pk_name){
        //获取包管理器
        PackageManager packageManager = context.getPackageManager();
        //通过包名获取Intent
        Intent it= packageManager.getLaunchIntentForPackage(pk_name);
        return it;
    }

    public static void showInstalledAppDetails(Context context, String packageName) {
        Intent intent = new Intent();
        final int apiLevel = Build.VERSION.SDK_INT;
        if (apiLevel >= 9) { // 2.3（ApiLevel 9）以上，使用SDK提供的接口
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts(SCHEME, packageName, null);
            intent.setData(uri);
        } else { // 2.3以下，使用非公开的接口（查看InstalledAppDetails源码）
            // 2.2和2.1中，InstalledAppDetails使用的APP_PKG_NAME不同。
            final String appPkgName = (apiLevel == 8 ? APP_PKG_NAME_22
                    : APP_PKG_NAME_21);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName(APP_DETAILS_PACKAGE_NAME,
                    APP_DETAILS_CLASS_NAME);
            intent.putExtra(appPkgName, packageName);
        }
        context.startActivity(intent);
    }

    public static void install(String path){
        Intent installIntent = new Intent(Intent.ACTION_VIEW);
        installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        installIntent.setDataAndType(Uri.parse("file://" + path),
                "application/vnd.android.package-archive");
        UIUtils.getContext().startActivity(installIntent);
    }
}
