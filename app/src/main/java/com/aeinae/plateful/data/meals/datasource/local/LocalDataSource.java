package com.aeinae.plateful.data.meals.datasource.local;

import android.app.Application;

import com.aeinae.plateful.data.meals.model.MealEntity;
import com.aeinae.plateful.data.meals.model.PlannedMealEntity;
import com.aeinae.plateful.database.AppDataBase;
import com.aeinae.plateful.database.MealDao;
import com.aeinae.plateful.database.PlannedMealDao;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class LocalDataSource {
    Application application;
    AppDataBase db;
    MealDao mealDao;
    PlannedMealDao plannedMealDao;
    public LocalDataSource(Application application){
        this.application = application;
        db = AppDataBase.getInstance(application);
        mealDao = db.getMealDao();
        plannedMealDao = db.getPlannedMealDao();
    }
    public Completable insertMeal(MealEntity meal){
        return mealDao.insert(meal);
    }
    public Completable insertAllMeals(List<MealEntity> meals){
        return mealDao.insertAll(meals);
    }
    public Single<List<MealEntity>> getFavoriteMeals(){
        return mealDao.getAllMeals();
    }
    public Completable deleteMeal(MealEntity meal){
        return mealDao.delete(meal);
    }
    public Completable deleteAllMeals(){
        return mealDao.deleteAll();
    }
    public Completable insertPlannedMeal(PlannedMealEntity meal){
        return plannedMealDao.insertScheduledMeal(meal);
    }
    public Completable removePlanedMeal(PlannedMealEntity meal){
        return plannedMealDao.removeShceduledMeal(meal);
    }
    public Single<List<PlannedMealEntity>> getAllPlannedMeals(long date){
        return plannedMealDao.getAllShceduledMeals(date);
    }


}
