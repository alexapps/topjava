package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.moc.MealRepositoryImp;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ladan on 2/13/17.
 */

@WebServlet(name = "/MealServlet", urlPatterns = {"/MealServlet"})
public class MealServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private MealRepositoryImp repository;
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEAL = "/meals.jsp";

    public MealServlet() {
        repository = new MealRepositoryImp();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
        List<Meal> mealList = new ArrayList<>();
        for (Meal item: repository.getAllMeals()) {
            mealList.add(item);
        }
        List<MealWithExceed> meals = MealsUtil.getFilteredWithExceeded(mealList, LocalTime.MIN, LocalTime.MAX, 2000);

        //super.doGet(req, resp);
        request.setAttribute("mealList", meals);
        request.getRequestDispatcher("/meals.jsp").forward(req, resp);

        */

        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("listMeal")) {
            forward = LIST_MEAL;
            request.setAttribute("mealList", getMealList());
        }
        else if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            repository.deleteMeal(mealId);

            forward = LIST_MEAL;
            request.setAttribute("mealList", getMealList());
        }
        else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = repository.getMealById(mealId);
            request.setAttribute("meal", meal);
        }
        else {
            forward = INSERT_OR_EDIT;
        }


        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Meal meal = new Meal(
                Integer.parseInt(req.getParameter("id")),
                LocalDateTime.now(),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories")));
        repository.saveMeal(meal);
        RequestDispatcher view = req.getRequestDispatcher(LIST_MEAL);
        req.setAttribute("mealList", getMealList ());
        view.forward(req, resp);
    }

    private List<MealWithExceed> getMealList () {
         List<Meal> mealList = new ArrayList<>();
        for (Meal item: repository.getAllMeals()) {
            mealList.add(item);
        }
        return MealsUtil.getFilteredWithExceeded(mealList, LocalTime.MIN, LocalTime.MAX, 2000);

    }
}
