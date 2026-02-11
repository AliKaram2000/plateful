package com.aeinae.plateful.favorites.view;

import com.aeinae.plateful.data.meals.model.MealEntity;

import java.util.List;

public interface FavoritesView {
    void setRecyclerView(List<MealEntity> meals);

    void displayFavoriteMeals();
}
