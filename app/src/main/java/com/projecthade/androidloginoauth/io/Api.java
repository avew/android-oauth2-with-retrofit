package com.projecthade.androidloginoauth.io;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by avew on 10/5/15.
 */
public interface Api {

    @FormUrlEncoded
    @POST("/oauth/token")
    void oauthToken(@Field("username") String username,
                    @Field("password") String password,
                    @Field("grant_type") String grant_type,
                    @Field("scope") String scope,
                    @Field("client_secret") String client_secret,
                    @Field("client_id") String client_id,
                    Callback<ResponseLogin> callback);
}
