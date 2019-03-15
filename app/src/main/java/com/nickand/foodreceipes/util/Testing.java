package com.nickand.foodreceipes.util;

import android.util.Log;

import com.nickand.foodreceipes.model.Recipe;

import java.util.List;

public class Testing {

    private static final String TAG = Testing.class.getSimpleName();

    public static void printRecipes(List<Recipe> recipeList) {
        for (Recipe recipe : recipeList) {
            Log.d(TAG, "onChaned: ".concat(recipe.getTitle()));
        }
    }
}
