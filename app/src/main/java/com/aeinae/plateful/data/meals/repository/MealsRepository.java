package com.aeinae.plateful.data.meals.repository;

import android.app.Application;

import com.aeinae.plateful.data.meals.datasource.local.LocalDataSource;
import com.aeinae.plateful.data.meals.datasource.remote.CategoriesResponse;
import com.aeinae.plateful.data.meals.datasource.remote.CountriesResponse;
import com.aeinae.plateful.data.meals.datasource.remote.IngredientsResponse;
import com.aeinae.plateful.data.meals.datasource.remote.MealsResponse;
import com.aeinae.plateful.data.meals.datasource.remote.RemoteDataSource;
import com.aeinae.plateful.data.meals.model.MealEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class MealsRepository {
    Application application;
    RemoteDataSource rds;
    LocalDataSource lds;
    public MealsRepository(Application application){
        this.application = application;
        rds = new RemoteDataSource();
        lds = new LocalDataSource(application);
    }
    public Single<MealsResponse> getRandomMeal(){
        return rds.getRandomMealResponse();
    }

    public Single<MealsResponse> getAllMeals(String mealName){
        return rds.getAllMealsResponse(mealName);
    }
    public Single<MealsResponse> getMealDetails(int mealID){
        return rds.getMealDetails(mealID);
    }
    public Completable insertMeal(MealEntity meal){
         return lds.insertMeal(meal);
    }
    public Completable insertAllMeals(List<MealEntity> meals){
        return lds.insertAllMeals(meals);
    }
    public Single<List<MealEntity>> getFavoriteMeals(){
        return lds.getFavoriteMeals();
    }
    public Completable deleteMeal(MealEntity meal){
        return lds.deleteMeal(meal);
    }
    public Single<MealsResponse> filterMealsByCategory(String mealCategory){return rds.filterMealsByCategory(mealCategory);}
    public Single<MealsResponse> filterMealsByIngredients(String mealIngredients){return rds.filterMealsByIngredient(mealIngredients);}
    public Single<MealsResponse> filterMealsByCountry(String mealCountry){return rds.filterMealsByCountry(mealCountry);}
    public Single<CategoriesResponse> getAllCategories() {
        return rds.getAllCategories();
    }

    public Single<CountriesResponse> getAllCountries() {
        return rds.getAllCountries();
    }

    public Single<IngredientsResponse> getAllIngredients() {
        return rds.getAllIngredients();
    }
}
