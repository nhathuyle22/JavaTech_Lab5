package org.example.Servlet;

import org.example.DAO.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Domain.Users;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") != null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String confirmPassword = request.getParameter("password_confirm").trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            request.setAttribute("error", "Please fill all fields");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Password and confirm password must be the same");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            Users user = UsersDAO.register(0 ,username, email, password);
            if (user == null) {
                request.setAttribute("error", "Username or email already exists");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
//               set session
                request.getSession().setAttribute("username", username);
                request.setAttribute("username", username);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }
}
