package com.aeinae.plateful.favorites.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.aeinae.plateful.R;
import com.aeinae.plateful.data.meals.model.MealEntity;
import com.bumptech.glide.Glide;
import java.util.List;
import lombok.Setter;

public class FavoriteMealsAdapter extends RecyclerView.Adapter<FavoriteMealsAdapter.FavoriteMealsViewHolder> {
    private List<MealEntity> meals = List.of();
    @Setter
    private OnRemoveFavoriteClickListener onRemoveFavoriteClickListener;
    @Setter
    private OnFavoriteCardClickListener onFavoriteCardClickListener;
    public void setMealsList(List<MealEntity> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new FavoriteMealsViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull FavoriteMealsViewHolder holder, int position) {
        MealEntity meal = meals.get(position);
        holder.mealName.setText(meal.getTitle());
        Glide.with(holder.mealImage)
                .load(meal.getThumbnail())
                .into(holder.mealImage);

        holder.removeFavorite.setOnClickListener(v -> {
            if (onRemoveFavoriteClickListener != null) {
                onRemoveFavoriteClickListener.onRemoveFavorite(meal);
            }
        });

        holder.itemView.setOnClickListener(v -> {
            if (onFavoriteCardClickListener != null) {
                onFavoriteCardClickListener.onFavoriteCardClick(meal.getId());
            }
        });
    }
    @Override
    public int getItemCount() {
        return meals.size();
    }
    public static class FavoriteMealsViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImage;
        TextView mealName;
        ImageView removeFavorite;
        public FavoriteMealsViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.meal_image);
            mealName = itemView.findViewById(R.id.meal_name);
            removeFavorite = itemView.findViewById(R.id.ic_removePlanned);
        }
    }


}