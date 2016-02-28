package com.schibsted.recipe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.schibsted.recipe.R;
import com.schibsted.recipe.adapter.holder.RecipeHolder;
import com.schibsted.recipe.bean.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Recipe> mRecipes = new ArrayList<>();
    private RecipeHolder.OnRecipeSelected mOnRecipeSelected;

    public RecipeAdapter(RecipeHolder.OnRecipeSelected onRecipeSelected) {
        mOnRecipeSelected = onRecipeSelected;
    }

    public void setRecipes(Recipe[] recipes) {
        Recipe[] recipeArray = recipes != null ? recipes : new Recipe[]{};
        mRecipes = new ArrayList<>();
        mRecipes.addAll(Arrays.asList(recipeArray));

        notifyDataSetChanged();
    }

    public void addRecipes(Recipe[] recipes) {
        Recipe[] recipeArray = recipes != null ? recipes : new Recipe[]{};
        mRecipes.addAll(Arrays.asList(recipeArray));

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
        if (holder instanceof RecipeHolder) {
            ((RecipeHolder) holder).populate(mRecipes.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mRecipes != null ? mRecipes.size() : 0;
    }
}
