package com.projecthade.androidloginoauth.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;
import retrofit.converter.JacksonConverter;

/**
 * Created by avew on 10/5/15.
 */
public class RestClient {

    private static Api REST_CLIENT;

    private static final String TAG = RestClient.class.getSimpleName();


    static {
        setUpRestClient();
    }


    private RestClient() {

    }

    public static Api get() {

        return REST_CLIENT;
    }


    private static void setUpRestClient() {

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(100, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(100, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(100, TimeUnit.SECONDS);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

        //menambahkan auth basic
        AuthBasicRequestInterceptor requestInterceptor = new AuthBasicRequestInterceptor();

        Client client = new Client();
        client.setClient_id(Constant.CLIENT_ID);
        client.setClient_secret(Constant.CLIENT_SECRET);


        requestInterceptor.setClient(client);


        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setRequestInterceptor(requestInterceptor)
                .setEndpoint(Constant.URLSERVER)
                .setClient(new OkClient(okHttpClient))
                .setConverter(new JacksonConverter(mapper))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("event"));

        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(Api.class);

    }
}
