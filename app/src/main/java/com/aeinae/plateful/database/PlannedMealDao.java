package com.aeinae.plateful.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aeinae.plateful.data.meals.model.PlannedMealEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface PlannedMealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertScheduledMeal(PlannedMealEntity plannedMeal);
    @Delete
    Completable removeShceduledMeal(PlannedMealEntity plannedMeal);
    @Query("SELECT * FROM planned_meal WHERE date = :date")
    Single<List<PlannedMealEntity>> getAllShceduledMeals(long date);

}
