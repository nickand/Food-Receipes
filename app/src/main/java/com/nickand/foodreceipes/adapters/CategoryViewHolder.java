package com.nickand.foodreceipes.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nickand.foodreceipes.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    CircleImageView mCategoryImage;
    TextView mCategoryTitle;
    OnRecipeListener mOnRecipeListener;

    public CategoryViewHolder(@NonNull View itemView, OnRecipeListener onRecipeListener) {
        super(itemView);

        this.mOnRecipeListener = onRecipeListener;
        mCategoryImage = itemView.findViewById(R.id.categoryImage);
        mCategoryTitle = itemView.findViewById(R.id.categoryTitle);

        itemView.setOnClickListener(this );
    }

    @Override
    public void onClick(View v) {
        mOnRecipeListener.onCategoryClick(mCategoryTitle.getText().toString());
    }
}
