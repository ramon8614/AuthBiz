package com.linkwisdom.mylibrary;

import android.app.Application;

/**
 * @ProjectName: NBN
 * @ClassName: MyApplication
 * @Description:
 * @Author: RAMON
 * @CreateDate: 2020/7/24 18:55
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version:
 */
public class MyApplication extends Application {

    static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
