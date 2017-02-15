package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;


/**
 * Created by ladan on 2/14/17.
 */
public interface MealRepository {
    public void saveMeal(Meal meal);
    public void deleteMeal(int id);
    public Meal getMealById(int id);
    public Collection<Meal> getAllMeals();
}
