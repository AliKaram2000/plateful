package com.aeinae.plateful.search.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aeinae.plateful.R;
import com.aeinae.plateful.data.meals.model.MealDto;
import com.bumptech.glide.Glide;

import java.util.List;

public class SearchMealsAdapter extends RecyclerView.Adapter<SearchMealsAdapter.SearchMealsViewHolder> {
    private List<MealDto> meals = List.of();
    public void setMealsList(List<MealDto> meals){
        this.meals = meals;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SearchMealsAdapter.SearchMealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_screen_card, parent, false);
        return new SearchMealsAdapter.SearchMealsViewHolder(view);
    }
    @Override
    public void onBindViewHolder(SearchMealsAdapter.SearchMealsViewHolder holder, int position) {
        MealDto meal = meals.get(position);
        holder.mealName.setText(meal.getStrMeal());
        Glide.with(holder.mealImage)
                .load(meal.getStrMealThumb())
                .into(holder.mealImage);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
    public static class SearchMealsViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImage;
        TextView mealName;
        public SearchMealsViewHolder(View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.meal_image);
            mealName = itemView.findViewById(R.id.meal_name);
        }
    }

}
