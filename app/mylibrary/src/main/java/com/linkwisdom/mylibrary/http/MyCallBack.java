package com.linkwisdom.mylibrary.http;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonElement;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author ramon
 * date 2018/11/22
 */
public class MyCallBack implements Callback {

    Activity act;
    public final String error = "HTTP ERROR: 404";

    public MyCallBack(Activity act) {
        this.act = act;
    }

    @Override
    public void onFailure(Call call, final IOException e) {

        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onResp();
                Log.e("ramon", "请求异常： " + e.toString());
                Toast.makeText(act, "请求失败 ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onResponse(final Call call, Response response) throws IOException {

        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onResp();
            }
        });

        if (response.body() == null) {
            Log.e("ramon", "onResponse: " + "请求返回原始数据为空");
            return;
        }

        String res = response.body().string();
        String url = response.request().url().toString();
        Log.e("ramon", " pub onResponse: " + url + " " + res);

        if (res.contains("<html>")) {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    on404();
                }
            });
            return;
        }

//        final ResponseEntity entity = GsonUtil.parseJsonWithGson(res, ResponseEntity.class);
        final ResponseBean ogbean;
        ogbean = GsonUtil.parseJsonWithGson(res, ResponseBean.class);

        if (ogbean.code == 200) {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onSuccess(ogbean.data);
                }
            });

        } else {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onFailed(ogbean.message);
                }
            });

        }

    }

    public void onSuccess(JsonElement datastr) {
    }

    /**
     * 返回失败
     */
    public void onFailed(String messgae) {

    }

    /**
     * 返回网页，一般是404回调
     */
    public void on404() {

    }

    /**
     * 响应调用，比如进度条操作等
     */
    public void onResp() {
    }

}
