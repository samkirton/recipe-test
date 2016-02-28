package com.schibsted.recipe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.schibsted.recipe.R;
import com.schibsted.recipe.adapter.holder.RecipeHolder;
import com.schibsted.recipe.bean.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Recipe[] mRecipes;
    private RecipeHolder.OnRecipeSelected mOnRecipeSelected;

    public RecipeAdapter(RecipeHolder.OnRecipeSelected onRecipeSelected) {
        mOnRecipeSelected = onRecipeSelected;
    }

    public void setRecipes(Recipe[] recipes) {
        mRecipes = recipes != null ? recipes : new Recipe[]{};
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        return new RecipeHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_recipe, parent, false), mOnRecipeSelected
        );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RecipeHolder) holder).populate(mRecipes[position]);
    }

    @Override
    public int getItemCount() {
        return mRecipes != null ? mRecipes.length : 0;
    }
}
