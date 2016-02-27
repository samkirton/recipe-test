package com.schibsted.recipe;

import android.app.Application;

import com.schibsted.recipe.api.sync.SyncApiManager;

import retrofit.RestAdapter;

public class DefaultApplication extends Application {
    private static DefaultApplication sInstance;

    private SyncApiManager mSyncApiManager;

    private static final String ENDPOINT = "http://food2fork.com";

    public static DefaultApplication getInstance() {
        return sInstance;
    }

    public SyncApiManager getSyncApiManager() {
        return mSyncApiManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mSyncApiManager = new SyncApiManager(new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build()
        );
    }
}
