package com.linkwisdom.mylibrary.http;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by ramon on 2018/3/13.
 * http 请求基类
 */
public class BaseBiz extends Constant {

    public static final MediaType FORM_CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");//form表单默认格式
    public OkHttpClient http;

    public BaseBiz() {
        http = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    /**
     * GET请求方法
     *
     * @param url
     * @param paramCallback
     */
    public void get(String url, Callback paramCallback) {

        Request.Builder localBuilder = new Request.Builder().url(Constant.BASE_URL + url);
        localBuilder.method("GET", null);
        localBuilder
                .addHeader("X-Secret-Key", "")
                .addHeader("device", "android");
        Request localRequest = localBuilder.build();
        http.newCall(localRequest).enqueue(paramCallback);
    }

    /**
     * form post
     *
     * @param url
     * @param paramMap
     * @param paramCallback
     */
    public void post(String url, Map<String, String> paramMap, Callback paramCallback) {

        FormBody.Builder localBuilder = new FormBody.Builder();

        Iterator localIterator = paramMap.keySet().iterator();

        while (localIterator.hasNext()) {
            String str = (String) localIterator.next();
//            Log.e("ramon", "post: " + str + " " + paramMap.get(str));
            localBuilder.add(str, paramMap.get(str));
        }

        RequestBody body = localBuilder.build();

        Log.e("ramon", "post: " + body.contentType());

        Request request = new Request.Builder()
                .addHeader("X-Secret-Key", "")
                .addHeader("device", "android")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("charset", "UTF-8")
                .url(Constant.BASE_URL + url)
                .post(body)
                .build();

        Call call = http.newCall(request);
        call.enqueue(paramCallback);

    }

    /**
     * json post
     *
     * @param url
     * @param map
     * @param paramCallback
     */
    public void postJson(String url, Map<String, String> map, Callback paramCallback) {

        JSONObject jsonObj = new JSONObject();

        if (map.size() > 0) {

            for (int i = 0; i < map.size(); i++) {

                Iterator localIterator = map.keySet().iterator();

                while (localIterator.hasNext()) {
                    String str = (String) localIterator.next();
                    try {
                        jsonObj.put(str, map.get(str));
                    } catch (JSONException e) {
                        Log.e("ramon", e.toString());
                    }
                }
            }
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonObj.toString());

        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json; charset=utf8")
                .addHeader("Accept", "application/json")
                .addHeader("device", "android")
                .addHeader("os", "android")
                .addHeader("timestamp", String.valueOf(System.currentTimeMillis()))
                .url(Constant.BASE_URL + url)
                .post(body)
                .build();

        Call call = http.newCall(request);
        call.enqueue(paramCallback);
    }

    /**
     * @param url
     * @param map
     * @param token
     * @param paramCallback
     */
    public void postJsonWithToken(String url, Map<String, String> map, String token, Callback paramCallback) {

        JSONObject jsonObj = new JSONObject();

        if (map.size() > 0) {

            for (int i = 0; i < map.size(); i++) {

                Iterator localIterator = map.keySet().iterator();

                while (localIterator.hasNext()) {
                    String str = (String) localIterator.next();
                    try {
                        jsonObj.put(str, map.get(str));
                    } catch (JSONException e) {
                        Log.e("ramon", e.toString());
                    }
                }
            }
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonObj.toString());

        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json; charset=utf8")
                .addHeader("Accept", "application/json")
                .addHeader("device", "android")
                .addHeader("os", "android")
                .addHeader("authorization", token)
                .addHeader("timestamp", String.valueOf(System.currentTimeMillis()))
                .url(Constant.BASE_URL + url)
                .post(body)
                .build();

        Call call = http.newCall(request);
        call.enqueue(paramCallback);
    }

    /**
     * @param url
     * @param obj
     * @param paramCallback
     */
    public void postJson(String url, JSONObject obj, Callback paramCallback) {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        Log.e("cbh", "postJson: " + jsonObj.toString());
        RequestBody body = RequestBody.create(JSON, obj.toString());

        Request request = new Request.Builder()
                .addHeader("X-Secret-Key", "")
                .addHeader("device", "android")
                .url(Constant.BASE_URL + url)
                .post(body)
                .build();

        Call call = http.newCall(request);
        call.enqueue(paramCallback);
    }

    /**
     * @param url
     * @param obj
     * @param paramCallback
     */
    public void put(String url, JSONObject obj, Callback paramCallback) {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        Log.e("cbh", "postJson: " + jsonObj.toString());
        RequestBody body = RequestBody.create(JSON, obj.toString());

        Request request = new Request.Builder()
                .addHeader("X-Secret-Key", "")
                .addHeader("device", "android")
                .url(Constant.BASE_URL + url)
                .put(body)
                .build();

        Call call = http.newCall(request);
        call.enqueue(paramCallback);
    }

    /**
     * put2
     *
     * @param url
     * @param map
     * @param paramCallback
     */
    public void put(String url, Map<String, String> map, Callback paramCallback) {

        JSONObject jsonObj = new JSONObject();

        if (map.size() > 0) {

            for (int i = 0; i < map.size(); i++) {

                Iterator localIterator = map.keySet().iterator();

                while (localIterator.hasNext()) {
                    String str = (String) localIterator.next();
                    try {
                        jsonObj.put(str, map.get(str));
                    } catch (JSONException e) {
                        Log.e("ramon", e.toString());
                    }
                }
            }
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        Log.e("ramon", "postJson: " + jsonObj.toString());
        RequestBody body = RequestBody.create(JSON, jsonObj.toString());

        Request request = new Request.Builder()
                .addHeader("X-Secret-Key", "")
                .addHeader("device", "android")
                .url(Constant.BASE_URL + url)
                .put(body)
                .build();

        Call call = http.newCall(request);
        call.enqueue(paramCallback);
    }

    /**
     * delete 方法
     */
    public void delete(String url, Map<String, String> paramMap, Callback callback) {

        FormBody.Builder localBuilder = new FormBody.Builder();

        Iterator localIterator = paramMap.keySet().iterator();

        while (localIterator.hasNext()) {
            String str = (String) localIterator.next();
//            Log.e("ramon", "post: " + str + " " + paramMap.get(str));
            localBuilder.add(str, paramMap.get(str));
        }

        RequestBody body = localBuilder.build();
//        FormBody formBody = new FormBody.Builder().build();

        Request.Builder builder = new Request.Builder()
                .addHeader("X-Secret-Key", "")
                .addHeader("device", "android")
                .url(Constant.BASE_URL + url).delete(body);

        Request request = builder.build();

        Call call = http.newCall(request);
        call.enqueue(callback);

    }

    /**
     * delete 方法
     */
    public void delete(String url, Callback callback) {

        FormBody formBody = new FormBody.Builder().build();

        Request.Builder builder = new Request.Builder()
                .addHeader("X-Secret-Key", "")
                .addHeader("device", "android")
                .url(Constant.BASE_URL + url).delete(formBody);

        Request request = builder.build();

        Call call = http.newCall(request);
        call.enqueue(callback);

    }

    /**
     * 上传多媒体
     *
     * @param url
     * @param video
     * @param videopic
     * @param pictures
     * @param paramCallback
     */
    public void upload(String url, File video, File videopic, List<File> pictures, Callback paramCallback) {

        OkHttpClient client;

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .build();

        MultipartBody.Builder body = new MultipartBody.Builder();
        body.setType(MultipartBody.FORM);

        if (video != null) {
            //加入视频文件
            body.addFormDataPart("video", "video.mp4",
                    RequestBody.create(MediaType.parse("multipart/form-data"), video));
            //视频名字
//            body.addFormDataPart("videoname", video.getName());
            //饰品封面
            body.addFormDataPart("vp", "vp.jpg",
                    RequestBody.create(MediaType.parse("multipart/form-data"), videopic));

        }
        //加入 图片
        if (pictures != null && pictures.size() > 0) {
            for (int i = 0; i < pictures.size(); i++) {
                File file = pictures.get(i);
                if (file != null) {
                    body.addFormDataPart("goodpic", i + ".jpg",
                            RequestBody.create(MediaType.parse("multipart/form-data"), file));
                }
            }
        }

        RequestBody requestBody = body.build();
        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + UUID.randomUUID())
                .addHeader("X-Secret-Key", "")
                .addHeader("device", "android")
                .url(Constant.BASE_URL + url)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(paramCallback);
    }

    /**
     * 重载上传单张图片
     *
     * @param pic           上传图片文件
     * @param paramCallback 回调
     */
    public void upload(File pic, String filename, Callback paramCallback) {

        OkHttpClient client;
        String url = "";
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .build();

        MultipartBody.Builder body = new MultipartBody.Builder();
        body.setType(MultipartBody.FORM);

        //加入 图片
        body.addFormDataPart("file", filename,
                RequestBody.create(MediaType.parse("multipart/form-data"), pic));

        RequestBody requestBody = body.build();
        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + UUID.randomUUID())
                .addHeader("X-Secret-Key", "")
                .addHeader("device", "android")
                .url(Constant.BASE_URL + url)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(paramCallback);
    }

    /**
     * 上传多张图片
     *
     * @param url
     * @param pictures
     * @param paramCallback
     */
    public void upload(String url, List<File> pictures, Callback paramCallback) {

        OkHttpClient client;

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .build();

        MultipartBody.Builder body = new MultipartBody.Builder();
        body.setType(MultipartBody.FORM);

        //加入 图片
        if (pictures != null && pictures.size() > 0) {
            for (int i = 0; i < pictures.size(); i++) {
                File file = pictures.get(i);
                if (file != null) {
                    body.addFormDataPart("goodpic", i + ".jpg",
                            RequestBody.create(MediaType.parse("multipart/form-data"), file));
                }
            }
        }

        RequestBody requestBody = body.build();
        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + UUID.randomUUID())
                .addHeader("X-Secret-Key", "")
                .addHeader("device", "android")
                .url(Constant.BASE_URL + url)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(paramCallback);
    }

}
