package com.linkwisdom.mylibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.linkwisdom.mylibrary.http.Constant;

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
    public static final int RECHARGE_APPLY = 905;
    public static final int WITHDRAW_APPLY = 906;
    private Activity activity;

    public AuthUtils(Activity activity) {
        this.activity = activity;
    }

    public AuthUtils(Activity activity, int server) {
        this.activity = activity;
        setServer(server);
    }

    /**
     * 设置服务器
     *
     * @param server
     */
    public void setServer(int server) {
        Constant.setService(server);
    }

    /**
     * 发起 授权申请
     *
     * @param appName
     * @param key
     * @param version
     */
    public void authApply(String appName, String key, String version) {

        //详细设置跳转
        Intent sendIntent = new Intent();
        sendIntent.setAction("com.lianzhihui.five.auth");
//                type = "text/plain"
        String type = "text/plain";
        sendIntent.setType(type);
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendIntent.putExtra("appName", appName);
        sendIntent.putExtra("key", key);
        sendIntent.putExtra("version", version);
        activity.startActivityForResult(sendIntent, AUTH_APPLY);
    }

    /**
     * 充值跳转请求
     *
     * @param token
     * @param point
     * @param chargeText
     * @param callbackUrl
     */
    public void rechargeApply(String token, int point, String chargeText, String callbackUrl) {

        //详细设置跳转
        Intent sendIntent = new Intent();
        sendIntent.setAction("com.lianzhihui.five.recharge");
        String type = "text/plain";
        sendIntent.setType(type);
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendIntent.putExtra("token", token);
        sendIntent.putExtra("point", point);
        sendIntent.putExtra("description", chargeText);
        sendIntent.putExtra("callbackUrl", callbackUrl);
        activity.startActivityForResult(sendIntent, RECHARGE_APPLY);
    }

    /**
     * 提现跳转请求
     *
     * @param token
     * @param address
     * @param callbackUrl
     */
    public void withdrawApply(String token, String address, String callbackUrl) {

        //详细设置跳转
        Intent sendIntent = new Intent();
        sendIntent.setAction("com.lianzhihui.five.withdraw");
        String type = "text/plain";
        sendIntent.setType(type);
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendIntent.putExtra("token", token);
        sendIntent.putExtra("address", address);
        sendIntent.putExtra("callbackUrl", callbackUrl);
        activity.startActivityForResult(sendIntent, WITHDRAW_APPLY);
    }

    public void checkToken() {

    }

}
