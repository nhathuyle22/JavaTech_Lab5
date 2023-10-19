package org.example.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.DAO.ProductDAO;
import org.example.Domain.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    private static final ProductDAO productService = new ProductDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        /product?action=del&id=1
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if (action != null && action.equals("del") && id != null) {
            Product product = productService.get(Integer.parseInt(id));
            if (product != null) {
                productService.remove(product);
                request.setAttribute("message", "Delete product successfully");
                request.setAttribute("type", "success");
            } else {
                request.setAttribute("message", "Cannot delete product");
                request.setAttribute("type", "danger");
            }
        }

//        if (action != null && action.equals("update") && id != null) {
//            Product product = productService.get(Integer.parseInt(id));
//            if (product != null) {
//                productService.update(product);
//                request.setAttribute("message", "Update product successfully");
//                request.setAttribute("type", "success");
//            } else {
//                request.setAttribute("message", "Cannot update product");
//                request.setAttribute("type", "danger");
//            }
//        }

        List<Product> products = (List<Product>) productService.getAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name").trim();
        String price = request.getParameter("price").trim();

        Product product = productService.add(new Product(name, Double.parseDouble(price)));
        if (product == null) {
            request.setAttribute("message", "Cannot add product");
            request.setAttribute("type", "danger");
        } else {
            request.setAttribute("message", "Add product successfully");
            request.setAttribute("type", "success");
        }

        List<Product> products = (List<Product>) productService.getAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
