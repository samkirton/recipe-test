package com.schibsted.recipe.bean;

public class Recipes implements Bean {
    private int count;
    private Recipe[] recipes;

    public int getCount() {
        return count;
    }

    public void setCount(int newVal) {
        count = newVal;
    }

    public Recipe[] getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipe[] newVal) {
        recipes = newVal;
    }

}