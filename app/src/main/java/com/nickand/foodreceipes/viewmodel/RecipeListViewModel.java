package com.nickand.foodreceipes.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.nickand.foodreceipes.model.Recipe;
import com.nickand.foodreceipes.repository.RecipeRepository;

import java.util.List;

public class RecipeListViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;

    public RecipeListViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeRepository.getRecipes();
    }

    public void searchRecipeAPI(String query, int pageNumber) {
        mRecipeRepository.searchRecipeAPI(query, pageNumber);
    }
}
