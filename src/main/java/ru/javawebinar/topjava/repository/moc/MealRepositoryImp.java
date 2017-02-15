package ru.javawebinar.topjava.repository.moc;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ladan on 2/14/17.
 */
public class MealRepositoryImp implements MealRepository {
       
    Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();

    public MealRepositoryImp() {
        for (Meal meal: MealsUtil.initMeals()) {
            mealMap.put(meal.getId(), meal);
        }
    }

    @Override
    public void saveMeal(Meal meal) {
        mealMap.put(meal.getId(), meal);

    }

    @Override
    public void deleteMeal(int id) {
        if (mealMap.containsKey(id)) {
            mealMap.remove(id);
        }
    }

    @Override
    public Meal getMealById(int id) {
        return mealMap.get(id);
    }

    @Override
    public Collection<Meal> getAllMeals() {
        return mealMap.values();
    }
}
