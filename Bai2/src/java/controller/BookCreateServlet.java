package controller;

import dao.BookCategoryDAO;
import dao.BookDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Book;
import model.BookCategory;
import model.User;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/book/create")
public class BookCreateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.getWriter().write("access denied");
            return;
        }

        // Lấy danh sách thể loại
        BookCategoryDAO dao = new BookCategoryDAO();
        List<BookCategory> categories = dao.getAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/view/book-create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.getWriter().write("access denied");
            return;
        }

        // Lấy dữ liệu từ form
        String title = request.getParameter("title");
        Date date = Date.valueOf(request.getParameter("date"));
        int cid = Integer.parseInt(request.getParameter("cid"));

        // Tạo và lưu sách
        Book book = new Book(0, title, date, cid, user.getUsername());
        new BookDAO().createBook(book);

        response.sendRedirect(request.getContextPath() + "/book/own");
    }
}
