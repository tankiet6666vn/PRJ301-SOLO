package controller;

import dao.BookCategoryDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.BookCategory;

import java.io.IOException;
import java.util.List;

@WebServlet("/category/list")
public class CategoryListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookCategoryDAO dao = new BookCategoryDAO();
        List<BookCategory> list = dao.getAll();
        request.setAttribute("categories", list);
        request.getRequestDispatcher("/view/category-list.jsp").forward(request, response);
    }
}
