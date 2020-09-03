package com.example.myshopping.app;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.example.httplibrary.HttpConstant;
import com.example.httplibrary.HttpGlobalConfig;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Interceptor;

public class ShowApplication extends Application {

    public static ShowApplication showApplication;

    public static ShowApplication getShowApplication(){
        return showApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showApplication = this;
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        ArrayList<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new CookieInterceptor());
        HttpGlobalConfig.getInsance()
                .setBaseUrl("http://169.254.207.29:8080/kotlin/")
                .setTimeout(HttpConstant.TIME_OUT)
                .setTimeUnit(HttpConstant.TIME_UNIT)
                .setInterceptors(interceptors)
                .setShowLog(true)
                .initReady(this);
        //判断LeakCanary是否已经运行在手机上
        //其他app已经运行了LeakCanary则不需要重新install
        if (LeakCanary.isInAnalyzerProcess(this)) {

            return;
        }
        //注册LeakCanary
        LeakCanary.install(this);

        CrashReport.initCrashReport(getApplicationContext(), "5a8d5b3af7", false);

    }
}
