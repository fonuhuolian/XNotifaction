package org.fonuhuolian.xnotifaction.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;

public class XNotifactionUtils {

    /**
     * 是否开启了通知提醒
     */
    public static boolean isNotificationEnabled(Context context) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        return notificationManagerCompat.areNotificationsEnabled();
    }


    /**
     * 跳转到开启通知页面
     */
    public static void jumpToAppDetailsSettings(Context context) {
        // 进入设置系统应用权限界面
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(intent);
    }


    /**
     * 获取版本号
     */
    public static int getVersionCode(Context context) {
        int version_code = 0;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下的versionCode
            version_code = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version_code;
    }

    /**
     * 当前版本不再提醒
     */
    public static void setVersionNeverNotify(Context context) {

        int versionCode = getVersionCode(context);

        if (versionCode == 0)
            return;

        SharedPreferences preference = context.getSharedPreferences("XNotifaction", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();

        editor.putInt("neverNotifyVersion", versionCode);
        editor.commit();
    }

    /**
     * 是否需要显示提示对话框
     */
    public static boolean isNeedShowDialog(Context context) {

        SharedPreferences preference = context.getSharedPreferences("XNotifaction", Context.MODE_PRIVATE);
        int neverNotifyVersion = preference.getInt("neverNotifyVersion", 0);
        int versionCode = getVersionCode(context);

        boolean notificationEnabled = isNotificationEnabled(context);

        if (!notificationEnabled) {
            // 未开启通知
            if (versionCode > neverNotifyVersion)
                return true;
            else
                return false;
        } else
            return false;

    }
}
