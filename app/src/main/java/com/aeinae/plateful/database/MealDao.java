package com.aeinae.plateful.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.aeinae.plateful.data.meals.model.MealEntity;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
@Dao
public interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(MealEntity meal);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<MealEntity> meals);
    @Query("SELECT * FROM meals")
    Single<List<MealEntity>> getAllMeals();
    @Delete
    Completable delete(MealEntity meal);
    @Query("DELETE FROM meals")
    Completable deleteAll();

}