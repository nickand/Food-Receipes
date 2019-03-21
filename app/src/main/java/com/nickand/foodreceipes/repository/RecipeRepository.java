package com.nickand.foodreceipes.repository;

import android.arch.lifecycle.LiveData;

import com.nickand.foodreceipes.api.RecipeApiClient;
import com.nickand.foodreceipes.model.Recipe;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository instance;
    private RecipeApiClient mRecipeApiClient;
    private String mQuery;
    private int mPageNumber;

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

    public LiveData<Recipe> getRecipe() {
        return mRecipeApiClient.getRecipe();
    }

    public void searchRecipeById(String recipeId) {
        mRecipeApiClient.searchRecipeById(recipeId);
    }

    public void searchRecipeAPI(String query, int pageNumber) {
        if (pageNumber == 0) {
            pageNumber = 1;
        }
        mQuery = query;
        mPageNumber = pageNumber;
        mRecipeApiClient.searchRecipeAPI(query, pageNumber);
    }

    public void searchNextPage() {
        searchRecipeAPI(mQuery, mPageNumber + 1);
    }

    public void cancelRequest() {
        mRecipeApiClient.cancelRequest();
    }
}
