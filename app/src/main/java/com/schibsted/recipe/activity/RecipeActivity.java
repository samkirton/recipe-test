package com.schibsted.recipe.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.schibsted.recipe.R;
import com.schibsted.recipe.adapter.RecipeAdapter;
import com.schibsted.recipe.api.ApiResponse;
import com.schibsted.recipe.bean.Recipe;
import com.schibsted.recipe.bean.Recipes;
import com.schibsted.recipe.presenter.RecipePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RecipeActivity extends BaseActivity implements RecipeView {
    @Bind(R.id.activity_recipe_recyclerview) RecyclerView mRecyclerView;

    private RecipePresenter mRecipePresenter;
    private RecipeAdapter mRecipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);

        // TODO: should be injected
        mRecipePresenter = new RecipePresenter(this);
        // TODO: should be injected???
        mRecipeAdapter = new RecipeAdapter();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecipeAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRecipePresenter.loadRecipes();
    }

    @Override
    public void getRecipes(Recipe[] recipes) {
        mRecipeAdapter.setRecipes(recipes);
    }

    @Override
    public void noRecipesFound() {

    }

    @Override
    public void errorFetchingRecipes() {
        System.out.print("");
    }
}
