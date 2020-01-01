package dsc.mwahbak.di.addtalent;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dsc.mwahbak.ui.main.addnew.choosemedia.ChooseMediaTypeFragment;
import dsc.mwahbak.ui.main.addnew.choosetalent.ChooseTalentTypeFragment;
import dsc.mwahbak.ui.main.addnew.uploadtalent.UploadTalentFragment;

@Module
public abstract class AddTalentFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ChooseTalentTypeFragment contributeChooseTalentTypeFragment();

    @ContributesAndroidInjector
    abstract ChooseMediaTypeFragment contributeChooseMediaTypeFragment();

    @ContributesAndroidInjector
    abstract UploadTalentFragment contributeUploadTalentFragment();


}
