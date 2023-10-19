package org.example.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Utils.HibernateUtils;
import org.hibernate.Session;

@WebServlet("/Program")
public class Program extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        Session session = HibernateUtils.getFactory().openSession();
        try{
            session.beginTransaction();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
