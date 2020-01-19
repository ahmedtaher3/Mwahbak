package dsc.mwahbak.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import dsc.mwahbak.R;
import dsc.mwahbak.base.BaseActivity;
import dsc.mwahbak.base.BaseApplication;
import dsc.mwahbak.data.DataManager;
import dsc.mwahbak.databinding.ActivityLoginBinding;
import dsc.mwahbak.network.ApiResponse;
import dsc.mwahbak.network.GetDataService;
import dsc.mwahbak.network.RetrofitClientInstance;
import dsc.mwahbak.ui.main.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    private static final String TAG = "LoginActivity";
    ActivityLoginBinding activityLoginBinding;
    GetDataService dataService;
    DataManager dataManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = getViewDataBinding();
        dataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        dataManager = ((BaseApplication) getApplication()).getDataManager();


        EditText input_password = (EditText) findViewById(R.id.input_password);
        EditText input_email = (EditText) findViewById(R.id.input_email);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(input_email.getText().toString(), input_password.getText().toString());
            }
        });

    }

    @Override
    protected void init() {

    }

    public void login(String email, String pass) {
        Log.d(TAG, "login: ");
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", pass);
        Call<ApiResponse> data = dataService.login(map);
        data.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.w("gson => ", new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));
                Log.d(TAG, "onResponse: ");

                if (response.body().getStatus()) {



                    Gson gson = new Gson();
                    String json = gson.toJson(response.body().getUser());
                    dataManager.saveUser(json);
                    dataManager.saveLoggingMode(true);
                    startActivity(new Intent(LoginActivity.this , MainActivity.class));
                    finish();


                } else {
                    Toast.makeText(LoginActivity.this, "email or password wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }
}
