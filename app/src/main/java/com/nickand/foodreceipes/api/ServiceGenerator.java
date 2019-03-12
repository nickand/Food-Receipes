package com.nickand.foodreceipes.api;

import com.nickand.foodreceipes.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private RecipeAPI recipeAPI = retrofit.create(RecipeAPI.class);

    public RecipeAPI getRecipeAPI() {
        return recipeAPI;
    }
}
