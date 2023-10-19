package org.example.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.DAO.ProductDAO;
import org.example.DAO.UsersDAO;
import org.example.Domain.Product;
import org.example.Domain.Users;
import org.example.Utils.HibernateUtils;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.*;
@WebServlet(name = "Login", value = "/Login")
public class LoginServlet extends HttpServlet {
    UsersDAO userdao = new UsersDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") != null) {
            response.sendRedirect("/lab05/");
        } else {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    request.setAttribute("username", cookie.getValue());
                }

                if (cookie.getName().equals("password")) {
                    request.setAttribute("password", cookie.getValue());
                }
            }
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String remember = request.getParameter("remember");
        System.out.println(username + " " + password + " " + remember);

        Users user = userdao.checkUsers(username, password);
        if (user == null) {
            request.setAttribute("error", "Username or password is incorrect");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if (remember != null) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("username", "");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            request.getSession().setAttribute("username", username);
            request.setAttribute("username", username);
            response.sendRedirect("/lab05/HomeServlet");
        }
    }
}
