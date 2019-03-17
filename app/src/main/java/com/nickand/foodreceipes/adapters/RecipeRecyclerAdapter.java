package com.nickand.foodreceipes.adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nickand.foodreceipes.R;
import com.nickand.foodreceipes.model.Recipe;
import com.nickand.foodreceipes.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int RECIPE_TYPE = 1;
    public static final int LOADING_TYPE = 2;
    public static final int CATEGORY_TYPE = 3;

    private List<Recipe> mRecipes;
    private OnRecipeListener mOnRecipeListener;

    public RecipeRecyclerAdapter(OnRecipeListener mOnRecipeListener) {
        this.mOnRecipeListener = mOnRecipeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = null;

        switch (i) {
            case RECIPE_TYPE: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recipe_list_item, viewGroup, false);
                return new RecipeViewHolder(view, mOnRecipeListener);
            }

            case LOADING_TYPE: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_loading_list_item, viewGroup, false);
                return new LoadingViewHolder(view);
            }

            case CATEGORY_TYPE: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_category_list_item, viewGroup, false);
                return new CategoryViewHolder(view, mOnRecipeListener);
            }

            default: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recipe_list_item, viewGroup, false);
                return new RecipeViewHolder(view, mOnRecipeListener);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        int itemViewType = getItemViewType(position);

        if (itemViewType == RECIPE_TYPE) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(viewHolder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(mRecipes.get(position).getImageUrl())
                    .into(((RecipeViewHolder) viewHolder).imageView);

            ((RecipeViewHolder) viewHolder).title.setText(mRecipes.get(position).getTitle());
            ((RecipeViewHolder) viewHolder).publisher.setText(mRecipes.get(position).getPublisher());
            ((RecipeViewHolder) viewHolder).socialScore.setText(String.valueOf(Math.round(mRecipes.get(position).getSocialRank())));
        } else if (itemViewType == CATEGORY_TYPE) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Uri path = Uri.parse("android.resource://com.nickand.foodreceipes/drawable/" + mRecipes.get(position).getImageUrl());

            Glide.with(viewHolder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(path)
                    .into(((CategoryViewHolder) viewHolder).mCategoryImage);

            ((CategoryViewHolder) viewHolder).mCategoryTitle.setText(mRecipes.get(position).getTitle());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mRecipes.get(position).getSocialRank() != null && mRecipes.get(position).getSocialRank() == -1) {
            return CATEGORY_TYPE;
        } else if (mRecipes.get(position).getTitle().equals("LOADING...")) {
            return LOADING_TYPE;
        } else {
            return RECIPE_TYPE;
        }
    }

    public void displayLoading() {
        if (!isLoading()) {
            Recipe recipe = new Recipe();
            recipe.setTitle("LOADING...");
            List<Recipe> loadingList = new ArrayList<>();
            loadingList.add(recipe);
            mRecipes = loadingList;
            notifyDataSetChanged();
        }
    }

    private boolean isLoading() {
        if (mRecipes != null && mRecipes.get(mRecipes.size() - 1).getTitle().equals("LOADING...")) {
            return true;
        }
        return false;
    }

    public void displaySearchCategories() {
        List<Recipe> categories = new ArrayList<>();
        for (int i = 0; i < Constants.DEFAULT_SEARCH_CATEGORIES.length; i++) {
            Recipe recipe = new Recipe();
            recipe.setTitle(Constants.DEFAULT_SEARCH_CATEGORIES[i]);
            recipe.setImageUrl(Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[i]);
            recipe.setSocialRank((double) -1);
            categories.add(recipe);
        }

        mRecipes = categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mRecipes != null) {
            return mRecipes.size();
        }
        return 0;
    }

    public void setRecipes(List<Recipe> recipes) {
        mRecipes = recipes;
        notifyDataSetChanged();
    }
}
