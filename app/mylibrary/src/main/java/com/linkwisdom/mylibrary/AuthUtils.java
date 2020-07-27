package com.linkwisdom.mylibrary;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.appcompat.app.AlertDialog;

import com.linkwisdom.mylibrary.http.Constant;

import java.util.List;

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
    //    public static final String DOWNLAOD_URL = "https://www.5wave.io/Home#page8";
    public static final String DOWNLAOD_URL = "https://d.5wave.io/index.html";
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
        if (checkApp()) {
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
        } else {
            download();
        }
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
        if (checkApp()) {
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
    }

    /**
     * 提现跳转请求
     *
     * @param token
     * @param address
     * @param callbackUrl
     */
    public void withdrawApply(String token, String address, String callbackUrl) {
        if (checkApp()) {
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
    }

    /**
     * 验证是否有APP
     *
     * @return
     */
    public boolean checkApp() {

        PackageManager packageManager = MyApplication.getInstance().getPackageManager();

        List<PackageInfo> pInfo = packageManager.getInstalledPackages(0);

        boolean isHave = false;
        String packageName = "";

        if (Constant.BASE_URL.equals("https://www.5wave.io")) {
            packageName = "com.lianzhihui.five";
        } else {
            packageName = "com.lianzhihui.five2";
        }

        for (int i = 0; i < pInfo.size(); i++) {

            String pn = pInfo.get(i).packageName;

            if (pn.equals(packageName)) {
                isHave = true;
            }
        }
        if (!isHave) {
            download();
        }
        return isHave;
    }

    /**
     * 下载app
     */
    public void download() {

        // 创建构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // 设置参数
        builder.setTitle("未安装《帝五波》")
                .setMessage("是否跳转下载？")
                .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(DOWNLAOD_URL);
                        intent.setData(content_url);
                        activity.startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        builder.create().show();

    }

    /**
     * 检测，并下载
     */
    public void is5Wave() {
        if (!checkApp()) {
            download();
        }
    }

}
