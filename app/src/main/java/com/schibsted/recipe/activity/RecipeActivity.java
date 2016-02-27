package com.schibsted.recipe.activity;

import android.os.Bundle;
import android.util.Log;

import com.schibsted.recipe.R;
import com.schibsted.recipe.api.ApiResponse;
import com.schibsted.recipe.bean.Recipes;
import com.schibsted.recipe.presenter.RecipePresenter;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RecipeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        new RecipePresenter().getRecipe()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createSubscriber(mGetRecipeAction, mGetRecipeError));
    }

    private Action1 mGetRecipeAction = new Action1<ApiResponse<Recipes>>() {

        @Override
        public void call(ApiResponse<Recipes> response) {
            System.out.print("");
        }
    };

    private Action1 mGetRecipeError = new Action1<Throwable>() {
        @Override
        public void call(Throwable e) {

        }
    };
}
