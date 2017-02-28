package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */

@Controller
public class MealRestController {
    public static Logger LOG = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAllMeals() {
        int userId = AuthorizedUser.id();

        return service.getMeals();
    }



}
