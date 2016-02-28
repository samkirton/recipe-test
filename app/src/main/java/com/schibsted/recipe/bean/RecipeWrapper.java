package com.schibsted.recipe.bean;

public class RecipeWrapper implements Bean {
    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe newVal) {
        recipe = newVal;
    }

}