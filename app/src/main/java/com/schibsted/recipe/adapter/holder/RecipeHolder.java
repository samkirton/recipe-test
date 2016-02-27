package com.schibsted.recipe.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.schibsted.recipe.R;
import com.schibsted.recipe.bean.Recipe;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.adapter_recipe_title) TextView mTitleTextView;

    public RecipeHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void populate(Recipe recipe) {
        mTitleTextView.setText(recipe.getTitle());
    }
}
