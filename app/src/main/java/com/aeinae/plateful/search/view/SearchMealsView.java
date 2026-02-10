package com.aeinae.plateful.search.view;

import com.aeinae.plateful.data.meals.model.MealDto;

import java.util.List;

public interface SearchMealsView {
    public void updateMealsList(List<MealDto> meals);
    public void displayError();
}
