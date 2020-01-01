package dsc.mwahbak.di.main;

import android.app.Application;

import androidx.recyclerview.widget.LinearLayoutManager;


import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

/*    @MainScope
    @Provides
    static PostsRecyclerAdapter provideAdapter(){
        return new PostsRecyclerAdapter();
    }

    @MainScope
    @Provides
    static LinearLayoutManager provideLinearLayoutManager (Application application){
        return new LinearLayoutManager(application);
    }

    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }*/
}
