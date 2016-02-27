package com.schibsted.recipe.activity;

import android.app.Activity;

import com.schibsted.recipe.api.ApiResponse;

import rx.Subscriber;
import rx.functions.Action1;

public class BaseActivity extends Activity {

    protected Subscriber createSubscriber(final Action1<? super ApiResponse> action1, final Action1<? super Throwable> error) {

        return new Subscriber<ApiResponse>() {

            @Override
            public final void onCompleted() {
                // do nothing
            }

            @Override
            public final void onError(Throwable e) {
                error.call(e);
            }

            @Override
            public final void onNext(ApiResponse args) {
                action1.call(args);
            }
        };
    }
}
