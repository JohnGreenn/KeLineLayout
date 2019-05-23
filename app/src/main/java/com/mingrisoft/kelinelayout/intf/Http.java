package com.mingrisoft.kelinelayout.intf;

import com.mingrisoft.kelinelayout.mod.GetSMS;
import com.mingrisoft.kelinelayout.mod.LoginBean;
import com.mingrisoft.kelinelayout.mod.User;
import com.mingrisoft.kelinelayout.mod.checkCode;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;




public interface Http {


    @GET("kelai/login")
    Call<User> getUserInfo();


//    @Headers({
//            "CLIENT:android",
//            "APPID:Android-1488435881",
//            "NONCE:319128",
//            "CURTIME:20190514100210",
//            "OPENKEY:9d6f209580dfc9e38bebfec9f4bd39d7"
////            "USERID:c0c8fcFd891c3a1e8ebc2faf32db87515"
//    })

    @GET("kelai/login/")
    Call<LoginBean> userLogin(@Query("UNAME") String UNAME, @Query("PWORD") String PWORD);

//    @POST("kelai/login/")
//    Call<BaseResult> login(@Body UserParam param);

    @FormUrlEncoded  //用于修饰Field注解
    @POST("kelai/sendSms/")
    Call<GetSMS> getSms(@Field("MOBILE") String MOBILE);

    @FormUrlEncoded
    @POST("kelai/checkSms/")
    Call<checkCode> checkSms(@Field("MOBILE") String MOBILE, @Field("CODE") String CODE);



}




