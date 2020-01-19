/*
 *    Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package dsc.mwahbak.data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by gaura on 22-08-2017.
 */

public class SharedPrefsHelper {

    public static final String MY_PREFS = "MY_PREFS";
    public static final String NAME = "NAME";
    public static final String USER = "USER";
    public static final String LOGGED_IN = "LOGGED_IN";



    SharedPreferences mSharedPreferences;

    public SharedPrefsHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
    }

    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }



    public void putUser(String name) {
        mSharedPreferences.edit().putString(USER, name).apply();
    }
    public String getUser() {
        return mSharedPreferences.getString(USER, null);
    }

    public void putLoggingMode(boolean logged) {
        mSharedPreferences.edit().putBoolean(LOGGED_IN, logged).apply();
    }
    public boolean getLoggingMode() {
        return mSharedPreferences.getBoolean(LOGGED_IN, false);
    }




}
