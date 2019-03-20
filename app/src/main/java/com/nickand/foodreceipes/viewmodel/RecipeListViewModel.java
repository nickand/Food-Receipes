package com.nickand.foodreceipes.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.nickand.foodreceipes.model.Recipe;
import com.nickand.foodreceipes.repository.RecipeRepository;

import java.util.List;

public class RecipeListViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;
    private boolean mIsViewingRecipes;
    private boolean mIsPerformingQuery;

    public RecipeListViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
        mIsPerformingQuery = false;
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeRepository.getRecipes();
    }

    public void searchRecipeAPI(String query, int pageNumber) {
        mIsViewingRecipes = true;
        mRecipeRepository.searchRecipeAPI(query, pageNumber);
    }

    public void searchNextPage() {
        if (!mIsPerformingQuery && mIsViewingRecipes) {
            mRecipeRepository.searchNextPage();
        }
    }

    public boolean isViewingRecipes() {
        return mIsViewingRecipes;
    }

    public void setIsViewingRecipes(boolean isViewingRecipes) {
        mIsViewingRecipes = isViewingRecipes;
    }

    public void setIsPerformingQuery(boolean isPerformingQuery) {
        mIsPerformingQuery = isPerformingQuery;
    }

    public boolean isPerformingQuery() {
        return mIsPerformingQuery;
    }

    public boolean oBackPressed() {
        if (isPerformingQuery()) {
            //Cancel the query
            mRecipeRepository.cancelRequest();
            mIsPerformingQuery = false;
        }
        if (mIsViewingRecipes) {
            mIsViewingRecipes = false;
            return false;
        }
        return true;
    }
}
