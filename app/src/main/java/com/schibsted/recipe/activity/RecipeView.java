package com.schibsted.recipe.activity;

import com.schibsted.recipe.bean.Recipe;

public interface RecipeView {
    void loadingRecipes();
    void displayRecipes(Recipe[] recipes);
    void addMoreRecipes(Recipe[] recipes);
    void noRecipesFound();
    void errorFetchingRecipes();
}
