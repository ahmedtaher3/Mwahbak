package dsc.mwahbak.ui.main.addnew;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import dsc.mwahbak.R;
import dsc.mwahbak.base.BaseActivity;
import dsc.mwahbak.databinding.ActivityAddNewTalentBinding;
import dsc.mwahbak.ui.main.addnew.choosetalent.ChooseTalentTypeFragment;

public class AddNewTalentActivity extends BaseActivity<ActivityAddNewTalentBinding> {
    private static final String TAG = "AddNewTalentActivity";

    private ActivityAddNewTalentBinding addNewTalentBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_new_talent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addNewTalentBinding = getViewDataBinding();

        setSupportActionBar(addNewTalentBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.add_new_talent));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_36dp);

        replaceFragmentWithAnimation(new ChooseTalentTypeFragment(), ChooseTalentTypeFragment.TAG);
    }

    @Override
    protected void init() {
    }

    public void replaceFragmentWithAnimation(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Container, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
