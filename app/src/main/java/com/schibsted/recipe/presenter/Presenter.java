package com.schibsted.recipe.presenter;

import com.schibsted.recipe.api.ApiResponse;

import rx.Observable;
import rx.Subscriber;

public abstract class Presenter {

    protected Observable observe(final Executor executor) {
        return Observable.create(new Observable.OnSubscribe<ApiResponse>() {
            @Override
            public void call(Subscriber<? super ApiResponse> subscriber) {
                subscriber.onNext(executor.go());
            }
        });
    }

    protected interface Executor {
        ApiResponse go();
    }
}