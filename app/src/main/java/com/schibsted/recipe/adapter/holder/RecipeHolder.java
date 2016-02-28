package com.schibsted.recipe.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.schibsted.recipe.R;
import com.schibsted.recipe.bean.Recipe;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.adapter_recipe_title) TextView mTitleTextView;
    @Bind(R.id.adapter_recipe_icon) ImageView mImageView;

    private Recipe mRecipe;
    private View mView;
    private OnRecipeSelected mOnRecipeSelected;

    public RecipeHolder(View itemView, OnRecipeSelected onRecipeSelected) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mView = itemView;
        mOnRecipeSelected = onRecipeSelected;
    }

    public void populate(Recipe recipe) {
        mRecipe = recipe;
        mTitleTextView.setText(Html.fromHtml(recipe.getTitle()));
        int width = mView.getContext().getResources().getDimensionPixelOffset(R.dimen.adapter_recipe_image);

        Picasso.with(mView.getContext())
                .load(recipe.getImage_url())
                .resize(width,width)
                .centerCrop()
                .into(mImageView);
    }

    @OnClick(R.id.adapter_recipe_row)
    public void selectRecipe() {
        mOnRecipeSelected.onRecipeSelected(mRecipe);
    }

    public interface OnRecipeSelected {
        void onRecipeSelected(Recipe recipe);
    }
}
