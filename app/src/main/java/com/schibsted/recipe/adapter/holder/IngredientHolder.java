package com.schibsted.recipe.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.schibsted.recipe.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IngredientHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.adapter_ingredients_title) TextView mTitleTextView;

    public IngredientHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(String value) {
        mTitleTextView.setText(Html.fromHtml(value));
    }
}
