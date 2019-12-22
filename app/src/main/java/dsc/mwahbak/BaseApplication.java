package dsc.mwahbak;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import dsc.mwahbak.di.DaggerAppComponent;

public class BaseApplication extends DaggerApplication {


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
//        return null;
    }
}






