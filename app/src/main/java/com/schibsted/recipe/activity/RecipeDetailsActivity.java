package com.schibsted.recipe.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.schibsted.recipe.DefaultApplication;
import com.schibsted.recipe.R;
import com.schibsted.recipe.bean.Recipe;
import com.schibsted.recipe.presenter.RecipeDetailPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeDetailsActivity extends BaseActivity implements RecipeDetailsView {
    @Bind(R.id.activity_recipe_details_progressbar) ProgressBar mProgressBar;
    @Bind(R.id.activity_recipe_details_error) TextView mErrorMessageTextView;

    private RecipeDetailPresenter mRecipeDetailPresenter;

    public static final String RECIPE_ID = "RECIPE_ID";
    public static final String RECIPE_TITLE = "RECIPE_TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        setTitle(getRecipeTitle());
        showHomeUp();
        ButterKnife.bind(this);

        mRecipeDetailPresenter = new RecipeDetailPresenter(DefaultApplication.getInstance().getApiManager(),this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRecipeDetailPresenter.loadRecipe(getRecipeId());
    }

    private String getRecipeId() {
        return getIntent() != null &&
                getIntent().hasExtra(RECIPE_ID) ?
                getIntent().getStringExtra(RECIPE_ID) : null;
    }

    private String getRecipeTitle() {
        return getIntent() != null &&
                getIntent().hasExtra(RECIPE_TITLE) ?
                getIntent().getStringExtra(RECIPE_TITLE) : getString(R.string.activity_recipe_details_title_empty);
    }

    @Override
    public void loadingRecipe() {
        mProgressBar.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setVisibility(View.GONE);
    }

    @Override
    public void displayRecipe(Recipe recipe) {
        mProgressBar.setVisibility(View.GONE);
        mErrorMessageTextView.setVisibility(View.GONE);
    }

    @Override
    public void noRecipeFound() {
        mProgressBar.setVisibility(View.GONE);
        mErrorMessageTextView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setText(getString(R.string.activity_recipe_details_no_result));
    }

    @Override
    public void errorFetchingRecipe() {
        mProgressBar.setVisibility(View.GONE);
        mErrorMessageTextView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setText(getString(R.string.activity_recipe_details_error));
    }

    @Override
    protected boolean shouldAnimateOut() {
        return true;
    }
}