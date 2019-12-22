package dsc.mwahbak;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private BottomNavigationView bottomNavigationView;
    private NavOptions navOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        init();
    }

    private void init() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.main, true)
                .build();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.home:{

                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(
                        R.id.homeScreen,
                        null,
                        navOptions
                );

                break;
            }

            case R.id.search:{

                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(
                        R.id.searchScreen,
                        null,
                        navOptions
                );

                break;
            }


            case R.id.add_new:{

                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(
                        R.id.addNewScreen,
                        null,
                        navOptions
                );

                break;
            }


            case R.id.notifications:{

                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(
                        R.id.notificationsScreen,
                        null,
                        navOptions
                );

                break;
            }


            case R.id.profile:{

                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(
                        R.id.profileScreen,
                        null,
                        navOptions
                );

                break;
            }

        }

        return true;
    }
}
