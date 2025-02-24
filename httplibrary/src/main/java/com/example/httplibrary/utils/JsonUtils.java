package com.example.httplibrary.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonUtils {
    /**
     *
     * @param data
     * @param tClass
     * @param <T>
     * @return 返回一个实体类的集合类型
     */
    public static <T> List<T> jsonToClassList(JsonElement data, Class<T> tClass) {
        Gson gson = new Gson();
        JsonArray jsonArray = null;
        if (data.isJsonArray()) {
            jsonArray = data.getAsJsonArray();
        }
        List<T> lists = new ArrayList<>();
        T t = null;
        Iterator it = jsonArray.iterator();
        //遍历json数组
        while (it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
//JsonElement转换为JavaBean对象
            t = gson.fromJson(e, tClass);
            lists.add(t);
        }
        return lists;
    }

    /**
     *
     * @param data  json字符串
     * @param tClass 实体类Class类型
     * @param <T>
     * @return
     */
    public static <T> T jsonToClass(JsonElement data, Class<T> tClass){
        return new Gson().fromJson(data,tClass);
    }
    public static <T> T jsonToClass(String data, Class<T> tClass){
        return new Gson().fromJson(data,tClass);
    }
    //实体类转json字符串
    public static <T> String classToJson(T t){
        return new Gson().toJson(t);
    }
}

