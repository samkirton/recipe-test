package com.schibsted.recipe.activity;

import com.schibsted.recipe.bean.Recipe;

public interface RecipeDetailsView {
    void loadingRecipe();
    void displayRecipe(Recipe recipe);
    void noRecipeFound();
    void errorFetchingRecipe();
}
