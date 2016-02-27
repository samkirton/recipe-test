package com.schibsted.recipe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.schibsted.recipe.R;
import com.schibsted.recipe.adapter.holder.RecipeHolder;
import com.schibsted.recipe.bean.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Recipe[] mRecipes;

    public void setRecipes(Recipe[] recipes) {
        mRecipes = recipes;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RecipeHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_recipe, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((RecipeHolder) viewHolder).populate(mRecipes[i]);
    }

    @Override
    public int getItemCount() {
        return mRecipes != null ? mRecipes.length : 0;
    }
}
