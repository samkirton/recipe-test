package com.schibsted.recipe.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.schibsted.recipe.DefaultApplication;
import com.schibsted.recipe.R;
import com.schibsted.recipe.bean.Recipe;
import com.schibsted.recipe.presenter.RecipeDetailPresenter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeDetailsActivity extends BaseActivity implements RecipeDetailsView {
    @Bind(R.id.activity_recipe_details_progressbar) ProgressBar mProgressBar;
    @Bind(R.id.activity_recipe_details_error) TextView mErrorMessageTextView;
    @Bind(R.id.activity_recipe_details_content) ViewGroup mContentViewGroup;
    @Bind(R.id.activity_recipe_details_toolbar) Toolbar mToolbar;
    @Bind(R.id.activity_recipe_details_collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.activity_recipe_details_image) ImageView mRecipeImageView;

    private RecipeDetailPresenter mRecipeDetailPresenter;

    public static final String RECIPE_ID = "RECIPE_ID";
    public static final String RECIPE_TITLE = "RECIPE_TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mCollapsingToolbarLayout.setTitle(getRecipeTitle());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.primary_dark);
        int primary = getResources().getColor(R.color.primary);
        mCollapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        mCollapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        supportStartPostponedEnterTransition();
    }

    @Override
    public void loadingRecipe() {
        mProgressBar.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setVisibility(View.GONE);
        mContentViewGroup.setVisibility(View.GONE);
    }

    @Override
    public void displayRecipe(Recipe recipe) {
        mProgressBar.setVisibility(View.GONE);
        mErrorMessageTextView.setVisibility(View.GONE);
        mContentViewGroup.setVisibility(View.VISIBLE);

        Picasso.with(this).load(recipe.getImage_url()).into(mRecipeImageView, mPicassoCallback);
    }

    @Override
    public void noRecipeFound() {
        mProgressBar.setVisibility(View.GONE);
        mContentViewGroup.setVisibility(View.GONE);
        mErrorMessageTextView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setText(getString(R.string.activity_recipe_details_no_result));
    }

    @Override
    public void errorFetchingRecipe() {
        mProgressBar.setVisibility(View.GONE);
        mContentViewGroup.setVisibility(View.GONE);
        mErrorMessageTextView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setText(getString(R.string.activity_recipe_details_error));
    }

    @Override
    protected boolean shouldAnimateOut() {
        return true;
    }

    private Callback mPicassoCallback = new Callback() {

        @Override
        public void onSuccess() {
            Bitmap bitmap = ((BitmapDrawable) mRecipeImageView.getDrawable()).getBitmap();
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                public void onGenerated(Palette palette) {
                    applyPalette(palette);
                }
            });
        }

        @Override
        public void onError() { }
    };
}