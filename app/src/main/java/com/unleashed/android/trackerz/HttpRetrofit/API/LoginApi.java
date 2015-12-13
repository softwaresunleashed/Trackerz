package com.unleashed.android.trackerz.HttpRetrofit.API;

import com.unleashed.android.trackerz.HttpRetrofit.Model.LoginModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by sudhanshu on 13/12/15.
 */
public interface LoginApi {

    @GET("/users/{user}")
    //here is the other url part.best way is to start using /


    public void getFeed(@Path("user") String user, Callback<LoginModel> response);
    //string user is for passing values from edittext for eg: user=basil2style,google
    //response is the response from the server which is now in the POJO

}
