package com.aeinae.plateful.details.view;

import com.aeinae.plateful.data.meals.model.MealDto;

import java.util.List;

public interface DetailsView {
    void displayMealDetails(MealDto meal, List<String> ingredients, List<String> measures);

    void onError();
}
