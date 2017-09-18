package me.aaron.top250;

import android.app.Application;
import android.content.Context;

/**
 * Created by aaron on 17-9-18.
 */

public class MyApplication extends Application {

    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getContext(){
        return application.getApplicationContext();
    }

    public static MyApplication getApplication(){
        return application;
    }
}
