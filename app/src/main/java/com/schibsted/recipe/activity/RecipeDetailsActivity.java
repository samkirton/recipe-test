package com.schibsted.recipe.activity;

import android.app.Activity;
import android.os.Bundle;

import com.schibsted.recipe.R;

public class RecipeDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
    }
}
