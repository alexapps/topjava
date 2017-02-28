package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {
    public List<MealWithExceed> getMeals();

    public List<MealWithExceed> getByPeriod(LocalDateTime startDate, LocalDateTime endDate, int userId);

    // id - meal id
    public void delete(int id, int userId);
    public Meal get(int id, int userId);
    public Meal save(Meal meal, int userId);
}
