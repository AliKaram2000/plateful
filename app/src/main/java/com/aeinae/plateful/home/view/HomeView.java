package com.aeinae.plateful.home.view;

import com.aeinae.plateful.data.meals.model.MealDto;

import java.util.List;

public interface HomeView {
    public void displayMealOfTheDay(MealDto mealDto);
    public void updateMealsList(List<MealDto> meals);
    public void displayError();
}
