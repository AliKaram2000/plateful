package com.aeinae.plateful.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aeinae.plateful.R;
import com.aeinae.plateful.data.meals.model.MealDto;
import com.aeinae.plateful.home.presenter.HomePresenter;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment implements HomeView {
    private ImageView randomMealImg;
    private TextView randomMealTitle;
    private RecyclerView recyclerView;
    HomeMealsAdapter adapter;
    private static String ALL_MEALS = getRandomAlphabet();
    private OnHomeCardClick onHomeCardClick;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        HomePresenter presenter = new HomePresenter(this, this.requireActivity().getApplication());
        presenter.getRandomMeal();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new HomeMealsAdapter();
        presenter.getAllMeals(ALL_MEALS);
        recyclerView.setAdapter(adapter);
        adapter.setOnFavoriteClickListener(meal -> {
            Toast.makeText(requireContext(),
                    "Meal added to Favorites: " + meal.getStrMeal(),
                    Toast.LENGTH_SHORT).show();
            presenter.insertFavoriteMeal(meal);
        });
        adapter.setOnHomeCardClickListener(id->{
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id);
            NavHostFragment.findNavController(this).navigate(action);
        });
    }

    @Override
    public void displayMealOfTheDay(MealDto mealDto) {
        randomMealTitle.setText(mealDto.getStrMeal());
        Glide.with(randomMealImg)
                .load(mealDto.getStrMealThumb())
                .into(randomMealImg);
        randomMealImg.setOnClickListener(v->{
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(mealDto.getIdMeal());
            NavHostFragment.findNavController(this).navigate(action);
        });
    }

    @Override
    public void updateMealsList(List<MealDto> meals) {
        adapter.setMealsList(meals);
    }

    @Override
    public void displayError() {
        Toast.makeText(getContext(), "Failed to load meal", Toast.LENGTH_SHORT);
    }
    public void bindView(View view){
        randomMealImg = view.findViewById(R.id.randomMealImg);
        randomMealTitle = view.findViewById(R.id.randomMealTitle);
        recyclerView = view.findViewById(R.id.recyclerViewHome);
    }
    private static String getRandomAlphabet() {
        Random random = new Random();
        char randomChar = (char) ('A' + random.nextInt(26));
        return String.valueOf(randomChar);
    }

}