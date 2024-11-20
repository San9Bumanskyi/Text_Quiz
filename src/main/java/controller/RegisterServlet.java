package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import utils.UserDatabase;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        if (UserDatabase.getUser(username) != null) {
            req.setAttribute("message", "Username already exists.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        User newUser = new User(username, password, role);
        UserDatabase.addUser(newUser);
        resp.sendRedirect("login.jsp");
    }
}
