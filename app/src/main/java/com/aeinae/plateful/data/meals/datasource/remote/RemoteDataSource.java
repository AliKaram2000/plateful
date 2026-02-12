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
    public Single<MealsResponse> filterMealsByCategory(String mealCategory){
        return mealsService.filterMealsByCategory(mealCategory).onErrorReturnItem(new MealsResponse());
    }
    public Single<MealsResponse> filterMealsByIngredient(String mealIngredient){
       return mealsService.filterMealsByIngredient(mealIngredient).onErrorReturnItem(new MealsResponse());
    }
    public Single<MealsResponse> filterMealsByCountry(String mealCountry){
        return mealsService.filterMealsByCountry(mealCountry).onErrorReturnItem(new MealsResponse());
    }
    public Single<CategoriesResponse> getAllCategories() {
        return mealsService.getAllCategories().onErrorReturnItem(new CategoriesResponse());
    }

    public Single<CountriesResponse> getAllCountries() {
        return mealsService.getAllCountries().onErrorReturnItem(new CountriesResponse());
    }

    public Single<IngredientsResponse> getAllIngredients() {
        return mealsService.getAllIngredients().onErrorReturnItem(new IngredientsResponse());
    }

}
