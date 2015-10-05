package com.projecthade.androidloginoauth.io;

import android.util.Log;

import retrofit.RequestInterceptor;

/**
 * Created by avew on 10/5/15.
 */
public class AuthBasicRequestInterceptor implements RequestInterceptor {

    private static final String TAG = AuthBasicRequestInterceptor.class.getCanonicalName();

    private Client client;

    @Override
    public void intercept(RequestFacade requestFacade) {

        if (client != null) {
            final String authorizationValue = encodeCredentialsForBasicAuthorization();
            requestFacade.addHeader("Authorization", authorizationValue);
        }
    }

    private String encodeCredentialsForBasicAuthorization() {
        final String userAndPassword = client.getClient_id() + ":" + client.getClient_secret();
        Log.d(TAG, "encodeCredentialsForBasicAuthorization() called with: " + android.util.Base64.encodeToString(userAndPassword.getBytes(), android.util.Base64.URL_SAFE));
        return "Basic " + android.util.Base64.encodeToString(userAndPassword.getBytes(), android.util.Base64.URL_SAFE);
    }


    public void setClient(Client client) {
        this.client = client;
    }
}
