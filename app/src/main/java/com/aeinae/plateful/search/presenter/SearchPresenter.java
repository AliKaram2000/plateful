package com.aeinae.plateful.search.presenter;

import android.app.Application;

import com.aeinae.plateful.data.meals.mapper.MealMapper;
import com.aeinae.plateful.data.meals.model.MealDto;
import com.aeinae.plateful.data.meals.model.MealEntity;
import com.aeinae.plateful.data.meals.repository.MealsRepository;
import com.aeinae.plateful.search.view.SearchMealsView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {
    MealsRepository repo;
    SearchMealsView searchMealsView;
    public SearchPresenter(SearchMealsView searchMealsView, Application application){
        this.searchMealsView = searchMealsView;
        repo = new MealsRepository(application);
    }
    public void searchAllMeals(String searchMeal){
        Disposable disposable = repo.getAllMeals(searchMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals->{
                            searchMealsView.updateMealsList(meals.getMeals());
                        },
                        t->{
                            searchMealsView.displayError();
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
                .subscribe(() -> { /* success, maybe toast/log */ },
                        throwable -> {
                            throwable.printStackTrace();
                            searchMealsView.displayError();
                });
    }


    public void filterMealsByCategory(String category) {
        Disposable disposable = repo.filterMealsByCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> searchMealsView.updateMealsList(meals.getMeals()),
                        t -> searchMealsView.displayError()
                );
    }

    public void filterMealsByIngredient(String ingredient) {
        Disposable disposable = repo.filterMealsByIngredients(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> searchMealsView.updateMealsList(meals.getMeals()),
                        t -> searchMealsView.displayError()
                );
    }

    public void filterMealsByCountry(String country) {
        Disposable disposable = repo.filterMealsByCountry(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> searchMealsView.updateMealsList(meals.getMeals()),
                        t -> searchMealsView.displayError()
                );
    }
    public void loadCategories() {
        Disposable disposable = repo.getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> searchMealsView.showFilterOptions("Category", response.getCategories()),
                        t -> searchMealsView.displayError()
                );
    }

    public void loadCountries() {
        Disposable disposable = repo.getAllCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> searchMealsView.showFilterOptions("Country", response.getCountries()),
                        t -> searchMealsView.displayError()
                );
    }

    public void loadIngredients() {
        Disposable disposable = repo.getAllIngredients()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> searchMealsView.showFilterOptions("Ingredient", response.getIngredients()),
                        t -> searchMealsView.displayError()
                );
    }

}
