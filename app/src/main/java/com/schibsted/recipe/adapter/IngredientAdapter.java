package com.schibsted.recipe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.schibsted.recipe.R;
import com.schibsted.recipe.adapter.holder.FooterHolder;
import com.schibsted.recipe.adapter.holder.IngredientHolder;
import com.schibsted.recipe.adapter.holder.StaticHolder;
import com.schibsted.recipe.bean.Recipe;

public class IngredientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Recipe mRecipe;
    private String[] mIngredients;
    private FooterHolder.OnFooterNavigationSelected mOnFooterNavigationSelected;

    private static final int HEADER = 0x1;
    private static final int FOOTER = 0x2;

    public IngredientAdapter(FooterHolder.OnFooterNavigationSelected onFooterNavigationSelected) {
        mOnFooterNavigationSelected = onFooterNavigationSelected;
    }

    public void setIngredients(Recipe recipe) {
        mRecipe = recipe;
        mIngredients = mRecipe.getIngredients() != null ? mRecipe.getIngredients() : new String[]{};
        notifyDataSetChanged();
    }

    private int getBasicItemCount() {
        return mIngredients != null ? mIngredients.length : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER:
                return new StaticHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_ingredients_header, parent, false));
            case FOOTER:
                return new FooterHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_ingredients_footer, parent, false), mOnFooterNavigationSelected);
            default:
                return new IngredientHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_ingredients, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof IngredientHolder) {
            ((IngredientHolder) holder).populate(mIngredients[position - 1]);
        } else if (holder instanceof FooterHolder) {
            ((FooterHolder)holder).populate(mRecipe);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else if (position == getBasicItemCount()+1 || (getBasicItemCount() == 0 && position == 1)) {
            return FOOTER;
        } else {
            return -1;
        }
    }

    @Override
    public int getItemCount() {
        return getBasicItemCount() + 2; // add the header and footer rows
    }
}
