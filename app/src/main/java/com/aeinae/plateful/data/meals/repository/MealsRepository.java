package com.aeinae.plateful.data.meals.repository;

import com.aeinae.plateful.data.meals.datasource.remote.MealsResponse;
import com.aeinae.plateful.data.meals.datasource.remote.RemoteDataSource;

import io.reactivex.rxjava3.core.Single;

public class MealsRepository {
    RemoteDataSource rds = new RemoteDataSource();
    public Single<MealsResponse> getRandomMeal(){
        return rds.getRandomMealResponse();
    }
    public Single<MealsResponse> getAllMeals(String mealName){
        return rds.getAllMealsResponse(mealName);
    }
}
