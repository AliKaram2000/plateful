package com.aeinae.plateful.home.presenter;

import android.app.Application;
import android.widget.Toast;

import com.aeinae.plateful.data.meals.mapper.MealMapper;
import com.aeinae.plateful.data.meals.model.MealDto;
import com.aeinae.plateful.data.meals.model.MealEntity;
import com.aeinae.plateful.data.meals.repository.MealsRepository;
import com.aeinae.plateful.home.view.HomeView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    MealsRepository repo;
    HomeView homeView;
    public HomePresenter(HomeView homeView, Application application) {
        this.homeView = homeView;
        repo = new MealsRepository(application);
    }

    public void getRandomMeal(){
        Disposable disposable = repo.getRandomMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals->{
                    homeView.displayMealOfTheDay(meals.getMeals().get(0));
                },
        t->{
                    homeView.displayError();
                    });
    }
    public void getAllMeals(String allMeals){
        Disposable disposable = repo.getAllMeals(allMeals)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals->{
                            homeView.updateMealsList(meals.getMeals());
                        },
                        t->{
                            homeView.displayError();
                        }
                );
    }
    public void insertFavoriteMeal(MealDto mealDto){
        insertMealsLocally(mealDto);
    }
    private void insertMealsLocally(MealDto mealDto){
        MealEntity mealEntity = MealMapper.toEntity(mealDto);
        repo.insertMeal(mealEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
