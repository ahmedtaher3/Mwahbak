package dsc.mwahbak.base;


import android.content.Context;

import androidx.multidex.MultiDex;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import dsc.mwahbak.data.DataManager;
import dsc.mwahbak.data.SharedPrefsHelper;
import dsc.mwahbak.di.DaggerAppComponent;

public class BaseApplication extends DaggerApplication {


    DataManager dataManager;
    public DataManager getDataManager() {
        return dataManager;
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(getApplicationContext());
        dataManager = new DataManager(sharedPrefsHelper);



    }
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
//        return null;
    }
}






