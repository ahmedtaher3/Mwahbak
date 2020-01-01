package dsc.mwahbak.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import dsc.mwahbak.R;
import dsc.mwahbak.base.BaseActivity;
import dsc.mwahbak.databinding.ActivityMainBinding;
import dsc.mwahbak.ui.main.addnew.AddNewTalentActivity;
import dsc.mwahbak.ui.main.home.HomeFragment;
import dsc.mwahbak.ui.main.notification.NotificationsFragment;
import dsc.mwahbak.ui.main.profile.ProfileFragment;
import dsc.mwahbak.ui.main.search.SearchFragment;


public class MainActivity extends BaseActivity<ActivityMainBinding> implements BottomNavigationView.OnNavigationItemSelectedListener
        , HomeFragment.OnFragmentInteractionListener
        , SearchFragment.OnFragmentInteractionListener
        , NotificationsFragment.OnFragmentInteractionListener
        , ProfileFragment.OnFragmentInteractionListener {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding activityMainBinding;

    final Fragment homeFragment = new HomeFragment();
    final Fragment searchFragment = new SearchFragment();
    final Fragment notificationsFragment = new NotificationsFragment();
    final Fragment profileFragment = new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = homeFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = getViewDataBinding();

        setSupportActionBar(activityMainBinding.toolbar);
        getSupportActionBar().setTitle(getString(R.string.home));

        activityMainBinding.navigation.setOnNavigationItemSelectedListener(this);
        activityMainBinding.navigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);

        fm.beginTransaction().add(R.id.Container, profileFragment, "4").hide(profileFragment).commit();
        fm.beginTransaction().add(R.id.Container, notificationsFragment, "3").hide(notificationsFragment).commit();
        fm.beginTransaction().add(R.id.Container, searchFragment, "2").hide(searchFragment).commit();
        fm.beginTransaction().add(R.id.Container, homeFragment, "1").commit();

    }

    @Override
    protected void init() {

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.home:{
                fm.beginTransaction().hide(active).show(homeFragment).commit();
                active = homeFragment;
                getSupportActionBar().setTitle(getString(R.string.home));
                return true;
            }

            case R.id.search:{
                fm.beginTransaction().hide(active).show(searchFragment).commit();
                active = searchFragment;
                getSupportActionBar().setTitle(getString(R.string.search));
                return true;
            }


            case R.id.add_new:{
               startActivity(new Intent(MainActivity.this , AddNewTalentActivity.class));
                return true;
            }


            case R.id.notifications:{
                fm.beginTransaction().hide(active).show(notificationsFragment).commit();
                active = notificationsFragment;
                getSupportActionBar().setTitle(getString(R.string.notifications));
                return true;
            }


            case R.id.profile:{
                fm.beginTransaction().hide(active).show(profileFragment).commit();
                active = profileFragment;
                getSupportActionBar().setTitle(getString(R.string.profile));
                return true;
            }

        }

        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (homeFragment.equals(active)) {
            activityMainBinding.navigation.setSelectedItemId(R.id.home);
            return;
        }
        else if (searchFragment.equals(active)) {
            activityMainBinding.navigation.setSelectedItemId(R.id.search);
            return;
        }
        else if (notificationsFragment.equals(active)) {
            activityMainBinding.navigation.setSelectedItemId(R.id.notifications);
            return;
        }

        else if (profileFragment.equals(active)) {
            activityMainBinding.navigation.setSelectedItemId(R.id.profile);
            return;
        }


    }
}
