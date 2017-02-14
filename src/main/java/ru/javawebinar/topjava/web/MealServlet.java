package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by ladan on 2/13/17.
 */
public class MealServlet extends HttpServlet {
    public static Logger LOG = LoggerFactory.getLogger(MealServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MealWithExceed> meals = MealsUtil.getFilteredWithExceeded(MealsUtil.initMeals(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);

        //super.doGet(req, resp);
        req.setAttribute("mealList", meals);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);

    }
}
