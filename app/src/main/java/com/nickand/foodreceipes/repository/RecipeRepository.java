package com.nickand.foodreceipes.repository;

import android.arch.lifecycle.LiveData;

import com.nickand.foodreceipes.api.RecipeApiClient;
import com.nickand.foodreceipes.model.Recipe;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository instance;
    private RecipeApiClient mRecipeApiClient;

    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    private RecipeRepository() {
        mRecipeApiClient = RecipeApiClient.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeApiClient.getRecipes();
    }

    public void searchRecipeAPI(String query, int pageNumber) {
        if (pageNumber == 0) {
            pageNumber = 1;
        }
        mRecipeApiClient.searchRecipeAPI(query, pageNumber);
    }
}
