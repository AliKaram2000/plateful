package com.aeinae.plateful.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aeinae.plateful.R;
import com.aeinae.plateful.data.meals.model.MealDto;
import com.aeinae.plateful.search.view.OnFavoriteSearchMealClickListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HomeMealsAdapter extends RecyclerView.Adapter<HomeMealsAdapter.HomeMealsViewHolder> {
    private List<MealDto> meals = List.of();
    private OnFavoriteHomeMealClickListener onFavoriteHomeMealClickListener;
    private OnHomeCardClick onHomeCardClick;
    public void setMealsList(List<MealDto> meals){
        this.meals = meals;
        notifyDataSetChanged();
    }
    public void setOnFavoriteClickListener(OnFavoriteHomeMealClickListener listener) {
        this.onFavoriteHomeMealClickListener = listener;
    }
    public void setOnHomeCardClickListener(OnHomeCardClick listener){
        this.onHomeCardClick = listener;
    }

    @NonNull
    @Override
    public HomeMealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_screen_card, parent, false);
        return new HomeMealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeMealsViewHolder holder, int position) {
        MealDto meal = meals.get(position);
        holder.mealName.setText(meal.getStrMeal());
        Glide.with(holder.mealImage)
                .load(meal.getStrMealThumb())
                .into(holder.mealImage);
        holder.mealFavorite.setOnClickListener(v -> {
            if (onFavoriteHomeMealClickListener != null) {
                onFavoriteHomeMealClickListener.onFavoriteClicked(meal);
            }
        });
        holder.itemView.setOnClickListener(v->{
            onHomeCardClick.onHomeCardClick(meal.getIdMeal());
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
    public static class HomeMealsViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImage;
        TextView mealName;
        ImageView mealFavorite;
        public HomeMealsViewHolder(View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.meal_image);
            mealName = itemView.findViewById(R.id.meal_name);
            mealFavorite = itemView.findViewById(R.id.meal_favorite);

        }
    }
}


