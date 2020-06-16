package com.linkwisdom.mylibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * @ProjectName: NBN
 * @ClassName: AuthUtils
 * @Description: 授权工具类
 * @Author: RAMON
 * @CreateDate: 2020/6/15 11:31
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version:
 */
public class AuthUtils {

    public static final int AUTH_APPLY = 901;

    public void authApply(Activity activity, String appName) {

        //详细设置跳转
        Intent sendIntent = new Intent();
        sendIntent.setAction("com.lianzhihui.five.auth");
//                type = "text/plain"
        String type = "image/*";
        sendIntent.setType(type);
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendIntent.putExtra("appName", appName);
        activity.startActivityForResult(sendIntent, AUTH_APPLY);
    }

}
