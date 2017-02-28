package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.xml.ws.ServiceMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public List<MealWithExceed> getMeals() {
        return MealsUtil.getFilteredWithExceeded(repository.getAll(AuthorizedUser.id()).stream().collect(Collectors.toList()), LocalTime.MIN, LocalTime.MAX, 10000);
    }

    @Override
    public List<MealWithExceed> getByPeriod(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return MealsUtil.getFilteredWithExceeded(repository.getBetween(startDate, endDate, userId).stream().collect(Collectors.toList()), LocalTime.MIN, LocalTime.MAX, 10000);
    }


    @Override
    public void delete(int id, int userId) {
        repository.delete(id, userId);

    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(id, userId);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        return repository.save(meal, userId);
    }
}
