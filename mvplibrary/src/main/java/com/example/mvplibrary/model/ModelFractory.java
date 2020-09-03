package com.example.mvplibrary.model;

import java.util.HashMap;


public class ModelFractory {

    //存储modle的类的集合
    private static HashMap<String,BaseModel> mMap=new HashMap<>();

    public static <M extends BaseModel> M creatModle(Class<M> mClass){
        if(mMap.get(mClass)!=null){
            return (M) mMap.get(mClass);
        }
        M mModle=null;
        try {
            //通过反射创建对象,你的子类的modle必须要有无参构造方法。
            mModle = mClass.newInstance();
            if(mModle!=null){
                mMap.put(mClass.getName(),mModle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mModle;
    }
}
