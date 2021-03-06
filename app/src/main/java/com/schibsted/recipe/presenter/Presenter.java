package com.schibsted.recipe.presenter;

import com.schibsted.recipe.api.ApiResponse;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public abstract class Presenter<View> {
    private View mView;

    protected View getView() {
        return mView;
    }

    public Presenter(View view) {
        mView = view;
    }

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