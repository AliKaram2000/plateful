package com.aeinae.plateful.network;

import com.aeinae.plateful.data.meals.datasource.remote.MealsResponse;
import com.aeinae.plateful.data.meals.model.MealDto;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealsService {
    @GET("random.php")
    Single<MealsResponse> getRandomMeal();
    @GET("search.php")
    Single<MealsResponse> getAllMeals(@Query("s") String mealName);
}
