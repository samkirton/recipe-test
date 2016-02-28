package com.schibsted.recipe.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.schibsted.recipe.R;
import com.schibsted.recipe.bean.Recipe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.adapter_recipe_title) TextView mTitleTextView;

    private Recipe mRecipe;
    private OnRecipeSelected mOnRecipeSelected;

    public RecipeHolder(View itemView, OnRecipeSelected onRecipeSelected) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mOnRecipeSelected = onRecipeSelected;
    }

    public void populate(Recipe recipe) {
        mRecipe = recipe;
        mTitleTextView.setText(Html.fromHtml(recipe.getTitle()));
    }

    @OnClick(R.id.adapter_recipe_title)
    public void selectRecipe() {
        mOnRecipeSelected.onRecipeSelected(mRecipe);
    }

    public interface OnRecipeSelected {
        void onRecipeSelected(Recipe recipe);
    }
}
