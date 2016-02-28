package com.schibsted.recipe.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.schibsted.recipe.DefaultApplication;
import com.schibsted.recipe.R;
import com.schibsted.recipe.adapter.IngredientAdapter;
import com.schibsted.recipe.adapter.holder.FooterHolder;
import com.schibsted.recipe.bean.Recipe;
import com.schibsted.recipe.presenter.RecipeDetailPresenter;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeDetailsActivity extends BaseActivity implements RecipeDetailsView {
    @Bind(R.id.activity_recipe_details_progressbar) ProgressBar mProgressBar;
    @Bind(R.id.activity_recipe_details_error) TextView mErrorMessageTextView;
    @Bind(R.id.activity_recipe_details_toolbar) Toolbar mToolbar;
    @Bind(R.id.activity_recipe_details_collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.activity_recipe_details_image) ImageView mRecipeImageView;
    @Bind(R.id.activity_recipe_details_ingredients_recyclerView) RecyclerView mIngredientsRecyclerView;

    private IngredientAdapter mIngredientAdapter;
    private RecipeDetailPresenter mRecipeDetailPresenter;
    private boolean mShouldAnimateOut;

    public static final String RECIPE_ID = "RECIPE_ID";
    public static final String RECIPE_TITLE = "RECIPE_TITLE";
    private static final String EXTRA_CUSTOM_TABS_TOOLBAR_COLOR = "android.support.customtabs.extra.TOOLBAR_COLOR";
    public static final String EXTRA_CUSTOM_TABS_EXIT_ANIMATION_BUNDLE = "android.support.customtabs.extra.EXIT_ANIMATION_BUNDLE";

    @Override
    protected void onResume() {
        super.onResume();
        mShouldAnimateOut = true;
    }

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

        mIngredientAdapter = new IngredientAdapter(mOnFooterNavigationSelected);
        mIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mIngredientsRecyclerView.setAdapter(mIngredientAdapter);

        mCollapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.primary_dark));
        mCollapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.primary));

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

    private void navigateToWebLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        Bundle extras = new Bundle();
        intent.putExtras(extras);
        intent.putExtra(EXTRA_CUSTOM_TABS_TOOLBAR_COLOR, getResources().getColor(R.color.primary));

        mShouldAnimateOut = false; // do not trigger the exit animation when a web browser is opening
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Bundle finishAnimBundle = ActivityOptions.makeCustomAnimation(this, R.anim.activity_open_scale, R.anim.activity_close_translate).toBundle();
            Bundle startAnimBundle = ActivityOptions.makeCustomAnimation(this, R.anim.activity_open_translate, R.anim.activity_close_scale).toBundle();

            intent.putExtra(EXTRA_CUSTOM_TABS_EXIT_ANIMATION_BUNDLE, finishAnimBundle);
            startActivity(intent, startAnimBundle);
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void loadingRecipe() {
        mProgressBar.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setVisibility(View.GONE);
        mIngredientsRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void displayRecipe(Recipe recipe) {
        mProgressBar.setVisibility(View.GONE);
        mErrorMessageTextView.setVisibility(View.GONE);
        mIngredientsRecyclerView.setVisibility(View.VISIBLE);

        mIngredientAdapter.setIngredients(recipe);
        Picasso.with(this).load(recipe.getImage_url()).into(mRecipeImageView);
    }

    @Override
    public void noRecipeFound() {
        mProgressBar.setVisibility(View.GONE);
        mIngredientsRecyclerView.setVisibility(View.GONE);
        mErrorMessageTextView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setText(getString(R.string.activity_recipe_details_no_result));
    }

    @Override
    public void errorFetchingRecipe() {
        mProgressBar.setVisibility(View.GONE);
        mIngredientsRecyclerView.setVisibility(View.GONE);
        mErrorMessageTextView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setText(getString(R.string.activity_recipe_details_error));
    }

    @Override
    public void navigateToViewInstructions(String url) {
        navigateToWebLink(url);
    }

    @Override
    public void navigateToViewOriginal(String url) {
        navigateToWebLink(url);
    }

    @Override
    protected boolean shouldAnimateOut() {
        return mShouldAnimateOut;
    }

    private FooterHolder.OnFooterNavigationSelected mOnFooterNavigationSelected = new FooterHolder.OnFooterNavigationSelected() {

        @Override
        public void onSelected(FooterHolder.FooterNavType footerNavType) {
            switch (footerNavType) {
                case VIEW_INSTRUCTIONS:
                    mRecipeDetailPresenter.viewInstructions();
                    break;
                case VIEW_ORIGINAL:
                    mRecipeDetailPresenter.viewOriginal();
                    break;
            }
        }
    };
}