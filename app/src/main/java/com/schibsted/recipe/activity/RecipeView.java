package com.schibsted.recipe.activity;

import com.schibsted.recipe.bean.Recipe;
import com.schibsted.recipe.bean.Recipes;

public interface RecipeView {
    void getRecipes(Recipe[] recipes);
    void noRecipesFound();
    void errorFetchingRecipes();

}
