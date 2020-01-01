package dsc.mwahbak.di.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dsc.mwahbak.ui.main.home.HomeFragment;
import dsc.mwahbak.ui.main.notification.NotificationsFragment;
import dsc.mwahbak.ui.main.profile.ProfileFragment;
import dsc.mwahbak.ui.main.search.SearchFragment;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract HomeFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();

    @ContributesAndroidInjector
    abstract NotificationsFragment contributeNotificationsFragment();

    @ContributesAndroidInjector
    abstract ProfileFragment ProfileFragment();
}
