package com.aeinae.plateful.calendar.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aeinae.plateful.R;
import com.aeinae.plateful.data.meals.model.PlannedMealEntity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CalendarMealsAdapter extends RecyclerView.Adapter<CalendarMealsAdapter.PlannedMealsViewHolder> {

    private List<PlannedMealEntity> plannedMeals = new ArrayList<>();
    private OnPlannedMealClickListener onPlannedMealClickListener;
    private OnRemovePlannedClickListener onRemovePlannedClickListener;

    @NonNull
    @Override
    public PlannedMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calendar, parent, false);
        return new PlannedMealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlannedMealsViewHolder holder, int position) {
        PlannedMealEntity meal = plannedMeals.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return plannedMeals.size();
    }

    public void setPlannedMeals(List<PlannedMealEntity> meals) {
        this.plannedMeals = meals;
        notifyDataSetChanged();
    }

    public void setOnPlannedMealClickListener(OnPlannedMealClickListener listener) {
        this.onPlannedMealClickListener = listener;
    }

    public void setOnRemovePlannedClickListener(OnRemovePlannedClickListener listener) {
        this.onRemovePlannedClickListener = listener;
    }

    class PlannedMealsViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mealImage;
        private final TextView mealName;
        private final ImageView removePlannedIcon;

        PlannedMealsViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.meal_image);
            mealName = itemView.findViewById(R.id.meal_name);
            removePlannedIcon = itemView.findViewById(R.id.ic_removePlanned);
        }

        void bind(PlannedMealEntity meal) {
            mealName.setText(meal.getTitle());

            Glide.with(mealImage.getContext())
                    .load(meal.getThumbnail())
                    .into(mealImage);

            itemView.setOnClickListener(v -> {
                if (onPlannedMealClickListener != null) {
                    onPlannedMealClickListener.onPlannedMealClick(meal);
                }
            });

            removePlannedIcon.setOnClickListener(v -> {
                if (onRemovePlannedClickListener != null) {
                    onRemovePlannedClickListener.onRemovePlannedClick(meal);
                }
            });
        }
    }

    public interface OnPlannedMealClickListener {
        void onPlannedMealClick(PlannedMealEntity meal);
    }

    public interface OnRemovePlannedClickListener {
        void onRemovePlannedClick(PlannedMealEntity meal);
    }
}