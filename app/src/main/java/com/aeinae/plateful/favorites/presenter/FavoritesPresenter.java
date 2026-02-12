package com.aeinae.plateful.favorites.presenter;

import android.app.Application;
import android.util.Log;

import com.aeinae.plateful.data.meals.model.MealEntity;
import com.aeinae.plateful.data.meals.repository.MealsRepository;
import com.aeinae.plateful.favorites.view.FavoritesView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritesPresenter {
    MealsRepository repo;
    FavoritesView favoritesView;
    public FavoritesPresenter(FavoritesView favoritesView, Application application){
        this.favoritesView = favoritesView;
        this.repo = new MealsRepository(application);
    }
    public void getFavoriteMeals(){
        Disposable disposable =repo.getFavoriteMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals-> {
                            favoritesView.setRecyclerView(meals);
                            Log.d("getfavtest",meals.toString());
                            },
                        t -> {
                            Log.d("getfavtest", t.getMessage());
                        }
                        );
    }
    public void deleteFavoriteMeal(MealEntity meal){
        repo.deleteMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
