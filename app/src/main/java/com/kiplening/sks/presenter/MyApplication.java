package com.kiplening.sks.presenter;

import android.app.Application;
import android.util.Log;

/**
 * Created by MOON on 2/28/2016.
 */
public class MyApplication extends Application {
    private static final MyApplication instance = new MyApplication();



    /*
    定义一些全局变量
     */
    /**
     * android应用程序真正入口
     * 此方法在所有activity，service，receiver组件之前调用
     */
    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("Create", "applicationg created");

    }

    /**
     * 此方法方便那些没有context对象的类中使用
     * @return MyApplication实例
     */
    public static MyApplication getApplicationInstance(){
        return instance;
    }
    /**
     *
     */


}

