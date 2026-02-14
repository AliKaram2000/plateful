package com.aeinae.plateful.calendar.view;

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
import android.widget.CalendarView;
import android.widget.Toast;

import com.aeinae.plateful.R;
import com.aeinae.plateful.calendar.presenter.CalendarPresenter;
import com.aeinae.plateful.data.meals.model.MealEntity;
import com.aeinae.plateful.data.meals.model.PlannedMealEntity;
import com.aeinae.plateful.favorites.presenter.FavoritesPresenter;

import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment implements CalenderMealsView {

    RecyclerView recyclerView;
    CalendarMealsAdapter adapter;
    CalendarPresenter presenter;
    CalendarView calendarView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new CalendarPresenter(this, this.getActivity().getApplication());
        calendarView = view.findViewById(R.id.calendar_view);
        recyclerView = view.findViewById(R.id.week_plan_recycler_view);
        calendarView.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull android.widget.CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth, 0, 0, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                long date = calendar.getTimeInMillis();
                presenter.getPlannedMeals(date);
            }
        });
    }

    @Override
    public void setRecyclerView(List<PlannedMealEntity> meals) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CalendarMealsAdapter();
        adapter.setPlannedMeals(meals);
        recyclerView.setAdapter(adapter);
        adapter.setOnRemovePlannedClickListener(meal -> {
            presenter.deletePlannedMeal(meal);
            Toast.makeText(requireContext(), "Removed "+ meal.getTitle()+ " from favorites", Toast.LENGTH_SHORT).show();
        });
        adapter.setOnPlannedMealClickListener(
                meal ->{
                    NavDirections action = com.aeinae.plateful.favorites.view.FavoritesFragmentDirections.actionFavoritesFragmentToDetailsFragment(meal.getId());
                    NavHostFragment.findNavController(this).navigate(action);
                });
    }
}