package org.example.Servlet;

import org.example.DAO.ProductDAO;
import org.example.Domain.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
    ProductDAO pr = new ProductDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HomeServlet");
        if (request.getSession().getAttribute("username") != null) {
            System.out.println("username: " + request.getSession().getAttribute("username"));
            System.out.println("redirect to index");
            List<Product> products = pr.getAll();
            request.setAttribute("products", products);
            request.getRequestDispatcher("index.jsp").forward(request, response);
//            response.sendRedirect("/lab05_war_exploded/");
        } else {
            System.out.println("redirect to login");
            response.sendRedirect("/lab05/login");
//            request.getRequestDispatcher("login.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
