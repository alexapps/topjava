package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ladan on 2/23/17.
 */


@WebServlet("/security_check")
public class LoginController extends HttpServlet {
    public static Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp);
        LOG.info("Log form processing " + req.getParameter("j_username") + " pass " + req.getParameter("j_password"));
        resp.sendRedirect("meals");
    }
}
