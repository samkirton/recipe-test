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

public class RecipeActivity extends BaseActivity implements RecipeView {
    private RecipePresenter mRecipePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        mRecipePresenter = new RecipePresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRecipePresenter.loadRecipes();
    }

    @Override
    public void getRecipes(Recipes recipes) {
        System.out.print("");
        recipes.getCount();
    }

    @Override
    public void errorFetchingRecipes() {
        System.out.print("");
    }
}
