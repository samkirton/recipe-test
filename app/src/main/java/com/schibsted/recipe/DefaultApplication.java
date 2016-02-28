package com.schibsted.recipe;

import android.app.Application;

import com.schibsted.recipe.api.sync.ApiManager;
import com.schibsted.recipe.api.sync.SyncApiManager;

import retrofit.RestAdapter;

public class DefaultApplication extends Application {
    private static DefaultApplication sInstance;

    private ApiManager mApiManager;
    private String mApiKey;

    private static final String ENDPOINT = "http://food2fork.com";

    public static DefaultApplication getInstance() {
        return sInstance;
    }

    public ApiManager getApiManager() {
        return mApiManager;
    }

    public String getApiKey() {
        return mApiKey;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mApiManager = new SyncApiManager(new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build()
        );

        mApiKey = getString(R.string.api_key);
    }
}