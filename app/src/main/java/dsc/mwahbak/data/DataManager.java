package dsc.mwahbak.data;

/**
 * Created by A.taher on 10/15/2018.
 */

public class DataManager {


    SharedPrefsHelper mSharedPrefsHelper;

    public DataManager(SharedPrefsHelper sharedPrefsHelper) {
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void clear() {
        mSharedPrefsHelper.clear();
    }


    public void saveUser(String user) {
        mSharedPrefsHelper.putUser(user);
    }
    public String getUser() {
        return mSharedPrefsHelper.getUser();
    }


    public void saveLoggingMode(boolean logged) {
        mSharedPrefsHelper.putLoggingMode(logged);
    }
    public boolean getLoggingMode() {
        return mSharedPrefsHelper.getLoggingMode();
    }
}


