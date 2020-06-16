package com.linkwisdom.mylibrary.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramon on 2017/11/18.
 * <p>
 * json转实体工具类
 */

public class GsonUtil {

    static Gson gson;

    public GsonUtil() {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(String.class, new StringConverter());
        gson = gb.create();
    }

    /**
     * json字符串转实体类
     *
     * @param paramString
     * @param paramClass
     * @param <T>
     * @return
     */
    public static <T> T parseJsonWithGson(String paramString, Class<T> paramClass) {
        if (gson == null) {

            GsonBuilder gb = new GsonBuilder();
            gb.registerTypeAdapter(String.class, new StringConverter());
            gson = gb.create();

        }
        return gson.fromJson(paramString, paramClass);
    }

    /**
     * 自定义 JSON转list集合
     * 1 有时候 jsonToList 无故报错 就使用此方法
     * 2 返回数据结构并不统一，所以使用两种解析方式。
     *
     * @param array
     * @param paramClass
     * @param <T>
     * @return
     */
    public static <T> List<T> parseJsonArrayWithGson(String array, Class<T> paramClass) {

        if (gson == null) {
            GsonBuilder gb = new GsonBuilder();
            gb.registerTypeAdapter(String.class, new StringConverter());
            gson = gb.create();
        }

        List<T> list;

        try {
            JSONArray jsonArray = new JSONArray(array);
            list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(gson.fromJson(jsonArray.optString(i), paramClass));
            }
            return list;

        } catch (JSONException e) {

            try {
                JSONObject obj = new JSONObject(array);
                //返回集合格式解析
                JSONArray ja = obj.optJSONArray("datas");
                list = new ArrayList<>();
                for (int i = 0; i < ja.length(); i++) {
                    list.add(gson.fromJson(ja.optString(i), paramClass));
                }
                return list;
            } catch (JSONException e1) {
                Log.e("ramon", "parseJsonArrayWithGson: " + e1.toString());
                return null;
            }
        }
    }

}
