package com.schibsted.recipe.activity;

import com.schibsted.recipe.bean.Recipes;

public interface RecipeView {
    void getRecipes(Recipes recipes);
    void errorFetchingRecipes();

}
