package com.aeinae.plateful.details.presenter;

import com.aeinae.plateful.data.meals.model.MealDto;

public interface MealCallBack {
    void getMealByID(MealDto meal);
}
