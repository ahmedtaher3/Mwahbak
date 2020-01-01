package dsc.mwahbak.di;



import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dsc.mwahbak.di.addtalent.AddTalentFragmentBuildersModule;
import dsc.mwahbak.di.addtalent.AddTalentModule;
import dsc.mwahbak.di.addtalent.AddTalentScope;
import dsc.mwahbak.di.addtalent.AddTalentViewModelsModule;
import dsc.mwahbak.di.main.MainFragmentBuildersModule;
import dsc.mwahbak.di.main.MainModule;
import dsc.mwahbak.di.main.MainScope;
import dsc.mwahbak.di.main.MainViewModelsModule;
import dsc.mwahbak.ui.main.MainActivity;
import dsc.mwahbak.ui.main.addnew.AddNewTalentActivity;

@Module
public abstract class ActivityBuildersModule {

    @AddTalentScope
    @ContributesAndroidInjector(
            modules = {AddTalentFragmentBuildersModule.class, AddTalentModule.class, AddTalentViewModelsModule.class})
    abstract AddNewTalentActivity contributeAddNewTalentActivity();


    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelsModule.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();

}
