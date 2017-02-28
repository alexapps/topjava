package ru.javawebinar.topjava.repository;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */

@Repository
public interface MealRepository {
    Meal save(Meal Meal, int userId);

    void delete(int id, int userId);

    Meal get(int id, int userId);

    Collection<Meal> getAll(int userid);

    Collection<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userid);
}
