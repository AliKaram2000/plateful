package com.aeinae.plateful.data.meals.datasource.remote;

import com.aeinae.plateful.network.MealsService;
import com.aeinae.plateful.network.RetrofitClient;

import io.reactivex.rxjava3.core.Single;

public class RemoteDataSource {
    MealsService mealsService;
    public RemoteDataSource() {
        mealsService = RetrofitClient.getRetrofit().create(MealsService.class);
    }
    public Single<MealsResponse> getRandomMealResponse(){
        return mealsService.getRandomMeal().onErrorReturnItem(new MealsResponse()); //TODO: Hardcode random meal
    }
    public Single<MealsResponse> getAllMealsResponse(String mealName){
        return mealsService.getAllMeals(mealName).onErrorReturnItem(new MealsResponse()); //TODO: Hardcode random meal
    }
    public Single<MealsResponse> getMealDetails(int mealID){
        return mealsService.getMealDetails(String.valueOf(mealID)).onErrorReturnItem(new MealsResponse());
    }

}
