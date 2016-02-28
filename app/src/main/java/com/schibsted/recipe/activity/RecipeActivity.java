package com.schibsted.recipe.activity;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.schibsted.recipe.DefaultApplication;
import com.schibsted.recipe.R;
import com.schibsted.recipe.adapter.RecipeAdapter;
import com.schibsted.recipe.bean.Recipe;
import com.schibsted.recipe.presenter.RecipePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeActivity extends BaseActivity implements RecipeView {
    @Bind(R.id.activity_recipe_recyclerview) RecyclerView mRecyclerView;
    @Bind(R.id.activity_recipe_progressbar) ProgressBar mProgressBar;
    @Bind(R.id.activity_recipe_error_message) TextView mErrorMessageTextView;

    private RecipePresenter mRecipePresenter;
    private RecipeAdapter mRecipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);
        setTitle(getString(R.string.activity_recipe_title));

        mRecipeAdapter = new RecipeAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecipeAdapter);

        mRecipePresenter = new RecipePresenter(DefaultApplication.getInstance().getApiManager(), this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRecipePresenter.loadInitialRecipes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_recipe, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.activity_recipe_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(mOnQueryTextListener);
        MenuItemCompat.setOnActionExpandListener(searchMenuItem, mOnActionExpandListener);

        return true;
    }

    @Override
    public void loadingRecipes() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        mErrorMessageTextView.setVisibility(View.GONE);
        hideSoftKeyboard(mErrorMessageTextView);
    }

    @Override
    public void displayRecipes(Recipe[] recipes) {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setVisibility(View.GONE);
        mRecipeAdapter.setRecipes(recipes);
    }

    @Override
    public void noRecipesFound() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mErrorMessageTextView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setText(getString(R.string.activity_recipe_no_results, mRecipePresenter.getLastSearchTerms()));
    }

    @Override
    public void errorFetchingRecipes() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mErrorMessageTextView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setText(R.string.activity_recipe_error);
    }

    private SearchView.OnQueryTextListener mOnQueryTextListener = new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String query) {
            mRecipePresenter.loadRecipes(query, 0);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private MenuItemCompat.OnActionExpandListener mOnActionExpandListener = new MenuItemCompat.OnActionExpandListener() {

        @Override
        public boolean onMenuItemActionExpand(MenuItem item) {
            return true;
        }

        @Override
        public boolean onMenuItemActionCollapse(MenuItem item) {
            mRecipePresenter.loadInitialRecipes();
            return true;
        }
    };
}