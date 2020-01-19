package dsc.mwahbak.network;

import java.util.ArrayList;
import java.util.Map;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetDataService {


    @POST("api/cats")
    Call<ApiResponse> get_cats();


     @POST("api/login")
    @FormUrlEncoded
    Call<ApiResponse> login(@FieldMap Map<String, String> qStringMap);


}
