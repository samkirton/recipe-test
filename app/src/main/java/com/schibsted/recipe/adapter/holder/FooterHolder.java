package com.schibsted.recipe.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.schibsted.recipe.R;
import com.schibsted.recipe.bean.Recipe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FooterHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.adapter_ingredients_footer_publisher_name) TextView mPublisherName;
    @Bind(R.id.adapter_ingredients_footer_social_rank) TextView mSocialRank;

    private View mView;
    private OnFooterNavigationSelected mOnFooterNavigationSelected;

    public FooterHolder(View itemView, OnFooterNavigationSelected onFooterNavigationSelected) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mView = itemView;
        mOnFooterNavigationSelected = onFooterNavigationSelected;
    }

    public void populate(Recipe recipe) {
        if (recipe != null) {
            String socialRank = mView.getContext().getString(R.string.activity_recipe_details_social_rank, recipe.getSocial_rank());
            mPublisherName.setText(recipe.getPublisher());
            mSocialRank.setText(socialRank);
        }
    }

    @OnClick(R.id.adapter_ingredients_footer_view_instructions)
    public void viewInstructionsClick() {
        mOnFooterNavigationSelected.onSelected(FooterNavType.VIEW_INSTRUCTIONS);
    }

    @OnClick(R.id.adapter_ingredients_footer_view_originals)
    public void viewOriginalsClick() {
        mOnFooterNavigationSelected.onSelected(FooterNavType.VIEW_ORIGINAL);
    }

    public enum FooterNavType {
        VIEW_INSTRUCTIONS,
        VIEW_ORIGINAL
    }

    public interface OnFooterNavigationSelected {
        void onSelected(FooterNavType footerNavType);
    }
}
