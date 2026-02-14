package com.aeinae.plateful.calendar.view;

import com.aeinae.plateful.data.meals.model.MealEntity;
import com.aeinae.plateful.data.meals.model.PlannedMealEntity;

import java.util.List;

public interface CalenderMealsView {

    void setRecyclerView(List<PlannedMealEntity> meals);
}
