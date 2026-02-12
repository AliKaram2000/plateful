package com.aeinae.plateful.network;

import com.aeinae.plateful.data.meals.datasource.remote.CategoriesResponse;
import com.aeinae.plateful.data.meals.datasource.remote.CountriesResponse;
import com.aeinae.plateful.data.meals.datasource.remote.IngredientsResponse;
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
    @GET("lookup.php")
    Single<MealsResponse> getMealDetails(@Query("i") String mealID);
    @GET("filter.php")
    Single<MealsResponse> filterMealsByCategory(@Query("c") String mealCategory);
    @GET("filter.php")
    Single<MealsResponse> filterMealsByIngredient(@Query("i") String mealIngredient);
    @GET("filter.php")
    Single<MealsResponse> filterMealsByCountry(@Query("a") String mealCountry);
    @GET("list.php?c=list")
    Single<CategoriesResponse> getAllCategories();
    @GET("list.php?a=list")
    Single<CountriesResponse> getAllCountries();
    @GET("list.php?i=list")
    Single<IngredientsResponse> getAllIngredients();
}
