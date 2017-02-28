package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;


import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl.ADMIN_ID;
import static ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl.USER_ID;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    // Map key - userId, value - map of Meals related to this user
    private Map< Integer , Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    private static final Comparator<Meal>  USER_MEAL_COMPARATOR = (m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime());

    {
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500), USER_ID);
        save(   new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),USER_ID);
        save(   new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),USER_ID);
        save(   new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),USER_ID);
        save(   new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),USER_ID);
        save(   new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510), USER_ID);

        save(   new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Admin", 510), ADMIN_ID);


    }

    @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> item = new HashMap<>();


        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        item.put(meal.getId(), meal);

        repository.put(userId, item);

        return meal;
    }

    @Override
    public void delete(int id, int userId) {
        Map<Integer, Meal> item = new HashMap<>();

        if (repository.containsKey(userId)) {
            item = repository.get(userId);

            if (item.containsKey(id)) {
                item.remove(id);
            }
        }
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> item = new HashMap<>();
        Meal returnValue = null;

        if (repository.containsKey(userId)) {
            item = repository.get(userId);

            if (item.containsKey(id)) {
                returnValue = item.get(id);
            }
        }

        return returnValue;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Collection<Meal> returnValue =  null;

        if (repository.containsKey(userId)) {
            Map<Integer, Meal> itemMap = repository.get(userId);
            returnValue = itemMap.values().stream().sorted(USER_MEAL_COMPARATOR).collect(Collectors.toList());

        }
        return returnValue;
    }

    @Override
    public Collection<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userid) {
        return getAll(userid).stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime().toLocalTime(), startDate.toLocalTime(), endDate.toLocalTime()))
                .sorted(USER_MEAL_COMPARATOR)
                .collect(Collectors.toList());
    }
}

